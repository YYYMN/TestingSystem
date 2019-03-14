package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Answer;
import com.testingSystem.model.entity.Question;

import java.util.List;

public interface AnswerDao {
    List<Answer> getAllAnswers();
    List<Answer> getAnswersByQuestionId(int questionId);
    void deleteAnswerFromDb(String answer);
    void addAnswersToDb(String[] answers, String question, String[] checkbox_option);
}
