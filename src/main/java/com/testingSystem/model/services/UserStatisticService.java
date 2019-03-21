package com.testingSystem.model.services;

import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.dao.TestDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.Statistic;
import com.testingSystem.model.entity.Test;
import com.testingSystem.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserStatisticService implements CalculatePercentage {

     // Кастомный класс для вывода данных в JSP
    public static final class UserInfo{
        private String userNameAndSurname;
        private String testName;
        private int numberOfTimes;
        private int percent;


        UserInfo(String userNameAndSurname, String testName,int numberOfTimes, int percent) {
            this.userNameAndSurname = userNameAndSurname;
            this.testName = testName;
            this.numberOfTimes = numberOfTimes;
            this.percent = percent;
        }


        public String getUserNameAndSurname() {
            return userNameAndSurname;
        }

        public String getTestName() {
            return testName;
        }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }

        public int getPercent() {
            return percent;
        }

        @Override
        public String toString() {
            return userNameAndSurname + " " +
                    testName + " " +
                    numberOfTimes + " " +
                    percent;
        }
    }

    // Кастомный класс для хранения имени теста, количествапройденых раз и процента
    private static final class TestNameCountPercent {
        private String testName;
        private Integer count;
        private Integer percent;

        TestNameCountPercent(String testName, Integer count, Integer percent) {
            this.testName = testName;
            this.count = count;
            this.percent = percent;
        }

        String getTestName() {
            return testName;
        }
    }

    private StatisticDao statisticDao;
    // карта хранит test_Id и testName. Чтобы много раз не обращаться к БД
    private Map<Integer,String> mapTestIdName;
    private Map<Integer,User> mapUserIdUser;

    @Autowired
    public UserStatisticService(StatisticDao statisticDao, TestDao testDao,UserDao userDao) {
        this.statisticDao = statisticDao;
        this.mapTestIdName = testDao.getAllTests().stream()
                .collect(Collectors.toMap(Test::getTestId,Test::getTestName));
        this.mapUserIdUser = userDao.getAllUsers().stream()
                .collect(Collectors.toMap(User::getUserId,user -> user));
    }

    // usersIdSet и List<Statistic>
    private Map<String,TestNameCountPercent> getTestNameCountPercentMap(List<Statistic> statisticListOfOneUser){
        // группируем статистику пользователя по testId
        Map<Integer,List<Statistic>> statisticMapByTestId = statisticListOfOneUser.stream()
                .collect(Collectors.groupingBy(Statistic::getTestId, HashMap::new, Collectors.toCollection(ArrayList::new)));

        List<TestNameCountPercent> testNameCountPercentList = new ArrayList<>();
        int percent;
        int count; // сколько раз был пройден тест
        for (Integer testId : statisticMapByTestId.keySet()){

            List<Statistic> statisticListOfOneTest = statisticMapByTestId.get(testId);
            percent = calculatePercentage(statisticListOfOneTest);
            // пройден столько раз, сколько ответов есть на первую запись в этом листе
            count = Collections.frequency(statisticListOfOneTest, statisticListOfOneTest.get(0));

            testNameCountPercentList.add(new TestNameCountPercent(mapTestIdName.get(testId),count,percent));
        }

        /*
         * сделать из testNameCountPercentList карту таких же обектов без дублирования имен тестов
         * количество пройденных раз суммируются. проценты складываются и считается их среднее
         */
        return testNameCountPercentList.stream()
                .collect(Collectors.toMap(TestNameCountPercent::getTestName,o -> o, (oldVal,newVal) -> {
                            oldVal.count = oldVal.count + newVal.count;
                            oldVal.percent = (int) Math.round((oldVal.percent + newVal.percent) / 2.);
                            return oldVal;
                        }));
    }

    public List<UserInfo> getUserInfoList(){
        List<UserInfo> usersInfoList = new ArrayList<>();

        // сначала сгруппировать всю статистику по user_Id
        Map<Integer,List<Statistic>> statisticMapByUserId = statisticDao.getAllStatistic().stream()
                .collect(Collectors.groupingBy(Statistic::getUserId, HashMap::new, Collectors.toCollection(ArrayList::new)));

        User user;
        Map<String,TestNameCountPercent> testNameCountPercentMap;
        for (Integer userId : statisticMapByUserId.keySet()){
            testNameCountPercentMap = getTestNameCountPercentMap(statisticMapByUserId.get(userId));

            TestNameCountPercent testNameCountPercent;
            for (String testName : testNameCountPercentMap.keySet()) {
                testNameCountPercent = testNameCountPercentMap.get(testName);
                user = mapUserIdUser.get(userId);

                usersInfoList.add(new UserInfo(user.getLastName() + " " + user.getFirstName()
                        ,testName,testNameCountPercent.count,testNameCountPercent.percent));

            }
        }
        // сортировка списка по Фамилии
        usersInfoList.sort(Comparator.comparing(o -> o.userNameAndSurname));
        return usersInfoList;
    }
}
