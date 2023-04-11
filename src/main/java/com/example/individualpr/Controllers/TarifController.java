package com.example.individualpr.Controllers;

import com.example.individualpr.Models.*;
import com.example.individualpr.Repos.*;
import com.example.individualpr.RoleChek;
import com.example.individualpr.Service.ReportExcelExporter;
import com.example.individualpr.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/tariff")
public class TarifController {
    @Autowired
    private PersonalAccountRepos presonalAccountRepos;

    @Autowired
    private ClientRepos clientRepos;

    @Autowired
    private RateRepos rateRepos;

    @GetMapping
    public String tarifList(Model model){
        model.addAttribute("personalAcc", presonalAccountRepos.findAll());
        model.addAttribute("client", clientRepos.findAll());
        model.addAttribute("rate", rateRepos.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        model.addAttribute("isAuth", userDetails.getUsername());
        model.addAttribute("isUser", new RoleChek().userCheck(auth));
        model.addAttribute("isAdmin", new RoleChek().adminCheck(auth));
        model.addAttribute("isEmployee", new RoleChek().employeeCheck(auth));
        return "tarif/main";
    }

    @GetMapping("/edit/{personalAccount}")
    public String tarifEdit(PersonalAccount personalAccount, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        model.addAttribute("isAuth", userDetails.getUsername());
        model.addAttribute("isUser", new RoleChek().userCheck(auth));
        model.addAttribute("isAdmin", new RoleChek().adminCheck(auth));
        model.addAttribute("isEmployee", new RoleChek().employeeCheck(auth));
        List<Client> clients = clientRepos.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("rates", rateRepos.findAll());
        return "tarif/main";
    }

    @PostMapping("/edit/{personalAccount}")
    public String tarifPostEdit(
            @ModelAttribute("personalAccount") @Valid PersonalAccount personalAccount,
            BindingResult bindingResult,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        model.addAttribute("isAuth", userDetails.getUsername());
        model.addAttribute("isUser", new RoleChek().userCheck(auth));
        model.addAttribute("isAdmin", new RoleChek().adminCheck(auth));
        model.addAttribute("isEmployee", new RoleChek().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            model.addAttribute("clients", clientRepos.findAll());
            model.addAttribute("rates", rateRepos.findAll());
            return "tarif/main";
        }

        presonalAccountRepos.save(personalAccount);
        return "redirect:/personalAccount";
    }
}
