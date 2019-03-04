package com.testingSystem.model.services;

import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionEditingService {

    private QuestionImpl questionImpl;
    private AnswerImpl answerImpl;

    @Autowired
    public QuestionEditingService(QuestionImpl questionImpl, AnswerImpl answerImpl) {
        this.questionImpl = questionImpl;
        this.answerImpl = answerImpl;
    }

    public String addQuestionByButton(String button, String question, String[] answers) {
        if ("save".equals(button)) {
            questionImpl.addQuestionToDb(new Question(question));
            answerImpl.addAnswersToDb(answers, question);
            return "CreateQuestion";
        }else return "CreateQuestion";
    }
}
