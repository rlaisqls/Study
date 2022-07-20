package com.practice.shoppingmall.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.shoppingmall.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .cors().and()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/coupons").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/items").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/items").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/items/{itemId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/items/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));

    }

}