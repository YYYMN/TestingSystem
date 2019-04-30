package com.testingSystem.controller;


import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.daoimpl.TestImpl;
import com.testingSystem.model.daoimpl.TopicImpl;
import com.testingSystem.model.entity.Answer;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.entity.Test;
import com.testingSystem.model.services.QuestionAndTestService;
import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

@Controller
public class AjaxController {

    private QuestionStatisticService questionStatisticService;
    private QuestionImpl questionImpl;
    private AnswerImpl answerImpl;
    private TestImpl testImpl;
    private TopicImpl topicImpl;
    private QuestionAndTestService questionAndTestService;

    @Autowired
    public AjaxController(TopicImpl topicImpl, TestImpl testImpl, QuestionStatisticService questionStatisticService, QuestionImpl questionImpl, AnswerImpl answerImpl, QuestionAndTestService questionAndTestService) {
        this.questionStatisticService = questionStatisticService;
        this.questionImpl = questionImpl;
        this.answerImpl = answerImpl;
        this.questionAndTestService = questionAndTestService;
        this.testImpl = testImpl;
        this.topicImpl = topicImpl;
    }

    @RequestMapping(value = "/DisplayQuestionsFromDb", method = RequestMethod.GET)
    @ResponseBody
    public List<Answer> displayQuestionsFromDb(@RequestParam(name = "targetQuestion") String targetQuestion, Model model, HttpServletRequest request){
        List<Answer> answerList = answerImpl.getAnswersByQuestionId(questionImpl.getQuestionByDescription(targetQuestion).getQuestionId());
        String description;
        List<String> answersDescription = new ArrayList<>();
        for(Answer answer : answerList){
            description = answer.getDescription();
            answersDescription.add(description);
        }
        model.addAttribute("answersDescription", answersDescription);
        return answerList;
    }

    @RequestMapping(value = "/DeleteAnswerFromDb", method = RequestMethod.POST)
    @ResponseBody
    public void deleteAnswerFromDb(@RequestParam(name = "targetAnswer") String targetAnswer,
                                   HttpServletRequest request) {
        answerImpl.deleteAnswerFromDb(targetAnswer);
    }

    @RequestMapping(value = "/GetTestsByTopic", method = RequestMethod.GET)
    @ResponseBody
    public List<Test> getTestsByTopic(@RequestParam(name = "targetTopic", required = false) String targetTopic,
                                      HttpServletRequest request) {
        return questionAndTestService.functionForList(topicImpl.getTopicByDescription(targetTopic).getTopicId());
    }

    @RequestMapping(value = "/GetQuestionsByTest", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getQuestionsByTest(@RequestParam(name = "targetTest", required = false) String targetTest,
                                             Model model, HttpServletRequest request) {
        return questionImpl.getAllQuestionsByTestId(testImpl.getTestByDescription(targetTest).getTestId());
    }
}
