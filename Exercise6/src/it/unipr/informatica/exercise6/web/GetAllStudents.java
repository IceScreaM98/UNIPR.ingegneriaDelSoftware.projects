package it.unipr.informatica.exercise6.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.exercise6.database.DatabaseManager;
import it.unipr.informatica.exercise6.model.Student;

@WebServlet("/getAllStudents")
@SuppressWarnings("serial")
public class GetAllStudents extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			DatabaseManager databaseManager = (DatabaseManager) session.getAttribute("DatabaseManager");
			if (databaseManager == null) {
				databaseManager = new DatabaseManager();
				session.setAttribute("DatabaseManager", databaseManager);
			}
			List<Student> allStudents = databaseManager.getAllStudents();
			session.setAttribute("allStudents", allStudents);
			req.getRequestDispatcher("get_all_students.jsp").forward(req, resp);
		}
		catch(Throwable t) {
			req.getRequestDispatcher("error.html").forward(req, resp);
		}
	}
}
