package com.nhnacademy.gateway.config;


import com.nhnacademy.gateway.auth.LoginSuccessHandler;
import com.nhnacademy.gateway.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//@EnableWebSecurity(debug = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/projectList").hasAnyAuthority("ROLE_USER") // roleadin은 관리자페이지? 같은거 되게
            .and()
            .csrf().disable()
            .formLogin()
            .usernameParameter("id")
            .passwordParameter("pwd")
            .loginPage("/auth/login")
            .loginProcessingUrl("/login")
            .successHandler(loginSuccessHandler())
//            .successHandler(loginSuccessHandler(null))
            .and()

            .logout()
            .logoutSuccessUrl("/auth/login")
            .and()
            .sessionManagement()
            .sessionFixation()
            .none()
            .and()
            .headers()
            .defaultsDisabled()
            .frameOptions().sameOrigin()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/error/403")
            .and();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(null));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
        CustomUserDetailsServiceImpl customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler(
//        RedisTemplate<String, String> redisTemplate) {
//        return new LoginSuccessHandler(redisTemplate);
//    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}