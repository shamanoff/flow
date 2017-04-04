package com.security_flow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.util.Assert.notNull;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDao;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/registration", "/").permitAll()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/webjars/**", "/img/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index", true)
                .and()
                .csrf()
                .disable()
                .logout().logoutUrl("/logout")
                .and()
                .headers().frameOptions().sameOrigin()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**", "/webjars/**", "/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider());
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDao);
        return provider;
    }


    @Autowired
    public SecurityConfig setUserDao(UserDetailsService userDao) {
        notNull(userDao);
        this.userDao = userDao;
        return this;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}