package com.testingSystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.stream.Stream;


@Controller
public class MainController {

    @GetMapping({"/", "/welcome"})
    public ModelAndView redirectFunction(HttpServletRequest request){
        HttpSession session;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> collection = auth.getAuthorities();
        String roles = collection.toString();

        if (roles.equals("[ROLE_Admin]")){
            session = request.getSession();
            session.setAttribute("role", "admin");
            return new ModelAndView("redirect: /admin/admin-main-page ");
        }
        else if(roles.equals("[ROLE_Tutor]")) {
            session = request.getSession();
            session.setAttribute("role", "tutor");
            return new ModelAndView("redirect: /tutor/tutor-main-page");
        }
        else if(roles.equals("[ROLE_User]")) {
            session = request.getSession();
            session.setAttribute("role", "user");
            return new ModelAndView("redirect: /user/user-main-page");
        }
        else {return new ModelAndView("redirect: /welcome");}

    }

    @GetMapping("/admin/admin-main-page")
    public String admin(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("username", name);
        return "admin/admin-main-page";
    }

    @GetMapping("/tutor/tutor-main-page")
    public String tutor(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("username", name);
        return "tutor/tutor-main-page";
    }

}
