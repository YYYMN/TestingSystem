package com.testingSystem.controller;

import com.testingSystem.model.entity.Role;
import com.testingSystem.model.entity.User;
import com.testingSystem.model.services.CreatingAndEditingUsersService;
import com.testingSystem.model.services.UserProgressGridService;
import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private UserStatisticService userStatisticService;
    private CreatingAndEditingUsersService creatingAndEditingUsersService;


    @Autowired
    public UserController(UserStatisticService userStatisticService, CreatingAndEditingUsersService creatingAndEditingUsersService) {
        this.userStatisticService = userStatisticService;
        this.creatingAndEditingUsersService = creatingAndEditingUsersService;
    }

    @GetMapping("/UsersInfo")
    public String showTestStatistic(Model model){
        model.addAttribute("list", userStatisticService.getUserInfoList());
        return "UsersInfo";
    }

    @GetMapping("/CreateUser")
    public String createUserPage(Model model){
        User user = new User();
        user.setRoles( new String[] {} );
        model.addAttribute("user", user);

        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());
        //model.addAttribute("allRoles",addUserService.rolesList());
        return "CreateUser";
    }

    @PostMapping("/CreateUser")
    public String addUser(Model model, User user, BindingResult result) {

        creatingAndEditingUsersService.CreatingUser(user);
        user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());

        model.addAttribute("success","Новый пользователь успешно добавлен!");
        return "CreateUser";
    }

    @GetMapping("/TableOfUsersForEditing")
    public String getTableOfUsersForEditing(Model model) {

        model.addAttribute("usersList",creatingAndEditingUsersService.getAllUsers());
        return "TableOfUsersForEditing";
    }

    @GetMapping("/getUserForUpdate")
    public String getUserForUpdate(HttpServletRequest request, Model model) {

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = creatingAndEditingUsersService.getUserById(userId);

        model.addAttribute("user", user);
        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());

        return "UpdateUser";
    }

    @PostMapping("/UpdateUser")
    public String updateUser(Model model, User user, BindingResult result) {

        // Роли можно не ввести, тогда случится Exception!!!    :(
        creatingAndEditingUsersService.updateUser(user);

        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());
        model.addAttribute("success","Пользователь успешно изменён!");
        return "UpdateUser";
    }

    @GetMapping("/DeleteUser")
    public ModelAndView deleteUser(HttpServletRequest request) {

        System.out.println(request.getParameter("userId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        creatingAndEditingUsersService.deleteUserByUserId(userId);

        //return "redirect: /TableOfUsersForEditing"  // не помогло
        return new ModelAndView("redirect: /TableOfUsersForEditing");
    }

}
