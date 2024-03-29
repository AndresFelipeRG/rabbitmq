package com.rabbitmqrouting.rabbitmqrouting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

public class RabbitAmqpRunner implements CommandLineRunner {

	@Value("${tutorial.client.duration:0}")
	private int duration;
	
	@Autowired
	private ConfigurableApplicationContext ctx;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ready ..running for "+ duration);
		Thread.sleep(duration);
		ctx.close();
	}

}
