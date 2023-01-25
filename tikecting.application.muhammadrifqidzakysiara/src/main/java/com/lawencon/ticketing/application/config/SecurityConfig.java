package com.lawencon.ticketing.application.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lawencon.ticketing.application.filter.AuthorizationFilter;
import com.lawencon.ticketing.application.service.UsersService;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

@Configuration
public class SecurityConfig {
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UsersService userService) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService).passwordEncoder(passwordEncoder).and().build();
	}
	
	@Bean
	public List<RequestMatcher> Matchers(){
	
		final List<RequestMatcher> requestMatchers = new ArrayList<>();
		requestMatchers.add(new AntPathRequestMatcher("/files/**",HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/swagger-ui/**",HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/v3/**",HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/login/**",HttpMethod.POST.name()));
		return requestMatchers;
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web)->Matchers().forEach((r)-> web.ignoring().requestMatchers(r));
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
		http.cors();
		http.csrf().disable();
		http.addFilterAt(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString(), HttpMethod.PUT.toString(), HttpMethod.PATCH.toString(), HttpMethod.DELETE.toString());
				WebMvcConfigurer.super.addCorsMappings(registry);
			}
		};
		
	}
}
