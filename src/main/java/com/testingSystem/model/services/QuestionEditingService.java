package com.testingSystem.model.services;

import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionEditingService {

    private QuestionImpl questionImpl;

    @Autowired
    public QuestionEditingService(QuestionImpl questionImpl) {
        this.questionImpl = questionImpl;
    }

    public String addByButton(String button, String question) {
        if ("save".equals(button)) {
            questionImpl.addQuestionToDb(new Question(question));
            return "CreateQuestion";
        }else return "CreateQuestion";
    }
}
