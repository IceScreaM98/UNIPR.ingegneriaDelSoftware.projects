package it.unipr.informatica.exercise3;

import it.unipr.informatica.concurrent.Buffer;

public class MainBuffer {
	
	public static void main(String[] args) {
		Buffer<Integer> buffer = new Buffer<>();
		for (int i = 0; i < 3; i++) {
			final int id = i;
			Thread t = new Thread(() -> {
				while(true) {
					try {
						int value = buffer.remove();
						System.out.println("Value [Thread id=" + id + "]: " + value + " with buffer size " + buffer.size());	
					}
					catch(InterruptedException ie) {
						//Blank
					}					
				}

			});
			t.start();
		}
		System.out.println("Readers created");
		try {
			Thread.sleep(5000);
			
		}
		catch(InterruptedException ie) {
			//Blank
		}
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(() ->  {
				for (int j = 0; j < 50; j++) {
					buffer.add(j);
				}
			});
			t.start();
		}
		try {
			Thread.sleep(10000);
			
		}
		catch(InterruptedException ie) {
			//Blank
		}
		System.out.println("Terminated");
		System.exit(0);
	}
}
