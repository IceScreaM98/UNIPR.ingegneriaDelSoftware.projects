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

@WebServlet("/add_student")
@SuppressWarnings("serial")
public class AddStudent extends HttpServlet{
	
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
				String familyName = req.getParameter("family_name");
				String name = req.getParameter("name");	
				databaseManager.addStudent(familyName, name);
				req.getRequestDispatcher("getAllStudents").forward(req, resp);
			}
			catch(Throwable t) {
				t.printStackTrace();
				req.getRequestDispatcher("error.html").forward(req, resp);
			}
		}
}
