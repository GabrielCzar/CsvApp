package com.persistencia.csv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            .csrf()
                .disable()
			.authorizeRequests()
		        .antMatchers("/", "/bower_components/**", "/js/**", "/css/**")
    		        .permitAll()
                .antMatchers(HttpMethod.POST, "/login")
                    .permitAll()
                .anyRequest()
				    .authenticated()
                .and()
                    .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                            UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JWTAuthenticationFilter(),
                            UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			    .withUser("admin")
                .password("password")
                .roles("ADMIN");
	}

}