package com.example.individualpr.Controllers;

import com.example.individualpr.RoleChek;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

    @GetMapping("/Header")
    public String Main(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            model.addAttribute("AuthUser",((UserDetails)principal).getUsername());
        } else {
            model.addAttribute("NotAuth", principal.toString());
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        model.addAttribute("isAuth", userDetails.getUsername());
        model.addAttribute("isUser", new RoleChek().userCheck(auth));
        model.addAttribute("isAdmin", new RoleChek().adminCheck(auth));
        model.addAttribute("isEmployee", new RoleChek().employeeCheck(auth));

        return "Blocks/Header";

    }

}