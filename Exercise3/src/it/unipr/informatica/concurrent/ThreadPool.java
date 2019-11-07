package it.unipr.informatica.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
	private Thread[] pool;
	private List<Task> queue;
	
	public ThreadPool(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		this.queue = new LinkedList<>();
		this.pool = new Thread[size];
		for (int i = 0; i < size; i++) {
			this.pool[i] = new Worker();
			this.pool[i].start();
		}
	}
	
	public synchronized void add(Task task) {
		this.queue.add(task);
		this.notifyAll();
	}
	
	public synchronized void stop() {
		this.add(new StopTask());
	}
	
	private class Worker extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					Task task = null;
					Object semaphore = ThreadPool.this;
					synchronized (semaphore) {
						while (queue.isEmpty())
							semaphore.wait();
						task = ThreadPool.this.queue.get(0);
						if (task instanceof StopTask) return;
						task = ThreadPool.this.queue.remove(0);
					}
					task.run();	
				}
				catch(InterruptedException ie) { //Chiusura JVM
					//Blank
				}
				catch(Throwable t) { //Cattura tutte le eccezioni
					t.printStackTrace();
				}

			}
		}
	}
	
	private class StopTask implements Task{
		@Override
		public void run() {
			//Blank
		}
	}

}
