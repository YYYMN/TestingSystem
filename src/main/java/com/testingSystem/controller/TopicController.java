package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.TopicImpl;
import com.testingSystem.model.entity.Topic;
import com.testingSystem.model.services.CreatingAndEditingTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

@Controller
public class TopicController {

    private TopicImpl topicImpl;
    private CreatingAndEditingTopicsService creatingAndEditingTopicsService;

    @Autowired
    public TopicController(CreatingAndEditingTopicsService creatingAndEditingTopicsService,
                           TopicImpl topicImpl) {
        this.creatingAndEditingTopicsService = creatingAndEditingTopicsService;
        this.topicImpl = topicImpl;
    }

    @GetMapping("/admin/create-topic")
    public String createTopicPage(Model model, HttpServletRequest request) {
        Topic topic = new Topic();
        model.addAttribute("topic", topic);

        return "admin/forTopic/create-topic";
    }

    @PostMapping("/admin/create-topic")
    public String CreateTopic(Model model, Topic topic, BindingResult result, HttpServletRequest request) {

        creatingAndEditingTopicsService.CreatingTopic(topic);
        model.addAttribute("success", "Новая тема успешно добавлена!");
        return "admin/forTopic/create-topic";
    }

    @GetMapping("/admin/table-of-topics-for-editing")
    public String getTableOfTopicsForEditing(Model model, HttpServletRequest request) {

        model.addAttribute("topicsList", creatingAndEditingTopicsService.getAllTopics());
        return "admin/forTopic/table-of-topics-for-editing";
    }

    @GetMapping("/admin/update-topic")
    public String getTopicForUpdate(HttpServletRequest request, Model model) {

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = creatingAndEditingTopicsService.getTopicById(topicId);
        model.addAttribute("topic", topic);
        return "admin/forTopic/update-topic";
    }

    @PostMapping("/admin/update-topic")
    public String updateTopic(Model model, Topic topic, BindingResult result, HttpServletRequest request) {

        creatingAndEditingTopicsService.updateTopic(topic);
        model.addAttribute("success", "Тема успешно изменёна!");
        return "admin/forTopic/update-topic";
    }

    @GetMapping("/admin/delete-topic")
    public ModelAndView deleteTopic(HttpServletRequest request) {

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        creatingAndEditingTopicsService.deleteTopicById(topicId);
        return new ModelAndView("redirect: /admin/table-of-topics-for-editing");
    }

    @GetMapping("/user/forTest/choose-topic")
    public String chooseTopic(Model model) {
        model.addAttribute("topics", topicImpl.getAllTopics());
        return "user/forTest/choose-topic";
    }
}
