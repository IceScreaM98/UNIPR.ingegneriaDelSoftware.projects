package it.unipr.informatica.exercise6.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.exercise6.database.DatabaseManager;

@SuppressWarnings("serial")
@WebServlet("/delete_student")
public class DeleteStudent extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			DatabaseManager databaseManager = (DatabaseManager) session.getAttribute("DatabaseManager");
			if (databaseManager == null) {
				databaseManager = new DatabaseManager();
				session.setAttribute("DatabaseManager", databaseManager);
			}
			String idString = req.getParameter("id_delete");
			int id = Integer.parseInt(idString);
			databaseManager.deleteStudent(id);
			req.getRequestDispatcher("getAllStudents").forward(req, resp);
		}
		catch(Throwable t) {
			req.getRequestDispatcher("error.html").forward(req, resp);
		}
		
	}
}
