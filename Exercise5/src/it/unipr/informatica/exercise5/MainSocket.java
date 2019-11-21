package it.unipr.informatica.exercise5;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * fare richiesta http://127.0.0.1:8080/index.html
 */

public class MainSocket {
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8080);){
			int requests = 0;
			while(true) {
				Socket socket = serverSocket.accept();
				new Worker(socket, requests++).start();
			}
			
		}catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static class Worker extends Thread{
		private Socket socket;
		private int requests;
		
		private Worker(Socket socket, int requests) {
			this.socket = socket;
			this.requests = requests;
		}
		
		@Override
		public void run() {
			try (InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter printWriter = new PrintWriter(outputStream);){
				int c;
				int counter = 0;
				while((c = inputStream.read()) >= 0) {
					System.out.print((char) c);
					if (c == 10) { //Carriage-Return
						if (counter == 1) break;
						else counter = 0;
					}
					else counter++;
				}
				String payload = "<!DOCTYPE html>\n"
								+ "<html>\n"
								+ "<head>\n"
								+ "<title>Exercise 5</title>\n"
								+ "</head>\n"
								+ "<body>\n"
								+ "<h1>Exercise 5</h1>\n"
								+ "<br>\n"
								+ "<p>Pagina prodotta dal server web (Richiesta " + this.requests + ")</p>\n"
								+ "</body>\n"
								+ "</html>";
				printWriter.print("HTTP 1.0 200 OK\n");
				printWriter.print("Content-type: text/html\r\n");
				printWriter.print("Content-length: " + payload.length() + "\r\n");
				printWriter.print("Set-cookie: esempio=valore" + this.requests + "\r\n");
				printWriter.print("\r\n");
				printWriter.print(payload);
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
