package com.rabbitmq.rabbitmqproject.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("sender")
public class Tut1Sender {

		@Autowired
		private RabbitTemplate template;
		
		@Autowired
		private Queue queue;
		
		@Scheduled(fixedDelay = 500, initialDelay = 300)
		public void send() {
			String message = "Hello World!";
			this.template.convertAndSend(queue.getName(), message);
			System.out.println(" [x] Sent '" + message + "'");
		}
}
