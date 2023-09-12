package com.userlocation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
		
		http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(
						(auth) -> auth
						.requestMatchers(HttpMethod.GET, "/get_users/{N}","/all").hasRole("READER")
				        .requestMatchers(HttpMethod.POST, "/create_data").hasRole("ADMIN")
				        .requestMatchers(HttpMethod.PUT, "/update_data/{id}").hasRole("READER").anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin.permitAll());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails user1=User.withUsername("admin").password(encoder.encode("1234")).roles("ADMIN").build();
		UserDetails user2=User.withUsername("reader").password(encoder.encode("1234")).roles("READER").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}

}
