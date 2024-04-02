package br.com.ms.f;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FApplication {

	public static void main(String[] args) {
		SpringApplication.run(FApplication.class, args);
	}

}
