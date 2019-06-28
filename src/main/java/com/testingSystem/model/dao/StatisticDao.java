package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Statistic;

import java.util.Date;
import java.util.List;

public interface StatisticDao {
    List<Statistic> getAllStatisticByQuestionId(Integer questionId);
    //List<Statistic> getAllStatisticByTestId(Integer testId);
    List<Statistic> getAllStatisticByUserId(Integer userId);
    List<Statistic> getAllStatistic();
    void addStatistic(String date, int correct, int questionId, int userId, int testId);
}
