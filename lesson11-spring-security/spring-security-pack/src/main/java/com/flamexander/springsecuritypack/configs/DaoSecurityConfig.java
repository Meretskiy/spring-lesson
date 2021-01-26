package com.flamexander.springsecuritypack.configs;

import com.flamexander.springsecuritypack.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity(debug = true)
@Profile("dao")
@RequiredArgsConstructor
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Dao Authentication Provider");
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/dao").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    //так как сейчас за наших юзеров отвечает наш userDetails, то нам необходимо создать PasswordEncoder,
    //который будет перегонять пароль от клиента в чистом виде и преобразовывать его в тот вид в котором он
    //лежит в базе.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //DaoAuthenticationProvider связывает в единое целое все наши конфигурации.
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}