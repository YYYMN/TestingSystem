package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.daoimpl.TestImpl;
import com.testingSystem.model.daoimpl.TopicImpl;
import com.testingSystem.model.services.QuestionAndTestService;
import com.testingSystem.model.services.TestStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    private TestStatisticService testStatisticService;
    private TopicImpl topicImpl;
    private TestImpl testImpl;
    private QuestionImpl questionImpl;
    private QuestionAndTestService questionAndTestService;

    @Autowired
    public TestController(QuestionAndTestService questionAndTestService, QuestionImpl questionImpl, TestStatisticService testStatisticService, TopicImpl topicImpl, TestImpl testImpl) {
        this.testStatisticService = testStatisticService;
        this.topicImpl = topicImpl;
        this.testImpl = testImpl;
        this.questionImpl = questionImpl;
        this.questionAndTestService = questionAndTestService;
    }

    @GetMapping("/TestsInfo")
    public String showTestStatistic(Model model){
        model.addAttribute("list", testStatisticService.getTestInfoList());
        return "TestsInfo";
    }

    @GetMapping("/CreateTest")
    public String createTest(Model model){
        model.addAttribute("topics", topicImpl.getAllTopics());
        model.addAttribute("JSONQuestions", questionAndTestService.getAllQuestionsAsJSONArray());
        return "CreateTest";
    }

    @PostMapping("/CreateTest")
    public String createTest(Model model, @RequestParam(name = "topic", required = false) String topic,
                                          @RequestParam(name = "test", required = false) String test,
                                          @RequestParam(name = "questions[]", required = false) String[] questions,
                                          @RequestParam(name = "testId", required = false) String testId){
        questionAndTestService.addTestToDb(topic, test, questions, testId);
        model.addAttribute("topics", topicImpl.getAllTopics());
        model.addAttribute("JSONQuestions", questionAndTestService.getAllQuestionsAsJSONArray());
        return "CreateTest";
    }
}
