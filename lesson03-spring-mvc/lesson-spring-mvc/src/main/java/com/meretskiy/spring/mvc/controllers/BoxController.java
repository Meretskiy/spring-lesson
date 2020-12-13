package com.meretskiy.spring.mvc.controllers;

import com.meretskiy.spring.mvc.model.Box;
import com.meretskiy.spring.mvc.services.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boxes")
public class BoxController {
    private BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping("/all")
    public String getAllBoxes(Model model) {
        model.addAttribute("frontBoxes", boxService.getAllBoxes());
        return "all_boxes";
    }

    @PostMapping("/add")
    public String addNewBox(@ModelAttribute Box box) {
        boxService.save(box);
        return "redirect:/boxes/all";
    }

    @GetMapping("/remove/{id}")
    public String deleteBoxById(@PathVariable Long id) {
        boxService.deleteById(id);
        return "redirect:/boxes/all";
    }


}
