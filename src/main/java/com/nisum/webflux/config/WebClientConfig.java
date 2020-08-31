package com.nisum.webflux.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	
	@Bean
	@Qualifier("webclient")
	@Scope("prototype")
	public  WebClient.Builder getWebClient() {
		return WebClient.builder();
	}

}
