package com.rabbitmq.rabbitmqworkqueue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqworkqueueApplication {
	
	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behavior.\n");
	            System.out.println("Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=hello-world,sender");
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitmqworkqueueApplication.class, args);
	}
	
	@Profile("n_usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpRunner();
	}
	
	

}