package com.testingSystem.controller;

import com.testingSystem.model.services.QuestionStatisticService;
import com.testingSystem.model.services.TestStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    private TestStatisticService testStatisticService;

    @Autowired
    public TestController(TestStatisticService testStatisticService) {
        this.testStatisticService= testStatisticService;
    }

    @GetMapping
    @RequestMapping()
    public String showTestStatistic(Model model){
        model.addAttribute("list", testStatisticService.getTestInfoList());
        return "TestInfo";
    }
}
