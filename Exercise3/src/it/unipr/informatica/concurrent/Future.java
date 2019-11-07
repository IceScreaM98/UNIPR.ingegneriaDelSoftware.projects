package it.unipr.informatica.concurrent;

public class Future<T> {
	private T value = null;
	private Throwable throwable = null;
	
	public synchronized void setValue(T value) {
		if (value == null)
			throw new IllegalArgumentException();
		this.value = value;
		this.notifyAll();
	}
	
	public synchronized void setThrowable(Throwable throwable) {
		if (throwable == null)
			throw new IllegalArgumentException();
		this.throwable = throwable;
		this.notifyAll();
	}
	
	public synchronized T getValue() throws InterruptedException{
		while (this.value == null && this.throwable == null)
			this.wait();
		return value;
	}
}
