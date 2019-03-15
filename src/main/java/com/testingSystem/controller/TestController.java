package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.TestImpl;
import com.testingSystem.model.daoimpl.TopicImpl;
import com.testingSystem.model.services.TestStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    private TestStatisticService testStatisticService;
    private TopicImpl topicImpl;
    private TestImpl testImpl;

    @Autowired
    public TestController(TestStatisticService testStatisticService, TopicImpl topicImpl, TestImpl testImpl) {
        this.testStatisticService = testStatisticService;
        this.topicImpl = topicImpl;
        this.testImpl = testImpl;
    }

    @GetMapping("/TestsInfo")
    public String showTestStatistic(Model model){
        model.addAttribute("list", testStatisticService.getTestInfoList());
        return "TestsInfo";
    }

    @GetMapping("/CreateTest")
    public String createTest(Model model){
        model.addAttribute("topics", topicImpl.getAllTopics());
        return "CreateTest";
    }

    @PostMapping("/CreateTest")
    public void catchTopic(Model model, @RequestAttribute(name = "topic") String topic){
        int topicId = topicImpl.getTopicByDescription(topic).getTopicId();
        model.addAttribute("tests", testImpl.getAllTestsByTopicId(topicId));
    }
}
