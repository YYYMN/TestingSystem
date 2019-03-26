package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.services.QuestionAndTestService;
import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin/questions-info")
    public String showQuestionStatistic(Model model) {
        model.addAttribute("list",questionStatisticService.getQuestionInfoList());
        return "/admin/forStatistic/questions-info";
    }

    @GetMapping("/CreateQuestion")
    public String createQuestion(Model model){
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "CreateQuestion";
    }

    @PostMapping("/CreateQuestion")
    public String createQuestion(@RequestParam(name = "question", required = false) String question,
                                 @RequestParam(name = "answer[]", required = false) String[] answers,
                                 @RequestParam(name="checkbox_option", required = false) String[] checkbox_option, Model model) {
        questionAndTestService.addQuestionAndAnswersToDb(question, answers, checkbox_option);
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "CreateQuestion";
    }

}
