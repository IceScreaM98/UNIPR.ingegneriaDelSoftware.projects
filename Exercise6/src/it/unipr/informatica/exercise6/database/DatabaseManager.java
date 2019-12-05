package it.unipr.informatica.exercise6.database;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipr.informatica.exercise6.impl.StudentImpl;
import it.unipr.informatica.exercise6.model.Student;

public class DatabaseManager {
	private String databaseLocation = "jdbc:derby://127.0.0.1/Example";
	private String clientDriver = "org.apache.derby.jdbc.ClientDriver";
	public DatabaseManager() {
		try {
			Class.forName(this.clientDriver);
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	public List<Student> getAllStudents() throws SQLException{
		List<Student> result = new ArrayList<Student>();
		try (Connection connection = DriverManager.getConnection(this.databaseLocation);
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
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public void deleteStudent(int id) throws SQLException{
		try (Connection connection = DriverManager.getConnection(this.databaseLocation);
			 PreparedStatement statement = connection.prepareStatement("delete from STUDENT where ID = ?");	
			){
				statement.setInt(1, id);
				statement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}		
	}
	
	public List<Student> getStudents(String familyName, String name) throws SQLException{
		List<Student> result = new ArrayList<Student>();
		try (Connection connection = DriverManager.getConnection(this.databaseLocation);
			 PreparedStatement statement = connection.prepareStatement("select * from STUDENT where FAMILYNAME like ? and NAME like ?");			 		
			){
			if (familyName == null) familyName = "";
			if (name == null) name = "";
			statement.setString(1, "%" + familyName + "%");
			statement.setString(2, "%" + name + "%");
			System.out.println(name);
			try(ResultSet resultSet = statement.executeQuery()){
				while(resultSet.next()) {
					int id = resultSet.getInt("ID");
					familyName = resultSet.getString("FAMILYNAME");
					name = resultSet.getString("NAME");
					Student student = new StudentImpl(id, familyName, name);
					result.add(student);				
				}
				return result;
			}
			catch(SQLException e) {
				e.printStackTrace();
				throw e;
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}	
	}
}
