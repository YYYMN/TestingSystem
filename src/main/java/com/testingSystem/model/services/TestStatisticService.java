package com.testingSystem.model.services;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.dao.TestDao;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.entity.Statistic;
import com.testingSystem.model.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TestStatisticService implements CalculatePercentage {

    //Кастомный класс для вывода данных в JSP
    public static final class TestInfo{
        private String testName;
        private int numberOfTimes;
        private int percent;

        TestInfo(String testName, int numberOfTimes, int percent) {
            this.testName = testName;
            this.numberOfTimes = numberOfTimes;
            this.percent = percent;
        }

        public String getTestName() { return testName; }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }

        public int getPercent() {
            return percent;
        }

        @Override
        public String toString() {
            return testName + " " +
                    numberOfTimes + " " +
                    percent;
        }
    }

    private QuestionDao questionDao;
    private StatisticDao statisticDao;
    private TestDao testDao;

    @Autowired
    public TestStatisticService(QuestionDao questionDao, StatisticDao statisticDao, TestDao testDao) {
        this.questionDao = questionDao;
        this.statisticDao = statisticDao;
        this.testDao = testDao;
    }

    /**
     * Получает лист вопросов из одного теста и возвращает
     *  информацию по этому тесту. Количество вопрос в тесте не меняется. Если кто-то
     *  добавил или удалил вопрос в тест, создаётся новый тест с таким же именем, но
     *  с изменёнными вопросами.
     *  Таким образо: Если вопросов в тесте 5, а ответов на них 10,
     * значит тест был пройден дважды.
     */
    private TestInfo getTestInfo(String testName, List<Statistic> statisticListOfOneTest){
        int percent;
        int count; // сколько раз был пройден тест

        percent = calculatePercentage(statisticListOfOneTest);
        count = Collections.frequency(statisticListOfOneTest, statisticListOfOneTest.get(0));

        return new TestInfo(testName,count,percent);
    }

    public List<TestInfo> getTestInfoList(){
        List<TestInfo> testInfoList = new ArrayList<>();
        // карта тесто и их id
        Map<Integer,Test> testNameMap = testDao.getAllTests().stream()
                .collect(Collectors.toMap(Test::getTestId, test -> test));
        // карта всей статистики сгруппированной по testId
        Map<Integer,List<Statistic>> statisticListMap = statisticDao.getAllStatistic().stream()
                .collect(Collectors.groupingBy(Statistic::getTestId, HashMap::new,Collectors.toCollection(ArrayList::new)));

        for (Integer testId : statisticListMap.keySet()){
            TestInfo testInfo = getTestInfo(testNameMap.get(testId).getTestName(), statisticListMap.get(testId));
            testInfoList.add(testInfo);
        }

        // Надо учесть тесты с одинаковыми именами...
        Map<String,List<TestInfo>> mapOfLists = testInfoList.stream()
                .collect(Collectors.groupingBy(TestInfo::getTestName, HashMap::new,Collectors.toCollection(ArrayList::new)));
        testInfoList.clear();

        for (String testName : mapOfLists.keySet()) {
            List<TestInfo> list = mapOfLists.get(testName);
            int count = 0; // количество пройденых раз просуммировать
            int percent = 0; // проценты сложить и поделить на количесвто
            for (TestInfo testInfo : list){
                count += testInfo.getNumberOfTimes();
                percent += testInfo.getPercent();
            }
            percent /= list.size();
            testInfoList.add(new TestInfo(testName, count, percent));
        }

       // отсотрировать по имени теста прежде чем отправить
        testInfoList.sort(Comparator.comparing(o -> o.testName));
        return testInfoList;
    }
}