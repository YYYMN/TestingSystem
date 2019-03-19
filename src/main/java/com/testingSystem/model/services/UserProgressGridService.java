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
        private List<Integer> percentList;


        UserGrid(String testName, List<Integer> percentList) {
            this.testName = testName;
            this.percentList = percentList;
        }

        public String getTestName() {
            return testName;
        }

        public List<Integer> getPercentList() {
            return percentList;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public void setPercentList(List<Integer> percentList) {
            this.percentList = percentList;
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
     * @param statistics Принимает список статистики уже сгруппированный по testId
     * и группирует по дате прохождения теста
     * @param testId testId с его помощью узнает количество вопросов в тесте
     * @return результаты прохожднеия тестов в процентах по датам
     */
    private List<Integer> getPercentList(List<Statistic> statistics, Integer testId){

        List<Integer> percentList = new ArrayList<>();

        // Группируем статистику по дате прохождения теста
        Map<Date,List<Statistic>> statisticMapByData = statistics.stream().collect(
                Collectors.groupingBy(Statistic::getDate, HashMap::new, Collectors.toCollection(ArrayList::new)));

        // На всякий случай надо отсортировать ключи для сохрнения хронологии
        for (Date date: new TreeSet<>(statisticMapByData.keySet())){

            List<Statistic> statisticList = statisticMapByData.get(date);
            int pecent = 0;
            int countTrueAnswers = 0;

            // имеет разный размер т.к в день можно проходить один тест разное количество раз
            double countQuestions = statisticList.size();

            for (Statistic statistic: statisticList){
                if (statistic.isCorrect()) countTrueAnswers++;
            }
            pecent = (int) Math.round(countTrueAnswers / countQuestions * 100);

            percentList.add(pecent);
        }
            return percentList;
    }


    public List<UserGrid> getUserProgressGrid(Integer userId){
        // Список кастомных объектов для отображения
        List<UserGrid> userProgressGridList = new ArrayList<>();

        // Карта всех тестов и их названий, чтобы каждый раз не обращаться в бд
        Map<Integer, String> mapTestIdName =  testDao.getAllTests().stream().collect(
                Collectors.toMap(Test::getTestId,Test::getTestName));

        // Сначала надо сджоинить статистику по testId, а потом уже по дате прохождения теста
        Map<Integer,List<Statistic>> statisticMapByTestId = statisticDao.getAllStatisticByUserId(userId).stream().collect(
                Collectors.groupingBy(Statistic::getTestId, HashMap::new, Collectors.toCollection(ArrayList::new)));


        for (Integer testId: statisticMapByTestId.keySet()) {

            // получить лист статистики по testId и отдать другой функции
            List<Integer> percentList = getPercentList(statisticMapByTestId.get(testId),testId);

            // накапливаем прогресс в лист
            userProgressGridList.add(new UserGrid(mapTestIdName.get(testId),percentList));
        }


        return userProgressGridList;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
