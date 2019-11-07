package it.unipr.informatica.exercise3;

import it.unipr.informatica.concurrent.Future;


import it.unipr.informatica.concurrent.Callback;

public class MainDownloadManager {
	public static void main(String[] args) {
		DownloadManager downloadManager = new DownloadManager(2);
		Callback<String> callback = new Callback<>() {
			@Override
			public void onResult(String result) {
				System.out.println(result);
			}
			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
			}
		};
		
		downloadManager.download("http://pageoftext.com/", callback);
		downloadManager.download("https://www.google.it", callback);
		downloadManager.download("https://cdl-info.unipr.it", callback);
		
		
		Future<String> result1 = downloadManager.download("http://pageoftext.com/");
		Future<String> result2 = downloadManager.download("http://pageoftext.com/");
		try {
			String text1 = result1.getValue();
			System.out.println("Received (future 1) " + text1.length() + " characters");
		}
		catch(InterruptedException ie) {
			//Blank
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		
		try {
			String text2 = result2.getValue();
			System.out.println("Received (future 2) " + text2.length() + " characters");
		}
		catch(InterruptedException ie) {
			//Blank
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		
	}
}
