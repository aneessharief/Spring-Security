package com.springBasics.basicWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		super.configure(auth);
		
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("0000")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("0000")
			.roles("admin");
			
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		
		.antMatchers("/admin").hasRole("admin")
		.antMatchers("/user").hasRole("user")
		.antMatchers("/").permitAll()
		.and().formLogin();
	}
	
}
