package com.testingSystem.controller;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.entity.Role;
import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserStatisticService userStatisticService;
    private RoleDao roleDao;

    @Autowired
    public UserController(UserStatisticService userStatisticService, RoleDao roleDao) {
        this.userStatisticService = userStatisticService;
        this.roleDao = roleDao;
    }

    @GetMapping("/UsersInfo")
    public String showTestStatistic(Model model){
        model.addAttribute("list", userStatisticService.getUserInfoList());
        return "UsersInfo";
    }

    @GetMapping("/CreateUser")
    public String createUser(Model model){
        model.addAttribute("roles",roleDao.getAllRoles());
        return "CreateUser";
    }
}
