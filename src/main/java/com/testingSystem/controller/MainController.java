package com.testingSystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;


@Controller
public class MainController {

    @GetMapping({"/", "/welcome"})
    public ModelAndView redirectFunction(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> collection = auth.getAuthorities();
        String roles = collection.toString();
        if (roles.equals("[ROLE_Admin]")){
            return new ModelAndView("redirect: /admin/admin-main-page ");
        }
        else if(roles.equals("[ROLE_Tutor]")) {
            return new ModelAndView("redirect: /tutor/tutor-main-page");
        }
        else if(roles.equals("[ROLE_User]")) {
            return new ModelAndView("redirect: /user/user-main-page");
        }
        else {return new ModelAndView("redirect: /welcome");}
    }

    @GetMapping("/admin/admin-main-page")
    public String admin() {
        return "admin/admin-main-page";
    }

}
