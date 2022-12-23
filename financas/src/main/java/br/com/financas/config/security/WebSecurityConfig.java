package br.com.financas.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true )
public class WebSecurityConfig{

	//@Autowired
	//private JwtTokenProvider tokenProvider;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.httpBasic()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests()
		//.antMatchers(HttpMethod.GET, "/categoria/**").permitAll()
		//.antMatchers(HttpMethod.POST, "/categoria/").hasRole("USER")
		//.antMatchers(HttpMethod.DELETE, "/categoria/**").hasRole("ADMIN")
		//.anyRequest().authenticated()
		.antMatchers("/user/authenticate").permitAll()
		.anyRequest().permitAll()
		.and()
		.csrf().disable();
		//http.apply(new JwtTokenConfigurer(tokenProvider));
		return http.build(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .build();
	}
	
}
