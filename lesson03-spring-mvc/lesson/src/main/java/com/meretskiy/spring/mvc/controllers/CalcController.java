package com.meretskiy.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalcController {
    @GetMapping("/calc_form")
    public String calculate() {
        return "calc_form";
    }

    @GetMapping("/calc_result")
    @ResponseBody
    public Integer sum(@RequestParam(name = "var_a") Integer a, @RequestParam(name = "var_b") Integer b) {
        return a + b;
    }

}
