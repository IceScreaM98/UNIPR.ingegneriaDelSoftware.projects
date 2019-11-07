package it.unipr.informatica.exercise3;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import it.unipr.informatica.concurrent.Future;

import it.unipr.informatica.concurrent.Active;
import it.unipr.informatica.concurrent.Callback;

public class DownloadManager extends Active{
	public DownloadManager(int size) {
		super(size);
	}
	
	public void download(String url, Callback<String> callback) {
		/*
		this.add(new Task() {
			@Override
			public void run() {
				DownloadManager.this.downloadSync(url);
			}
		});
		*/
		this.add(() -> {
			try {
				String result = this.downloadSync(url);
				callback.onResult(result);
			}
			catch(Throwable t) {
				callback.onFailure(t);
			}
			
		});
		
	}
	
	public Future<String> download(String url){
		Future<String> result = new Future<>();
		this.add(() ->{
			try {
				String text = this.downloadSync(url);
				result.setValue(text);
			}
			catch(Throwable t) {
				result.setThrowable(t);
			}
		});
		
		return result;
		
	}
	
	public String downloadSync(String url) throws IOException{
		/*
		System.out.println("Download started from: " + url);
		try {
			Thread.sleep(5000);
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		System.out.println("Download terminated from: " + url);
		*/
		URL u = new URL(url);
		InputStream is = u.openStream(); //u.openConnection().getInputStream()
		byte[] buffer = new byte[4096];
		String result = "";
		int counter = is.read();
		while (counter >= 0) {
			result += new String(buffer, 0, counter);
			counter = is.read();
		}
		System.out.println(result);
		return result;
	}
}
