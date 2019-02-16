package com.testingSystem.model.services;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.dao.TestDao;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.entity.Statistic;
import com.testingSystem.model.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestStatisticService {

    public static final class TestInfo{
        private String test;
        private String numberOfTimes;
        private String percent;

        public TestInfo(String test, String numberOfTimes, String percent) {
            this.test = test;
            this.numberOfTimes = numberOfTimes;
            this.percent = percent;
        }

        public String getTest() {
            return test;
        }

        public String getNumberOfTimes() {
            return numberOfTimes;
        }

        public String getPercent() {
            return percent;
        }


    }

    private QuestionDao questionDao;
    private StatisticDao statisticDao;
    private TestDao testDao;

    @Autowired
    public TestStatisticService(QuestionDao questionDao, StatisticDao statisticDao, TestDao testDao) {
        this.questionDao = questionDao;
        this.statisticDao = statisticDao;
        this.testDao = testDao;
    }

    /**
     * Получает лист вопросов из одного теста и возвращает
     *  информацию по этому тесту. Количество вопрос в тесте не меняется. Если кто-то
     *  добавил или удалил вопрос в тест, создаётся новый тест с таким же именем, но
     *  с изменёнными вопросами.
     *  Таким образо: Если вопросов в тесте 5, а ответов на них 10,
     * значит тест был пройден дважды.
     */
    private TestInfo getTestInfo(String testName, List<Question> allQuestionsFromTest){
        TestInfo testInfo = null;
        // сумарное количество ответов на вопрос
        int numberOfQuestionsInTest= 0;
        // сколько раз был пройден тест
        int numberOfTimes = 0;
        // количество правильных ответов на тесте
        double countOfCorrecetAnswers = 0;

        // Если на 1й вопрос из теста нет ни одного ответа, т.е вернулся пустой лист
        // значит и для всех отстальных вопросов не будет ответов в БД. Тест ни разу не проходили.
        List<Statistic> statisticList = statisticDao.getAllStatisticByQuestionId(allQuestionsFromTest.get(0).getQuestionId());
        if(!statisticList.isEmpty()){
            numberOfQuestionsInTest = statisticList.size();
        }else {
            return new TestInfo(testName,"Тест ни разу не проходили", "0%");
        }

        // накапливание всех возможных ответов на вопросы из теста. Начинаем со второго вопроса из теста. (индекс у него в листе 1)
        // и добавляем в лист
        for (int i = 1; i < allQuestionsFromTest.size(); i++ ){
            statisticList.addAll(statisticDao.getAllStatisticByQuestionId(allQuestionsFromTest.get(i).getQuestionId()));
        }

        for (Statistic statistic: statisticList){
            if (statistic.isCorrect()){
                countOfCorrecetAnswers++;
            }
        }

        numberOfTimes = statisticList.size()/numberOfQuestionsInTest;
        return new TestInfo(testName, String.valueOf(numberOfTimes),countOfCorrecetAnswers/statisticList.size() + "0");
    }

    public List<TestInfo> getTestInfoList(){
        List<TestInfo> testInfoList = new ArrayList<>();

        // Получить все тесты
        List<Test> testList = testDao.getAllTests();
        for (Test test: testList){
            // Для каждого теста получить список вопросов к нему и передать в функцию
            TestInfo testInfo = getTestInfo(test.getTestName(),questionDao.getAllQuestionsByTestId(test.getTestId()));
            testInfoList.add(testInfo);
        }

        //Могут повторяться имена тестов. В идеале их надо объединять в один.
        return testInfoList;
    }

}