package com.itibo.project.world_of_tests.config;

import com.itibo.project.world_of_tests.filters.StatelessAuthenticationFilter;
import com.itibo.project.world_of_tests.filters.StatelessLoginFilter;
import com.itibo.project.world_of_tests.security.TokenAuthenticationService;
import com.itibo.project.world_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public SecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.exceptionHandling()
            .and().anonymous()
            .and().servletApi()
            .and().headers().cacheControl();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/posts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/quizzes/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/quizzes/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/users/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/uploads/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/uploads/**").permitAll();

        http.addFilterBefore(
                new StatelessLoginFilter("/api/login", tokenAuthenticationService, userService, authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(
                new StatelessAuthenticationFilter(tokenAuthenticationService),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }
}