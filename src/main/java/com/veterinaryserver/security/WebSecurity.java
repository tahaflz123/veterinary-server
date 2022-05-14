package com.veterinaryserver.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.veterinaryserver.service.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final Logger logger = LoggerFactory.getLogger(WebSecurity.class);

	@Bean
	public AuthenticationService authenticationService() {
		logger.info("authentication service bean");
		return new AuthenticationService();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		logger.info("daoAuthenticationProvider bean");
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(authenticationService());
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("configure");
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		   .antMatchers("/api/user/register").permitAll()
		   .antMatchers("/api/user/login").permitAll()
		   .antMatchers("/api/user/profile").hasAnyAuthority("ADMIN", "USER")
		   .antMatchers("/api/user/admin/**").hasAuthority("ADMIN")
		   .antMatchers("/api/animal/create").hasAnyAuthority("USER", "ADMIN")
		   .antMatchers("/api/animal/admin/**").hasAuthority("ADMIN")
		   .antMatchers("/api/animal/update/**").hasAnyAuthority("ADMIN", "USER")
		   .and()
	    .formLogin()
	       .loginPage("/api/user/login")
	       .defaultSuccessUrl("/api/animal/all")
	    .and()
	       .logout()
	       .logoutSuccessUrl("/api/user/login");
		logger.info("configure http");
	}
	
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
