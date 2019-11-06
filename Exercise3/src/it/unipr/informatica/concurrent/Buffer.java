package it.unipr.informatica.concurrent;


import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> queue = new LinkedList<>();
	
	public synchronized int size() {
		return queue.size();
	}
	
	public synchronized void add(T value) {
		this.queue.addLast(value);
		this.notifyAll();
	}
	
	public synchronized T remove() throws InterruptedException{
		while(this.queue.isEmpty()){
			this.wait();
		}
		return this.queue.removeFirst();
	}
	
}
