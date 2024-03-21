package com.PRJ321x_namtqFX20225.asm3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(configurer -> configurer
				.requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("USER")
				.requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("USER")
				.requestMatchers(HttpMethod.GET, "/api/doctor/**").hasRole("DOCTOR")
				.requestMatchers(HttpMethod.PATCH, "/api/doctor/**").hasRole("DOCTOR")
				.requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/api/admin/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/forgotPassword/**").permitAll()
				.anyRequest().authenticated()
		);
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.csrf(csrf -> csrf.disable());
		return httpSecurity.build();
	}
}
