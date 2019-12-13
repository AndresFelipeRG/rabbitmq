package com.rabbitmqtopics.rabbitmqtopics.tut5;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class Tut5Config {
	@Bean
	public TopicExchange fanout() {
		return new TopicExchange("tut.topic");
	}
	
	@Profile("receiver")
	private static class ReceiverConfig{
		
		@Bean
		public Queue autoDeleteQueue1() {
			return new AnonymousQueue();
		}
		@Bean
		public Queue autoDeleteQueue2() {
			return new AnonymousQueue();
		}	
		@Bean
		public Binding binding1a(TopicExchange direct, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("*.orange.*");
		}
		@Bean
		public Binding binding1b(TopicExchange direct, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("*.*.rabbit");
		}
		@Bean
		public Binding binding2a(TopicExchange direct, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("lazy.#");
		}
	
		@Bean
		public Tut5Receiver receiver() {
			return new Tut5Receiver();
		}
	}
	
	@Profile("sender")
	@Bean
	public Tut5Sender sender() {
		return new Tut5Sender();
	}
}
