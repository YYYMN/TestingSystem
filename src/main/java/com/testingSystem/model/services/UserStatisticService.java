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

         public UserInfo() {
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

    // Кастомный класс для хранения имени теста, количества пройденых раз и процента
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
    private List<TestNameCountPercent> getTestNameCountPercentList(List<Statistic> statisticListOfOneUser){
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


        // Надо учесть тесты с одинаковыми именами...
        Map<String,List<TestNameCountPercent>> mapOfLists = testNameCountPercentList.stream()
                .collect(Collectors.groupingBy(TestNameCountPercent::getTestName,HashMap::new, Collectors.toCollection(ArrayList::new)));
        testNameCountPercentList.clear();

        for (String testName : mapOfLists.keySet()) {
            List<TestNameCountPercent> testNameCountPercents = mapOfLists.get(testName);
            count = 0; // количество пройденых раз просуммировать
            percent = 0; // проценты сложить и поделить на количесвто
            for (TestNameCountPercent testNameCountPercent : testNameCountPercents){
                count += testNameCountPercent.count;
                percent += testNameCountPercent.percent;
            }
            percent /= testNameCountPercents.size();
            testNameCountPercentList.add(new TestNameCountPercent(testName, count, percent));
        }

        return testNameCountPercentList;
    }

    public List<UserInfo> getUserInfoList(){
        List<UserInfo> usersInfoList = new ArrayList<>();

        // сначала сгруппировать всю статистику по user_Id
        Map<Integer,List<Statistic>> statisticMapByUserId = statisticDao.getAllStatistic().stream()
                .collect(Collectors.groupingBy(Statistic::getUserId, HashMap::new, Collectors.toCollection(ArrayList::new)));

        User user;
        List<TestNameCountPercent> testNameCountPercentList;
        for (Integer userId : statisticMapByUserId.keySet()){
            testNameCountPercentList = getTestNameCountPercentList(statisticMapByUserId.get(userId));

            for (TestNameCountPercent testNameCountPercent : testNameCountPercentList) {
                user = mapUserIdUser.get(userId);

                usersInfoList.add(new UserInfo(user.getLastName() + " " + user.getFirstName()
                        ,testNameCountPercent.testName,testNameCountPercent.count,testNameCountPercent.percent));
            }
        }
        // сортировка списка по Фамилии
        usersInfoList.sort(Comparator.comparing(o -> o.userNameAndSurname));
        return usersInfoList;
    }
}
