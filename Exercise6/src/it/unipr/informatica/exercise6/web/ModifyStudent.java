package it.unipr.informatica.exercise6.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.exercise6.database.DatabaseManager;

@WebServlet("/modify_student")
@SuppressWarnings("serial")
public class ModifyStudent extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			DatabaseManager databaseManager = (DatabaseManager) session.getAttribute("DatabaseManager");
			if (databaseManager == null) {
				databaseManager = new DatabaseManager();
				session.setAttribute("DatabaseManager", databaseManager);
			}
			int id = Integer.parseInt(request.getParameter("id"));
			String familyName = request.getParameter("family_name");
			String name = request.getParameter("name");
			databaseManager.modifyStudent(id, familyName, name);
			request.getRequestDispatcher("getAllStudents").forward(request, response);
		}
		catch(Throwable t) {
			request.getRequestDispatcher("error.html").forward(request, response);
			t.printStackTrace();
		}
	}

}
