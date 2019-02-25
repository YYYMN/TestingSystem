package com.testingSystem.model.services;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.dao.TestDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.entity.Statistic;
import com.testingSystem.model.entity.Test;
import com.testingSystem.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserStatisticService {

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

    // Кастомный класс для хранения количества вопросов в тесте
    private static final class CountQuestionsInTest{
        private int count;

        CountQuestionsInTest(int count) {
            this.count = count;
        }

        int getCount() {
            return count;
        }

        void setCount(int count) {
            this.count = count;
        }
    }

    private QuestionDao questionDao;
    private StatisticDao statisticDao;
    private TestDao testDao;
    private UserDao userDao;

    @Autowired
    public UserStatisticService(QuestionDao questionDao, StatisticDao statisticDao, TestDao testDao,UserDao userDao) {
        this.questionDao = questionDao;
        this.statisticDao = statisticDao;
        this.testDao = testDao;
        this.userDao = userDao;
    }

    // usersIdSet и List<Statistic>
    private Map<Integer,List<Statistic>> getUsersIdMapWhoPassedTheTest(Integer testId, CountQuestionsInTest countQuestionsInTest){
        List<Question> allQuestionsFromTest = questionDao.getAllQuestionsByTestId(testId);

        // Если на 1й вопрос из теста нет ни одного ответа, т.е вернулся пустой лист,
        // значит и для всех отстальных вопросов не будет ответов в БД. Тест никто ни разу не проходил.
        List<Statistic> statisticList = statisticDao.getAllStatisticByQuestionId(allQuestionsFromTest.get(0).getQuestionId());
        if(!statisticList.isEmpty()){
            // записать количество вопросов в тесте
            countQuestionsInTest.setCount(allQuestionsFromTest.size());

            for (int i = 1; i < allQuestionsFromTest.size(); i++ ){
                statisticList.addAll(statisticDao.getAllStatisticByQuestionId(allQuestionsFromTest.get(i).getQuestionId()));
            }
            // Если хоть кто-то из пользователей проходил тест, сгруппировать всю статистику по UserId
            return statisticList.stream()
                    .collect(Collectors.groupingBy(Statistic::getUserId));
        }else {
            return null;
        }

    }

    public List<UserInfo> getUserInfoList(){
        List<UserInfo> usersInfoList = new ArrayList<>();
        List<Test> testList = testDao.getAllTests();
        for (Test test: testList) {
            CountQuestionsInTest countQuestionsInTest = new CountQuestionsInTest(0);
            // usersIdSet и List<Statistic>
            Map<Integer, List<Statistic>> usersStatisticMapWhoPassedTheTest = getUsersIdMapWhoPassedTheTest(test.getTestId(),countQuestionsInTest);
            // Если вернулся null, никто из пользователей не проходил тест.
            if (usersStatisticMapWhoPassedTheTest != null) {
                /*
                 * * карта пользователей. Ключём является id. Необходима для
                 * * быстрого получения имени и фамилии пользователя, вместо
                 * * постоянного обращения к бд.
                 * */
                Map<Integer,User> allUsersMapFromBD = userDao.getAllUsers().stream()
                        .collect(Collectors.toMap(User::getUserId,user -> user));

                /*
                 * Множество всех пользователей, которые проходили
                 * данный тест.
                 */
                Set<Integer> usersIdSetWhoPassedTheTest = usersStatisticMapWhoPassedTheTest.keySet();

                /* поиск пользователя прошёдшего тест и
                 * подсчёт процента правильных ответов
                 */
                for (Integer userId: usersIdSetWhoPassedTheTest) {
                    // собственно сам поиск юзера
                    User user = allUsersMapFromBD.get(userId);
                    List<Statistic> statisticList = usersStatisticMapWhoPassedTheTest.get(userId);

                    // количество правильных ответов на тест
                    double countOfCorrectAnswers = 0;
                    for (Statistic statistic: statisticList){
                        if(statistic.isCorrect()) countOfCorrectAnswers++;
                    }
                    usersInfoList.add(new UserInfo(user.getLastName() + " " + user.getFirstName(),
                            test.getTestName(),
                            statisticList.size()/countQuestionsInTest.getCount(),
                            (int) Math.round(countOfCorrectAnswers / statisticList.size() * 100)));
                }
            }
        }
        // сортировка списка по Фамилии
        usersInfoList.sort(Comparator.comparing(o -> o.userNameAndSurname));
        return usersInfoList;
    }
}
