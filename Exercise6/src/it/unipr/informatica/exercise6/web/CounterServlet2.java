package it.unipr.informatica.exercise6.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/counter2")
@SuppressWarnings("serial")
public class CounterServlet2 extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object value = session.getAttribute("counter");
		int counter = 0;
		if (value != null) 
			counter = (int) value;
			else counter = 0;	
		counter++;
		session.setAttribute("counter", counter);
		req.getRequestDispatcher("counter.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
