package ru.roms2002.infotransfer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebConfig {

	@Value("${messenger.ip}")
	private String LOCAL_NETWORK_CIDR;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/mail/**", "/notification/**")
						.access(new WebExpressionAuthorizationManager(
								"hasIpAddress('" + LOCAL_NETWORK_CIDR + "')"))
						.anyRequest().permitAll())
				.build();
	}
}
