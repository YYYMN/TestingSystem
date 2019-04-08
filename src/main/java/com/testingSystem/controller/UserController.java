package com.testingSystem.controller;

import com.testingSystem.model.entity.User;
import com.testingSystem.model.services.CreatingAndEditingUsersService;
import com.testingSystem.model.services.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserStatisticService userStatisticService;
    private CreatingAndEditingUsersService creatingAndEditingUsersService;


    @Autowired
    public UserController(UserStatisticService userStatisticService, CreatingAndEditingUsersService creatingAndEditingUsersService) {
        this.userStatisticService = userStatisticService;
        this.creatingAndEditingUsersService = creatingAndEditingUsersService;
    }

    @GetMapping({"/admin/users-info","/tutor/users-info"})
    public String showTestStatistic(Model model, HttpSession session, HttpServletRequest request){
        model.addAttribute("list", userStatisticService.getUserInfoList());
        String role = (String) session.getAttribute("role");
        return ""+role+"/forStatistic/users-info";
    }

    @GetMapping("/admin/create-user")
    public String createUserPage(Model model, HttpServletRequest request){
        User user = new User();
        user.setRoles( new String[] {} );
        model.addAttribute("user", user);

        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());
        //model.addAttribute("allRoles",addUserService.rolesList());
        return "admin/forUser/create-user";
    }

    @PostMapping("/admin/create-user")
    public String addUser(Model model, User user, BindingResult result, HttpServletRequest request) {

        creatingAndEditingUsersService.CreatingUser(user);
        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());

        model.addAttribute("success","Новый пользователь успешно добавлен!");
        return "admin/forUser/create-user";
    }

    @GetMapping("/admin/table-of-users-for-editing")
    public String getTableOfUsersForEditing(Model model, HttpServletRequest request) {

        model.addAttribute("usersList",creatingAndEditingUsersService.getAllUsers());
        return "admin/forUser/table-of-users-for-editing";
    }

    @GetMapping("/admin/update-user")
    public String getUserForUpdate(HttpServletRequest request, Model model) {

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = creatingAndEditingUsersService.getUserById(userId);

        model.addAttribute("user", user);
        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());

        return "admin/forUser/update-user";
    }

    @PostMapping("/admin/update-user")
    public String updateUser(Model model, User user, BindingResult result, HttpServletRequest request) {

        // Роли можно не ввести, тогда случится Exception!!!    :(
        creatingAndEditingUsersService.updateUser(user);

        model.addAttribute("allRoles", creatingAndEditingUsersService.allRolesList());
        model.addAttribute("success","Пользователь успешно изменён!");
        return "admin/forUser/update-user";
    }

     @GetMapping("/admin/delete-user")
     public ModelAndView deleteUser(HttpServletRequest request) {

        int userId = Integer.parseInt(request.getParameter("userId"));
        creatingAndEditingUsersService.deleteUserByUserId(userId);

        //return "redirect: /TableOfUsersForEditing"  // не помогло
        return new ModelAndView("redirect: /admin/table-of-users-for-editing");
    }
}
