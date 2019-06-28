package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Literature;

public interface LiteratureDao {
    Literature getLiteratureByQuestionId(int questionId);
}
