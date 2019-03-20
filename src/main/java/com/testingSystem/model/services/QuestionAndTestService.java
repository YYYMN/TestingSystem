package com.testingSystem.model.services;

import com.google.gson.Gson;
import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.daoimpl.TestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionAndTestService {

    private QuestionImpl questionImpl;
    private AnswerImpl answerImpl;
    private TestImpl testImpl;


    @Autowired
    public QuestionAndTestService(TestImpl testImpl, QuestionImpl questionImpl, AnswerImpl answerImpl) {
        this.questionImpl = questionImpl;
        this.answerImpl = answerImpl;
        this.testImpl = testImpl;
    }

    public void addQuestionAndAnswersToDb(String question, String[] answers, String[] checkbox_option) {
            questionImpl.addQuestionToDb(question);
            answerImpl.addAnswersToDb(answers, question, checkbox_option);
    }

    public String getAllQuestionsAsJSONArray(){
        Gson gson = new Gson();
        return gson.toJson(questionImpl.getAllQuestions());
    }

    public void addTestToDb(String topic, String test, String[] questions, String testId) {
        testImpl.addTestToDb(topic, test, questions, testId);

    }
}
