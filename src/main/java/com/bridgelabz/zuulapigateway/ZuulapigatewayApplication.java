package com.bridgelabz.zuulapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.bridgelabz.zuulapigateway.zuulservices.ServiceFilter;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class ZuulapigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulapigatewayApplication.class, args);
	}
	
	@Bean
	public ServiceFilter zuulFilter() {
		return new ServiceFilter();
	}
	
}
