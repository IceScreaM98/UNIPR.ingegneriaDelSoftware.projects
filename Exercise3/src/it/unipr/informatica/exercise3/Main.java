package it.unipr.informatica.exercise3;

public class Main {
	/* metodo 1 con classe
	private static class Counter implements Runnable{   //extends Thread
		private int id;
		
		private Counter(int id) {
			this.id = id;
		}
		@Override
		public void run() {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Counter " + this.id + ": " + i);
				try {
					Thread.sleep(2000);
				}
				catch(InterruptedException ie) {
					//Blank
				}
			}
		}
	}
		
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Counter c = new Counter(i);
			Thread t = new Thread(c);     //c.start() se estendo la classe Thread
			t.start();
		}
	}
	*/
	public static void main(String[] args) {
		SharedInteger[] a = new SharedInteger[10];
		for (int i = 0; i < 10; i++) {
			final SharedInteger s = new SharedInteger();
			final int id = i;
			a[i] = s;
			Thread t = new Thread(() -> {
				/*   //senza SharedInteger
				for (int j = 1; j <= 10; j++) {
					System.out.println("Counter " + id + ": " + j);
					try {
						Thread.sleep(2000);
					}
					catch(InterruptedException ie) {
						//Blank
					}
				}
				*/
				try {
					int value = s.get();
					System.out.println("Value [Thread id=" + id + "]: " + value);	
					System.out.println(Thread.currentThread().getName());
				}
				catch(InterruptedException ie) {
					//Blank
				}
			},"Thread " + i);
			t.start();
		}
		System.out.println("Threads created");
		try {
			Thread.sleep(2000);
			
		}
		catch(InterruptedException ie) {
			//Blank
		}
		for (int i = 0; i < 10; i++) {
			a[i].set(100 + i);
		}
	}
	
	private static class SharedInteger{
		private int value;
		private boolean iniitialized;
		
		public SharedInteger() {
			this.value = 0;
			this.iniitialized = false;
		}
		
		public synchronized void set(int value) {
			this.value = value;
			this.iniitialized = true;
			this.notifyAll();
		}
		
		public final synchronized int get() throws InterruptedException{
			//synchronized(this){...} se voglio sincronizzare dei pezzi di codice
			while(!this.iniitialized) {
				this.wait();
			}
			return this.value;
		}
	}
	
}
