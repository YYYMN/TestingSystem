package com.testingSystem.controller;

import com.testingSystem.model.services.UserProgressGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserGridProgressController {

    private UserProgressGridService userProgressGridService;

    @Autowired
    public UserGridProgressController(UserProgressGridService userProgressGridService) {
        this.userProgressGridService = userProgressGridService;
    }

    @GetMapping({"/admin/table-of-users-for-watching-grid", "/tutor/table-of-users-for-watching-grid"})
    public String getTableOfUsersForWatchingGrid(Model model, HttpSession session, HttpServletRequest request){
        model.addAttribute("usersList", userProgressGridService.getAllUsers());
        String role = (String) session.getAttribute("role");

        return "/"+role+"/forUser/table-of-users-for-watching-grid";
    }


    @GetMapping({"/admin/user-for-watching-grid", "/tutor/user-for-watching-grid"})
    public String getUserForWatchingGrid(HttpServletRequest request, Model model, HttpSession session){

        int userId = Integer.parseInt(request.getParameter("userId"));
        String userLastName = request.getParameter("userLastName");
        String userFirstName = request.getParameter("userFirstName");
        String userProgressGridList = userProgressGridService.getUserProgressGrid(userId);
        String role = (String) session.getAttribute("role");

        model.addAttribute("progressGridList",userProgressGridList);
        model.addAttribute("userLastAndFirstName",userLastName + " " + userFirstName);


        return "/"+role+"/forUser/user-grid-progress";
    }


}
