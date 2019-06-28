package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.*;
import com.testingSystem.model.entity.*;
import com.testingSystem.model.services.CalculatePercentage;
import com.testingSystem.model.services.QuestionAndTestService;
import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Controller
public class QuestionController {

    private QuestionStatisticService questionStatisticService;
    private QuestionImpl questionImpl;
    private QuestionAndTestService questionAndTestService;
    private UserImpl userImpl;
    private StatisticImpl statisticImpl;
    private LiteratureImpl literatureImpl;
    private LinkImpl linkImpl;
    private TestImpl testImpl;
    private QTConnImpl qtConnImpl;

    @Autowired
    public QuestionController(QuestionStatisticService questionStatisticService,
                              QuestionImpl questionImpl,
                              QuestionAndTestService questionAndTestService,
                              UserImpl userImpl,
                              StatisticImpl statisticImpl,
                              LiteratureImpl literatureImpl,
                              LinkImpl linkImpl,
                              TestImpl testImpl,
                              QTConnImpl qtConnImpl) {
        this.questionStatisticService = questionStatisticService;
        this.questionImpl = questionImpl;
        this.questionAndTestService = questionAndTestService;
        this.userImpl = userImpl;
        this.statisticImpl = statisticImpl;
        this.literatureImpl = literatureImpl;
        this.linkImpl = linkImpl;
        this.testImpl = testImpl;
        this.qtConnImpl = qtConnImpl;
    }

    @GetMapping({"/admin/questions-info", "tutor/questions-info"})
    public String showQuestionStatistic(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("list", questionStatisticService.getQuestionInfoList());
        String role = (String) session.getAttribute("role");
        return "/" + role + "/forStatistic/questions-info";
    }

    @GetMapping("/tutor/add-or-update-question")
    public String createQuestion(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Integer testId = Integer.parseInt(request.getParameter("testId"));
        System.out.println(testId);
        model.addAttribute("questions", questionImpl.getAllQuestionsByTestId(testId));
        model.addAttribute("testId", testId);
        System.out.println("Это просто создать вопрос");
        return "tutor/add-or-update-question";
    }

    @PostMapping("/tutor/add-or-update-question")
    public String createQuestion(@RequestParam(name = "question", required = false) Object question,
                                 @RequestParam(name = "answer[]", required = false) Object[] answers,
                                 @RequestParam(name = "checkbox_option", required = false) Object[] checkbox_option,
                                 Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        questionAndTestService.addQuestionAndAnswersToDb((String) question, (String[]) answers, (String[]) checkbox_option);
        int testId = Integer.parseInt(request.getParameter("testId"));
        String question1 = (String) question;
        Question question2 = questionImpl.getQuestionByDescription(question1);
        qtConnImpl.addTestIdAndQuestionIdToCTConn(testId, question2.getQuestionId());
        System.out.println("TestID - " + testId + "questionId - " + question2.getQuestionId());
        model.addAttribute("questions", questionImpl.getAllQuestions());
        System.out.println("Создать несколько вопросов");
        return "tutor/add-or-update-question";
    }


    @GetMapping("/user/forTest/start-test")
    public String startTest(@RequestParam(name = "testId") int testId,
                            @SessionAttribute(name = "questionId") int questionId,
                            Model model, HttpServletRequest request) {
        List<Integer> allQuestionsIdByTestId = questionImpl.getAllQuestionsIdByTestId(testId);
        if (questionId == allQuestionsIdByTestId.size()) {
            List<Integer> correctAnswers = (List<Integer>) request.getSession().getAttribute("correctAnswer");
            List<Integer> questionWrongAnswerID = (List<Integer>) request.getSession().getAttribute("questionWrongAnswerID");
            int rightAnswer = 0;
            int wrongAnswer = 0;
            for (Integer correctAnswer : correctAnswers) {
                if (correctAnswer == 1) {
                    rightAnswer++;
                } else {
                    wrongAnswer++;
                }
            }
            List<Literature> literatureList = new ArrayList<>();
            List<Link> linkList = new ArrayList<>();
            for (Integer integer : questionWrongAnswerID) {
                literatureList.add(literatureImpl.getLiteratureByQuestionId(integer));
            }
            for (Literature literature : literatureList) {
                linkList.add(linkImpl.getLinkByLiteratureId(literature.getLiteratureId()));
            }
            model.addAttribute("literatureList", literatureList);
            model.addAttribute("linkList", linkList);
            model.addAttribute("rightAnswers", rightAnswer);
            model.addAttribute("wrongAnswers", wrongAnswer);
            model.addAttribute("corrects", correctAnswers);
            model.addAttribute("questionWrongAnswerID", questionWrongAnswerID);
            return "user/forTest/finish";
        }
        Question question = questionImpl.getQuestionByTestIdAndQuestionId(testId, allQuestionsIdByTestId.get(questionId));
        List<Answer> answer = questionAndTestService.getAnswerByQuestionId(question.getQuestionId());
        String login = (String) request.getSession().getAttribute("login");
        User user = userImpl.getUserByLogin(login);
        int userId = user.getUserId();
        request.getSession().setAttribute("userId", userId);
        model.addAttribute("testId", testId);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        request.getSession().setAttribute("questionId", questionId + 1);
        return "user/forTest/test";
    }

    @GetMapping("/user/forTest/answer")
    public String answerQuestion(@RequestParam(name = "testId") int testId,
                                 @RequestParam(name = "answer") int correct,
                                 @RequestParam(name = "questionId") int questionId,
                                 @SessionAttribute(name = "userId") int userId,
                                 Model model, HttpServletRequest request) {
        List<Integer> correctAnswer = (List<Integer>) request.getSession().getAttribute("correctAnswer");
        correctAnswer.add(correct);
        List<Integer> questionWrongAnswerID = (List<Integer>) request.getSession().getAttribute("questionWrongAnswerID");
        String answer;
        if (correct == 1) {
            answer = "Правильно!!!";
        } else {
            questionWrongAnswerID.add(questionId);
            answer = "Не правильно!!!";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        statisticImpl.addStatistic(dateFormat.format(date), correct, questionId, userId, testId);
        model.addAttribute("testId", testId);
        model.addAttribute("answer", answer);
        return "user/forTest/answer";
    }
}
