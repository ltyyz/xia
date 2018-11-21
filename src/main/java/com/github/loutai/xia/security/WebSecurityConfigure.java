package com.github.loutai.xia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/html/**", "/css/**", "/fonts/**", "/img/**", "/js/**", "/easyui/**","/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/needLogin")
                .loginProcessingUrl("/login").defaultSuccessUrl("/html/xia/index.html",true)
                .failureUrl("/html/xia/login.html?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/html/xia/login.html").permitAll()
                .and().csrf().disable();
    }

}
