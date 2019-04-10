package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.services.QuestionAndTestService;
import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {

    private QuestionStatisticService questionStatisticService;
    private QuestionImpl questionImpl;
    private QuestionAndTestService questionAndTestService;

    @Autowired
    public QuestionController(QuestionStatisticService questionStatisticService, QuestionImpl questionImpl, QuestionAndTestService questionAndTestService) {
        this.questionStatisticService = questionStatisticService;
        this.questionImpl = questionImpl;
        this.questionAndTestService = questionAndTestService;
    }

    @GetMapping({"/admin/questions-info", "tutor/questions-info"})
    public String showQuestionStatistic(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("list",questionStatisticService.getQuestionInfoList());
        String role = (String) session.getAttribute("role");
        return "/"+role+"/forStatistic/questions-info";
    }

    @GetMapping("/tutor/add-or-update-question")
    public String createQuestion(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "tutor/add-or-update-question";
    }

    @PostMapping("/tutor/add-or-update-question")
    public String createQuestion(@RequestParam(name = "question", required = false) Object question,
                                 @RequestParam(name = "answer[]", required = false) Object[] answers,
                                 @RequestParam(name="checkbox_option", required = false) Object[] checkbox_option,
                                 Model model, HttpServletRequest request,  HttpServletResponse response, HttpSession session) {
        questionAndTestService.addQuestionAndAnswersToDb((String) question, (String[]) answers, (String[])checkbox_option);
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "tutor/add-or-update-question";
    }

}
