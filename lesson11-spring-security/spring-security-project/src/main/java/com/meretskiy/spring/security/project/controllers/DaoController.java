package com.meretskiy.spring.security.project.controllers;

import com.meretskiy.spring.security.project.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DaoController {
    private final ScoreService scoreService;

    @GetMapping("score/get/{id}")
    public String findScoresByUserId(@PathVariable Long id) {
        return "User id " + id + " score= " + scoreService.findQuantityByUserId(id);
    }

    @GetMapping("score/inc")
    public String updateQuantityInc(Principal principal) {
        int quantity = scoreService.updateQuantityInc(principal);
        return "quantity inc successes, new quantity = " + quantity;
    }

    @GetMapping("score/dec")
    public String updateQuantityDec(Principal principal) {
        int quantity = scoreService.updateQuantityDec(principal);
        return "quantity dec successes, new quantity = " + quantity;
    }

    @GetMapping("score/get/current")
    public String findCurrentScore(Principal principal) {
        return "Score = " + scoreService.getCurrentScore(principal);
    }
}
