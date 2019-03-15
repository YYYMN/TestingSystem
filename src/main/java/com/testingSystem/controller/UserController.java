package com.testingSystem.controller;

import com.testingSystem.model.entity.User;
import com.testingSystem.model.services.AddUserService;
import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserStatisticService userStatisticService;
    private AddUserService addUserService;

    @Autowired
    public UserController(UserStatisticService userStatisticService,AddUserService addUserService) {
        this.userStatisticService = userStatisticService;
        this.addUserService = addUserService;
    }

    @GetMapping("/UsersInfo")
    public String showTestStatistic(Model model){
        model.addAttribute("list", userStatisticService.getUserInfoList());
        return "UsersInfo";
    }

    @GetMapping("/AddUser")
    public String createUserPage(Model model){
        User user = new User();
        user.setRoles( new String[] {} );
        model.addAttribute("user", user);

        model.addAttribute("allRoles",addUserService.allRolesList());
        //model.addAttribute("allRoles",addUserService.rolesList());
        return "AddUser";
    }



    @PostMapping("/AddUser")
    public String addUser(Model model, User user, BindingResult result) {

        addUserService.addUser(user);
        user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRoles",addUserService.allRolesList());

        model.addAttribute("success","Новый пользователь успешно добавлен!");
        return "AddUser";
    }
}
