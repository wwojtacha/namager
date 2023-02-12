package com.example.namager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class NamagerApplication {

	public static void main(String[] args) {SpringApplication.run(NamagerApplication.class, args);}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
