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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @GetMapping({"/admin/tests-info", "/tutor/tests-info"})
    public String showTestStatistic(Model model, HttpSession session, HttpServletRequest request){
        model.addAttribute("list", testStatisticService.getTestInfoList());
        String role = (String) session.getAttribute("role");
        return "/"+role+"/forStatistic/tests-info";
    }

    @GetMapping("/tutor/add-or-update-test")
    public String createTest(Model model, HttpServletRequest request){
        model.addAttribute("topics", topicImpl.getAllTopics());
        model.addAttribute("JSONQuestions", questionAndTestService.getAllQuestionsAsJSONArray());
        return "tutor/add-or-update-test";
    }

    @PostMapping("/tutor/add-or-update-test")
    public String createTest(@RequestParam(name = "topic", required = false) String topic,
                             @RequestParam(name = "test", required = false) String test,
                             @RequestParam(name = "questions[]", required = false) String[] questions,
                             @RequestParam(name = "testId", required = false) String testId,
                             Model model, HttpServletRequest request){
        questionAndTestService.addTestToDb(topic, test, questions, testId);
        model.addAttribute("topics", topicImpl.getAllTopics());
        model.addAttribute("JSONQuestions", questionAndTestService.getAllQuestionsAsJSONArray());
        return "tutor/add-or-update-test";
    }
}
