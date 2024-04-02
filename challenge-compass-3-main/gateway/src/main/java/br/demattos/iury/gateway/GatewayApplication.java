package br.demattos.iury.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
						.routes()
						.route(r->r.path("/api/v1/employees/**").uri("lb://ms-employee"))
						.route(r->r.path("/api/v1/proposals/**").uri("lb://ms-proposal"))
						.route(r->r.path("/api/v1/votes/**").uri("lb://ms-proposal"))
						.route(r->r.path("/api/v1/voting/**").uri("lb://ms-voting"))
						.build();
	}

}
