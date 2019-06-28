package com.testingSystem.model.services;

import com.google.gson.Gson;
import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.daoimpl.TestImpl;
import com.testingSystem.model.entity.Answer;
import com.testingSystem.model.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionAndTestService {

    private QuestionImpl questionImpl;
    private AnswerImpl answerImpl;
    private TestImpl testImpl;


    @Autowired
    public QuestionAndTestService(TestImpl testImpl, QuestionImpl questionImpl, AnswerImpl answerImpl) {
        this.questionImpl = questionImpl;
        this.answerImpl = answerImpl;
        this.testImpl = testImpl;
    }

    public void addQuestionAndAnswersToDb(String question, String[] answers, String[] checkbox_option) {
            questionImpl.addQuestionToDb(question);
            answerImpl.addAnswersToDb(answers, question, checkbox_option);
    }

    public List<Answer> getAnswerByQuestionId(int question) {
        return answerImpl.getAnswersByQuestionId(question);
    }

    public String getAllQuestionsAsJSONArray(){
        Gson gson = new Gson();
        return gson.toJson(questionImpl.getAllQuestions());
    }

    public void addTestToDb(String topic, String test, String[] questions, String testID) {
        if (testID.equals("")) {
            testImpl.addNewTestToDB(topic, test, questions);
            questionImpl.addQuestionsAndTestIdToQTConnection(questionImpl.getQuestionsIdByDescriptions(questions), test);
        }else {
            int testId = Integer.parseInt(testID);
            try{
                if (questions.length == testImpl.getCountOfQuestionsInTest(testID)){
                    testImpl.updateTest(test, testId);
                }else {
                    if (questions.length != testImpl.getCountOfQuestionsInCorrectTest(test)){
                        testImpl.addNewCorrectTestToDB(test, topic);
                        questionImpl.addQuestionsAndTestIdToQTConnection(questionImpl.getQuestionsIdByDescriptions(questions), test);
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    public List<Test> functionForList(int topicId){
        List<Test> testList = testImpl.getAllTestsByTopicId(topicId); // который вернёт JdbcTemplate

        Map<String, Test> map = testList.stream()
                .collect(Collectors.toMap(Test::getDescription, test -> test,
                        (oldTest, newTest) -> (oldTest.getTestId() > newTest.getTestId()) ? oldTest : newTest));

        testList.clear();
        for(String name : map.keySet()){
            testList.add(map.get(name));
        }
        return testList;
    }
}
