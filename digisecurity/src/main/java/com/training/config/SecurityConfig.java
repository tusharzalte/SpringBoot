package com.training.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests ->
		requests.requestMatchers("/").permitAll() // localhost:9080
		.requestMatchers("/index").hasAnyRole("USER", "ADMIN")
		.requestMatchers("/viewAllProducts").hasAnyRole("USER", "ADMIN")
		.requestMatchers("/addProduct").hasAnyRole("ADMIN")
		.requestMatchers("/hello").hasAnyRole("TRAINEE")
		.requestMatchers("/deleteProduct").hasAnyRole("ADMIN")
		.anyRequest()
				.authenticated())
		.formLogin(login -> login.loginPage("/login").permitAll())
				.logout(logout -> logout.permitAll().logoutSuccessUrl("/logoutURL")).exceptionHandling().accessDeniedPage("/accessDenied");
		// http.csrf().disable();
		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception
	{
		authenticationMgr.inMemoryAuthentication()
		.withUser("neha").password("tech").authorities("ROLE_USER")
		.and()
		.withUser("tufail").password("ahmed").authorities("ROLE_USER", "ROLE_ADMIN")
		.and().
		withUser("admin").password("admin").authorities("ROLE_ADMIN")
		.and().
		withUser("dev").password("dev123").authorities("ROLE_TRAINEE");

	}
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(datasource);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();		//{noop}
	}
}
