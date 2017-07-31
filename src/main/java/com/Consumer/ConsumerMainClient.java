package com.Consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerMainClient {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		ConsumerThread thread;
		for(int i=1; i<=5; i++)
		{
			service.submit(new ConsumerThread());
		}
		
		service.shutdown();

	}

}
