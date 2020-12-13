package com.meretskiy.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
    @GetMapping("/first")
    public String showFirstPage(Model model, @RequestParam(defaultValue = "Stranger") String username) {
        model.addAttribute("username", username);
        return "first_page";
    }
}
