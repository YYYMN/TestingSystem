package com.testingSystem.controller;

import com.testingSystem.model.services.AddUserService;
import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("rolesList",addUserService.rolesList());
        return "AddUser";
    }
    @PostMapping("/SaveUser")
    public String addUser(Model model, @ModelAttribute("role") String role,
                          @ModelAttribute("lastName") String lastName,
                          @RequestParam("firstName") String firstName,
                          @RequestParam("password") String password,
                          @RequestParam("login") String login,
                          @RequestParam("email") String email)
    {
        addUserService.addUser(role,lastName,firstName,login,password,email);
        model.addAttribute("success","New user was successfully added!");
        model.addAttribute("rolesList",addUserService.rolesList());
        return "AddUser";
    }
}
