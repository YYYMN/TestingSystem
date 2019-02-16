package com.testingSystem.model.services;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class QuestionStatisticService {

    private static final class QuestionNumberOfTimesPercent {
        private String question;
        private String numberOfTimes;
        private String percent;

        public QuestionNumberOfTimesPercent() { }

        public void setQuestion(String question) {
            this.question = question;
        }

        public void setNumberOfTimes(String numberOfTimes) {
            this.numberOfTimes = numberOfTimes;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getQuestion() {
            return question;
        }

        public String getNumberOfTimes() {
            return numberOfTimes;
        }

        public String getPercent() {
            return percent;
        }
    }

    private QuestionDao questionDao;

    @Autowired
    public QuestionStatisticService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    private List<QuestionNumberOfTimesPercent> questionNumberOfTimesPercentList;

    public List<QuestionNumberOfTimesPercent> dataForQuestionStatisticPage(){


       QuestionNumberOfTimesPercent object = new QuestionNumberOfTimesPercent();

        List<Question> questionList = questionDao.getAllQuestions();


        questionNumberOfTimesPercentList.add(object);
        return questionNumberOfTimesPercentList;
    }
}
