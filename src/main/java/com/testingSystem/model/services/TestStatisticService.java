package com.testingSystem.model.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestStatisticService {

    public static final class TestInfo{
        private String test;
        private String numberOfTimes;
        private String percent;

        public TestInfo() {
        }

        public String getTest() {
            return test;
        }

        public String getNumberOfTimes() {
            return numberOfTimes;
        }

        public String getPercent() {
            return percent;
        }


    }

    private List<TestInfo> testInfoList;

    public List<TestInfo> getTestInfoList(){

        TestInfo object = new TestInfo();



        testInfoList.add(object);
        return testInfoList;
    }

}
