package com.ums.umsbackend;

import com.ums.umsbackend.domains.Duration;
import com.ums.umsbackend.repositories.DurationConfigRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(UMSApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialData(DurationConfigRespository durationConfigRespository){
		return (args) -> {

			durationConfigRespository.save(new Duration(1L, 30));

		};
	}
}
