package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.services.QuestionAndTestService;
import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {

    private QuestionStatisticService questionStatisticService;
    private QuestionImpl questionImpl;
    private AnswerImpl answerImpl;
    private QuestionAndTestService questionAndTestService;

    @Autowired
    public QuestionController(QuestionStatisticService questionStatisticService, QuestionImpl questionImpl, AnswerImpl answerImpl, QuestionAndTestService questionAndTestService) {
        this.questionStatisticService = questionStatisticService;
        this.questionImpl = questionImpl;
        this.answerImpl = answerImpl;
        this.questionAndTestService = questionAndTestService;
    }

    @GetMapping({"/admin/questions-info", "tutor/questions-info"})
    public String showQuestionStatistic(Model model, HttpSession session) {
        model.addAttribute("list",questionStatisticService.getQuestionInfoList());
        String role = (String) session.getAttribute("role");
        return "/"+role+"/forStatistic/questions-info";
    }

    @GetMapping("/tutor/add-or-update-question")
    public String createQuestion(Model model, HttpSession session){
        model.addAttribute("questions",questionImpl.getAllQuestions());
        System.out.println(session.getId());
        String role = (String) session.getAttribute("role");
        System.out.println(role);
        return "tutor/add-or-update-question";
    }

    @PostMapping("/tutor/add-or-update-question")
    public String createQuestion(@RequestParam(name = "question", required = false) String question,
                                 @RequestParam(name = "answer[]", required = false) String[] answers,
                                 @RequestParam(name="checkbox_option", required = false) String[] checkbox_option, Model model) {
        questionAndTestService.addQuestionAndAnswersToDb(question, answers, checkbox_option);
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "tutor/add-or-update-question";
    }

}
