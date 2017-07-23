package com.ums.umsbackend.configurations;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Created by Naveen on 16/07/2017.
 */
@Configuration
@EnableWebSecurity
public class UMSSecurityConfigurator extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .and()
                .csrf().disable().cors().disable();
    }



}
