package com.paymentchain.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAdminServer
@EnableEurekaClient
public class AdminserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminserverApplication.class, args);
	}

	@Configuration
	public static class SecurityPermitAllConfig {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
			// http.authorizeHttpRequests().anyRequest().permitAll().and().csrf().disable();
			http
            .authorizeHttpRequests(
				(a) -> {
					try {
						a.anyRequest().permitAll().and().csrf().disable();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			).httpBasic();
			return http.build();
		}
	}

}
