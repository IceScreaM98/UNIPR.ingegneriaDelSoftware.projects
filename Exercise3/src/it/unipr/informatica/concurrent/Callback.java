package it.unipr.informatica.concurrent;

public interface Callback<T> {
	public void onResult(T result);
	public void onFailure(Throwable throwable);
}
