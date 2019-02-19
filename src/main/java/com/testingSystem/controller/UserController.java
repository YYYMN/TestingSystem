package com.testingSystem.controller;

import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private UserStatisticService userStatisticService;

    @Autowired
    public UserController(UserStatisticService userStatisticService) {
        this.userStatisticService = userStatisticService;
    }

    @GetMapping
    @RequestMapping("/UsersInfo")
    public String showTestStatistic(Model model){
        model.addAttribute("list", userStatisticService.getUserInfoList());
        return "UsersInfo";
    }
}
