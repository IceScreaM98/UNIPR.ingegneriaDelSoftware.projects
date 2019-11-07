package it.unipr.informatica.concurrent;

public abstract class Active {
	private ThreadPool pool;
	
	protected Active(int size) {
		this.pool = new ThreadPool(size);
	}
	
	protected void add(Task task) {
		this.pool.add(task);
	}
	
	protected void stop() {
		this.pool.stop();
	}
}
