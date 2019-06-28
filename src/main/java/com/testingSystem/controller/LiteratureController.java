package com.testingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LiteratureController {

    @PostMapping("/user/user-main-page")
    public String testPostMapping(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println(name);
        return "";
    }
}
