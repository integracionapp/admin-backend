package com.deliverar.admin.security;

import com.deliverar.admin.filter.CustomAuthenticationFilter;
import com.deliverar.admin.filter.CustomAuthorizationFilter;
import com.deliverar.admin.service.TokenService.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), tokenService, objectMapper);
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.cors().configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("*"));
            configuration.setAllowedHeaders(List.of("*"));
            return configuration;
        });
        http.authorizeRequests()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/login/**", "/token/refresh/**").permitAll()

                //Providers
                .antMatchers(GET, "/providers/**").hasAnyAuthority("ROLE_PROVIDER", "ROLE_ADMIN")
                .antMatchers(POST, "/providers/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(PUT, "/providers/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(DELETE, "/providers/**").hasAnyAuthority("ROLE_ADMIN")

                //Operators
                .antMatchers(GET, "/operators/**").hasAnyAuthority("ROLE_OPERATOR", "ROLE_ADMIN")
                .antMatchers(POST, "/operators/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(PUT, "/operators/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(DELETE, "/operators/**").hasAnyAuthority("ROLE_ADMIN")

                //Franchises
                .antMatchers(GET, "/franchises/**").hasAnyAuthority("ROLE_FRANCHISE", "ROLE_ADMIN")
                .antMatchers(POST, "/franchises/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(PUT, "/franchises/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(DELETE, "/franchises/**").hasAnyAuthority("ROLE_ADMIN")

                //Users
                .antMatchers("/users/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/roles/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        http.requiresChannel().anyRequest().requiresSecure();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(tokenService, objectMapper), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}