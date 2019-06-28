package com.testingSystem.model.services;

import com.testingSystem.model.entity.Statistic;

import java.util.List;

public interface CalculatePercentage {
    default int calculatePercentage(List<Statistic> statisticList){

        int countOfTrueAnswers = 0;
        double countQuestions = statisticList.size();

        for (Statistic statistic : statisticList){
            if (statistic.isCorrect()) {
                countOfTrueAnswers++;
            }
        }
        return  (int) Math.round(countOfTrueAnswers / countQuestions * 100);
    }
}
