package com.rabbitmq.rabbitmqworkqueue.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;

@Profile("receiver")
@RabbitListener(queues = "hello")
public class Tut2Receiver {

	private final int instance;
	
	public Tut2Receiver() {
		this.instance = -1;
	}
	
	public Tut2Receiver(int i) {
		this.instance = i;
	}
	
	@RabbitHandler
	public void receive(String in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance + " [x] Received" + in+ "'");
		doWork(in);
		watch.stop();
		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds()+ "s");
	}

	private void doWork(String in) throws InterruptedException {
		for(char ch: in.toCharArray()) {
			if(ch == '.') {
				Thread.sleep(1000);
			}
		}
		
	}

}
