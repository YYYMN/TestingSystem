package com.testingSystem.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ComponentScan("com.testingSystem")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

/*        http.authorizeRequests().anyRequest().hasAnyRole("Admin","User")
                .and()
                .formLogin()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();*/

/*        http.authorizeRequests()
                .antMatchers("/","/login", "/images/**", "/css/**").permitAll()
                .anyRequest().hasAnyRole("Admin","User")
                .and()
                .formLogin()
               // .loginProcessingUrl("/login") // Submit URL
                .loginPage("/login")
                .permitAll()//
                .defaultSuccessUrl("/Hi")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and()
                .csrf().disable();*/


        http.authorizeRequests()
                .antMatchers("/login", "/images/**", "/css/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("Admin")
                .antMatchers("/tutor/**").hasAnyRole("Tutor")
                .antMatchers("/user/**").hasAnyRole("User")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // .loginProcessingUrl("/login") // Submit URL
                .loginPage("/login")
                .permitAll()//
                .defaultSuccessUrl("/welcome")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and()
                .csrf().disable();


    }

}
