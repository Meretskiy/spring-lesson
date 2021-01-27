package com.meretskiy.spring.security.project.controllers;

import com.meretskiy.spring.security.project.services.ScoreService;
import com.meretskiy.spring.security.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DaoController {
    private final UserService userService;
    private final ScoreService scoreService;

    @GetMapping("score/get/{id}")
    public String findScoresByUserId(@PathVariable Long id) {
        return "User id " + id + "score= " + scoreService.findScoresByUserId(id);
        //TODO падает со StackOverflowError
    }
}
