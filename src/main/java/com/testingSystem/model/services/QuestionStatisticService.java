package com.testingSystem.model.services;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionStatisticService {

    /**
     * Кастомный класс для вывода данных в JSP
     */
    public static final class QuestionInfo {
        private String questionName;
        private int numberOfTimes;
        private int percent;

        QuestionInfo(String questionName, int numberOfTimes, int percent) {
            this.questionName = questionName;
            this.numberOfTimes = numberOfTimes;
            this.percent = percent;
        }

        @Override
        public String toString() {
            return questionName + " " +
                    numberOfTimes + " " +
                    percent;
        }

        public String getQuestionName() {
            return questionName;
        }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }

        public int getPercent() {
            return percent;
        }
    }

    private QuestionDao questionDao;
    private StatisticDao statisticDao;

    @Autowired
    public QuestionStatisticService(QuestionDao questionDao, StatisticDao statisticDao) {
        this.questionDao = questionDao;
        this.statisticDao = statisticDao;
    }

    /**
     * Получает объкт типа Question. На основании его делает запрос в БД и формирует объект для JSP.
     */
    private QuestionInfo getQuestionInfo(Question question){
        QuestionInfo questionInfo;

        List<Statistic> statisticList = statisticDao.getAllStatisticByQuestionId(question.getQuestionId());
        // System.out.println(statisticList); Почему он создаётся 2 раза????!!!!
        //Если есть хоть какая-то статистика для вопроса
        if(!statisticList.isEmpty()){
            // сумарное количество ответов на вопрос
            int numberOfTimes = statisticList.size();
            // количество правильных ответов на вопрос
            double countOfCorrecetAnswers = 0;
            for (Statistic statistic : statisticList){
                if (statistic.isCorrect()){
                    countOfCorrecetAnswers ++;
                }
            }
            questionInfo = new QuestionInfo(
                    question.getDescription(),
                    numberOfTimes,
                    (int) Math.round(countOfCorrecetAnswers / numberOfTimes * 100));
        }
        // Иначе вернуть описание вопроса и, что пройден он 0 раз.
        else {
            questionInfo = null;
                    /*new QuestionInfo(question.getDescription(),
                    "Ответов на вопрос ещё не было",
                    "0%");*/
        }

        return questionInfo;
    }

    /**
     * Возвращает лист объектов для JSP
     */
    public List<QuestionInfo> getQuestionInfoList(){
        List<QuestionInfo> questionInfoList = new ArrayList<>();
        QuestionInfo questionInfo;
        List<Question> questionList = questionDao.getAllQuestions();
        for (Question question : questionList) {
            questionInfo = getQuestionInfo(question);
            if(questionInfo != null){
                questionInfoList.add(questionInfo);
            }
        }

        //questionNumberOfTimesPercentList = questionNumberOfTimesPercentList.stream()
          //      .collect(Collectors.groupingBy(QuestionInfo::getQuestionName)).values();
        // прежде чем вернуть этот лист, небходимо сгруппировать вопросы с одинаковым описанием в один.
        // т.к один и тот же вопрос может относиться к разным тестам из одного топика.
        return questionInfoList;
    }
}
