package it.unipr.informatica.exercise6.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.exercise6.database.DatabaseManager;
import it.unipr.informatica.exercise6.model.Student;

@WebServlet("/search_students")
@SuppressWarnings("serial")
public class SearchStudents extends HttpServlet{
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
			resp.setContentType("text/plain");
			String familyName = req.getParameter("familyName");
			String name = req.getParameter("name");	
			System.out.println(name);
			List<Student> students = databaseManager.getStudents(familyName,name);
			PrintWriter out = new PrintWriter(resp.getOutputStream());
			out.println("{");
			out.println("length : " + students.size() + ", ");
			out.println("students : [");
			for (int i = 0; i < students.size(); i++) {
				//out.print(i + " : " );
				out.println("{");
				Student student = students.get(i);
				out.println("id : '" + student.getId() + "', ");
				out.println("familyName : '" + student.getFamilyName() + "', ");
				out.println("name : '" + student.getName() + "', ");
				out.print("}");
				if (i != students.size() - 1) 
					out.println(",");
				else out.println();
			}
			out.println("]");
			out.println("}");
			out.flush();
		}
		catch(Throwable t) {
			
		}
	}
}
