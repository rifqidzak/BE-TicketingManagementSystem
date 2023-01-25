package com.lawencon.ticketing.application.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.ticketing.application.dto.exception.ExceptionDto;
import com.lawencon.ticketing.application.service.JwtService;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private final List<RequestMatcher> matcher;
	private final ObjectMapper objectMapper;

	public AuthorizationFilter(List<RequestMatcher> matcher, ObjectMapper objectMapper) {
		this.matcher = matcher;
		this.objectMapper = objectMapper;
	}

	@Autowired
	public void setJwtService(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final Long count = matcher.stream().filter((m) -> m.matches(request)).collect(Collectors.counting());
		if (count == 0) {
			final String header = request.getHeader("Authorization");
			final String[] headerExplode = header.split(" ");
			try {
				final Map<String, Object> map = jwtService.parseJwt(headerExplode[1]);
				final GrantedAuthority authority = new SimpleGrantedAuthority(map.get("rc").toString());
				final List<GrantedAuthority> authorities = Arrays.asList(authority);
				final Authentication authentication = new UsernamePasswordAuthenticationToken(map.get("id"),null,authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				final ExceptionDto<String> exceptionDto = new ExceptionDto<>();
				exceptionDto.setMessage("Session Expired");
				response.getWriter().append(objectMapper.writeValueAsString(exceptionDto));
				response.setContentType("application/json");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

}
