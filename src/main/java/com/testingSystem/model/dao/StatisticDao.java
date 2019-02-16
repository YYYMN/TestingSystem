package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Statistic;

import java.util.List;

public interface StatisticDao {
    List<Statistic> getAllStatisticByQuestionId(Integer questionId);
}
