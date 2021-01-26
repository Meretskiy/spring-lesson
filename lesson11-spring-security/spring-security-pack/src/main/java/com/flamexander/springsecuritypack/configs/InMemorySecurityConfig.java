package com.flamexander.springsecuritypack.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
    Создаем класс и наследуемся от WebSecurityConfigurerAdapter, это делает наш бин конфигуратором для
    правил безопасности нашего приложения. Над классом ставим аннотацию @EnableWebSecurity
 */
@EnableWebSecurity
@Profile("inmemory")
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(InMemorySecurityConfig.class.getName());

    /*
    Юзеров сохраняем прямо тут
     */
    @Bean
    public UserDetailsService users() {
        //информация о пользователе
        //UserDetails - один из основных интерфейсов спринга, который описывает пользователей
        //имя, пароль, роль
        UserDetails user = User.builder()
                .username("user")
                //пароль в bcrypt
                .password("{bcrypt}$2y$12$BGMFlWNQk8wFpMpei4ixGeaSyntPo1.2LUvxIzCc6rwxXKkiwwHdO")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$BGMFlWNQk8wFpMpei4ixGeaSyntPo1.2LUvxIzCc6rwxXKkiwwHdO")
                .roles("USER", "ADMIN")
                .build();
        //возвращаем InMemoryUserDetailsManager - который хранит связки логин пароль просто в памяти
        return new InMemoryUserDetailsManager(user, admin);
    }

    /*
    Настройка правил безопасности нашего приложения
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("In-Memory Security Configuration");
        //настройка правил, какой уровень должен быть у пользователя, что бы он имел доступ к ресурсам
        http.authorizeRequests()
                //матчеры - указываем эндпоинт относительно которого будет правило, выбираем правило
                //по энпоинту и всему дальнейшему пути (**) все юзаеры должны быть аутентифицированы
                .antMatchers("/auth_page/**").authenticated()
                //по эндпоинту доступ есть только с определенной ролью
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                //во все остальные эндпоинты всех пускаем
                .anyRequest().permitAll()
                .and()
                //позволяем пользователю вбить логин пароль в стандартной спринговой форме
                .formLogin();
    }
}
