package it.unipr.informatica.exercise6.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/counter")
@SuppressWarnings("serial")
public class CounterServlet extends HttpServlet{
	//private int counter = 0;
	//private static final long serialVersionUID = 1;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		OutputStream outputStream = resp.getOutputStream();
		PrintWriter out = new PrintWriter(outputStream);
		Object value = session.getAttribute("counter");
		int counter;
		if (value != null) 
			counter = (int) value;
		else {
			counter = 0;	
		}
		counter++;
		session.setAttribute("counter", counter);
		//this.counter++;
		
		String payload = "<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<title>Exercise 6</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "<h1>Exercise 6</h1>\n"
				+ "<br>\n"
				+ "<p>Pagina prodotta dal server web (Richiesta " + counter + ")</p>\n"
				+ "</body>\n"
				+ "</html>";
		out.print(payload);
		out.flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
