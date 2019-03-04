package com.testingSystem.controller;


import com.testingSystem.model.daoimpl.TestImpl;
import com.testingSystem.model.daoimpl.TopicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {

    private TopicImpl topicImpl;
    private TestImpl testImpl;

    @Autowired
    public TopicController(TopicImpl topicImpl, TestImpl testImpl) {
        this.topicImpl = topicImpl;
        this.testImpl = testImpl;
    }




    @GetMapping("/EditTopic")
    public String editTopic(Model model){
        model.addAttribute("topics", topicImpl.getAllTopics());
        model.addAttribute("tests", testImpl.getAllTests());
        return "EditTopic";
    }


}
