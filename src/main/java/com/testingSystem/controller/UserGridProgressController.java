package com.testingSystem.controller;

import com.testingSystem.model.services.UserProgressGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserGridProgressController {

    private UserProgressGridService userProgressGridService;

    @Autowired
    public UserGridProgressController(UserProgressGridService userProgressGridService) {
        this.userProgressGridService = userProgressGridService;
    }

    @GetMapping("/TableOfUsersForWatchingGrid")
    public String getTableOfUsersForWatchingGrid(Model model){
        model.addAttribute("usersList", userProgressGridService.getAllUsers());
        return "TableOfUsersForWatchingGrid";
    }

    @GetMapping("/UserForWatchingGrid")
    public String getUserForWatchingGrid(HttpServletRequest request, Model model){

        int userId = Integer.parseInt(request.getParameter("userId"));
        String userLastName = request.getParameter("userLastName");
        String userFirstName = request.getParameter("userFirstName");
        List<UserProgressGridService.UserGrid> userProgressGridList = userProgressGridService.getUserProgressGrid(userId);

        model.addAttribute("progressGridList",userProgressGridList);
        model.addAttribute("userLastAndFirstName",userLastName + " " + userFirstName);

        return "UserGridProgress";
    }
}
