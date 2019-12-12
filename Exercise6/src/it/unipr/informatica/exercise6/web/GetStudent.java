package it.unipr.informatica.exercise6.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.exercise6.database.DatabaseManager;

@WebServlet("/get_student")
@SuppressWarnings("serial")
public class GetStudent extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("id_modify"));
			String familyName = request.getParameter("family_name_modify");
			String name = request.getParameter("name_modify");	
			session.setAttribute("id", id);
			session.setAttribute("family_name", familyName);
			session.setAttribute("name", name);
			request.getRequestDispatcher("modify_student.jsp").forward(request, response);
		}
		catch(Throwable t) {
			t.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}

}
