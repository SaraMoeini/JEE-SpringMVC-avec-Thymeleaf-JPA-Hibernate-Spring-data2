package org.sid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityCongig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = 
		          PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth.inMemoryAuthentication().withUser("admin").password("{noop}12345").roles("USER","ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
                // {noop} for plain text 
		auth
        .inMemoryAuthentication()
        .withUser("root")
        .password(encoder.encode("root"))
        .roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	//strategie de securite / des regles
		//super.configure(http);
		//http.formLogin().loginPage("/login");
		
		http.authorizeRequests().antMatchers("/login").permitAll()
								.antMatchers("/operations","/consulterCompte").access("hasRole('USER')")
								.antMatchers("/saveOperation").access("hasRole('ADMIN')")
								.and()
								.formLogin();
		//http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
		http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
		http.csrf().disable().formLogin();
		http.exceptionHandling().accessDeniedPage("/403");

	}
}
