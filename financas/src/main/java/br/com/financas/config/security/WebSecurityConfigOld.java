package br.com.financas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@SuppressWarnings(value = { "deprecation" })
public class WebSecurityConfigOld extends WebSecurityConfigurerAdapter{

	
	final UserDetailsServiceImpl userDetailsService;
	
	public WebSecurityConfigOld(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.GET, "/categoria/**").permitAll()
			.antMatchers(HttpMethod.POST, "/categoria/").hasRole("USER")
			.antMatchers(HttpMethod.DELETE, "/categoria/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.csrf().disable();
	}
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("teste")
			.password(passwordEncoder().encode("teste"))
			.roles("ADMIN");
	}
	*/
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
