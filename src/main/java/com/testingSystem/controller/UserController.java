package com.testingSystem.controller;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.services.AddUserService;
import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String createUser(Model model){
        model.addAttribute("rolesList",addUserService.rolesList());
        return "AddUser";
    }
    /*@PostMapping("/SaveUser")
    public String addUser(Model model, @RequestParam(name = "role") String role,
                          @RequestParam(name = "") String role,
                          @RequestParam(name = "role") String role,
                          @RequestParam(name = "role") String role,
                          @RequestParam(name = "role") String role,)
    {
        addUserService.addUser();
        model.addAttribute("success","New user was successfully added!");
        return "AddUser";
    }*/
}
