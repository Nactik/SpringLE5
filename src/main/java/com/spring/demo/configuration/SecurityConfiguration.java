package com.spring.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("demo").password("demo").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("admin").build());
        return manager;
    }

    public void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/robots.txt").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/mock/**").permitAll()
                .antMatchers("/h2/**").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html").hasRole("Admin")
                .antMatchers(HttpMethod.POST,"/teachers/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/teachers/{\\d+}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll().defaultSuccessUrl("/teachers")
                .and()
                .logout().permitAll();

        //h2
        http.headers().frameOptions().disable().and().csrf().disable();
    }
}