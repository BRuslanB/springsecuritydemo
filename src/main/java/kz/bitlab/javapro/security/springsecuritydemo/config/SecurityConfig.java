package kz.bitlab.javapro.security.springsecuritydemo.config;

import kz.bitlab.javapro.security.springsecuritydemo.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, proxyTargetClass = true)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        http.exceptionHandling().accessDeniedPage("/forbidden");
        http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll();

        http.formLogin()
                .loginProcessingUrl("/vhod")                        //<form action = "/vhod" method = "post">
                .usernameParameter("user_email")                    //<input type = "text" name = "user_email">
                .passwordParameter("user_password")                 //<input type = "password" name = "user_password">
                .defaultSuccessUrl("/profile")                      // response.sendRedirect("/profile")
                .failureUrl("/enter?loginerror")   // response.sendRedirect("/enter?error");
                .loginPage("/enter").permitAll();                    // /enter

        http.logout()
                .logoutUrl("/vyhod")                    //<form action = "/vyhod" method = "post">
                .logoutSuccessUrl("/enter");            // response.sendRedirect("/enter");

        http.csrf().disable(); //запрет на post запросы

        return http.build();
    }
}