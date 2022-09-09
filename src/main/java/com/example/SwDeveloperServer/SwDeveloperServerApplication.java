package com.example.SwDeveloperServer;

import com.example.SwDeveloperServer.domain.user.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SwDeveloperServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwDeveloperServerApplication.class, args);
	}

}
