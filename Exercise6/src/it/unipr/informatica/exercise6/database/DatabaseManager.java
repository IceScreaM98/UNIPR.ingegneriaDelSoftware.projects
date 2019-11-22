package it.unipr.informatica.exercise6.database;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipr.informatica.exercise6.impl.StudentImpl;
import it.unipr.informatica.exercise6.model.Student;

public class DatabaseManager {
	public DatabaseManager() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	public List<Student> getAllStudents(){
		List<Student> result = new ArrayList<Student>();
		try (Connection connection = DriverManager.getConnection("jdbc:derby://127.0.0.1/Example");
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("select * from STUDENT");		
			){
			while(resultSet.next()) {
				int id = resultSet.getInt("ID");
				String familyName = resultSet.getString("FAMILYNAME");
				String name = resultSet.getString("NAME");
				Student student = new StudentImpl(id, familyName, name);
				result.add(student);
			}
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		return result;
	}
}
