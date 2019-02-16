package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
    List<Question> getAllQuestionsByTestId(Integer testId);

}
