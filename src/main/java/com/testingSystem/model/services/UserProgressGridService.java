package com.testingSystem.model.services;

import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.dao.TestDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.Statistic;
import com.testingSystem.model.entity.Test;
import com.testingSystem.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserProgressGridService {

    public static final class UserGrid{
        private String testName;
        private Integer testId;
        private List<Integer> percentList;

        UserGrid(String testName,Integer testId, List<Integer> percentList) {
            this.testName = testName;
            this.testId = testId;
            this.percentList = percentList;
        }

        public String getTestName() {
            return testName;
        }

        public List<Integer> getPercentList() {
            return percentList;
        }

        public Integer getTestId() {
            return testId;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public void setPercentList(List<Integer> percentList) {
            this.percentList = percentList;
        }

        public void setTestId(Integer testId) {
            this.testId = testId;
        }
    }

    private TestDao testDao;
    private StatisticDao statisticDao;
    private UserDao userDao;

    @Autowired
    public UserProgressGridService(TestDao testDao, StatisticDao statisticDao, UserDao userDao) {
        this.testDao = testDao;
        this.statisticDao = statisticDao;
        this.userDao = userDao;
    }

    /**
     * @param statistics Принимает список статистики только конкретного теста ( testId ) !!!
     * и группирует по дате прохождения теста
     * @return результаты прохожднеия тестов в процентах по датам
     */
    private List<Integer> getPercentList(List<Statistic> statistics){

        List<Integer> percentList = new ArrayList<>();

        // Группируем статистику по дате прохождения теста
        Map<Date,List<Statistic>> statisticMapByData = statistics.stream().collect(
                Collectors.groupingBy(Statistic::getDate, HashMap::new, Collectors.toCollection(ArrayList::new)));

        // На всякий случай надо отсортировать ключи для сохрнения хронологии
        for (Date date: new TreeSet<>(statisticMapByData.keySet())){

            List<Statistic> statisticList = statisticMapByData.get(date);
            int percent;
            int countTrueAnswers = 0;

            // имеет разный размер т.к в день можно проходить один тест разное количество раз
            double countQuestions = statisticList.size();

            for (Statistic statistic: statisticList){
                if (statistic.isCorrect()) countTrueAnswers++;
            }
            percent = (int) Math.round(countTrueAnswers / countQuestions * 100);

            percentList.add(percent);
        }
            return percentList;
    }


    public List<UserGrid> getUserProgressGrid(Integer userId){
        // Список кастомных объектов для отображения
        List<UserGrid> userProgressGridList = new ArrayList<>();
        List<UserGrid> userProgressGridListFinal = new ArrayList<>();

        // Карта всех тестов и их названий, чтобы каждый раз не обращаться в бд
        Map<Integer, String> mapTestIdName =  testDao.getAllTests().stream().collect(
                Collectors.toMap(Test::getTestId,Test::getTestName));

        // Сначала надо сджоинить статистику по testId, а потом уже по дате прохождения теста
        Map<Integer,List<Statistic>> statisticMapByTestId = statisticDao.getAllStatisticByUserId(userId).stream().collect(
                Collectors.groupingBy(Statistic::getTestId, HashMap::new, Collectors.toCollection(ArrayList::new)));


        for (Integer testId: statisticMapByTestId.keySet()) {

            // получить лист статистики по testId и отдать другой функции
            List<Integer> percentList = getPercentList(statisticMapByTestId.get(testId));

            // накапливаем прогресс в лист
            userProgressGridList.add(new UserGrid(mapTestIdName.get(testId), testId, percentList));
        }

        // группируем объекты по названию теста в мапку.....
        Map<String, List<UserGrid>> map = userProgressGridList.stream()
                .collect(Collectors.groupingBy(UserGrid::getTestName, HashMap::new, Collectors.toCollection(ArrayList::new)));

        List<Integer> allPercents = new ArrayList<>();
        for (String testName : map.keySet()){
            List<UserGrid> userGridList = map.get(testName);
            // сортируем по testId для сохранения хранологии
            userGridList.sort((o1, o2) -> (o1.testId > o2.testId) ? 1 : ((o1.testId.equals(o2.testId)) ? 0 : -1));
            for (UserGrid userGrid : userGridList){
                allPercents.addAll(userGrid.getPercentList());
            }
            userProgressGridListFinal.add(new UserGrid(testName,null, new ArrayList<>(allPercents)));
            allPercents.clear();
        }

        return userProgressGridListFinal;
        // return userProgressGridList; // бес группировки по testName
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
