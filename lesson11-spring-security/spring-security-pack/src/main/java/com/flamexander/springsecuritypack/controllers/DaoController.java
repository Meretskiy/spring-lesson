package com.flamexander.springsecuritypack.controllers;

import com.flamexander.springsecuritypack.entities.User;
import com.flamexander.springsecuritypack.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
public class DaoController {
    private final UserService userService;

    //после аутентификации, когда наш юзер попадает в спрингКонтекст и с юзером начинает ассоциироваться JSessionID
    //мы можем заинжектить в любой метод нашего контроллера Principal
    //Principal - это минимальная информация о юзере
    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        // Authentication a = SecurityContextHolder.getContext().getAuthentication();
        //из principal достаем имя и с помощью сервера достаем настоящего пользователя, а там уже берем емайл
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }
}
