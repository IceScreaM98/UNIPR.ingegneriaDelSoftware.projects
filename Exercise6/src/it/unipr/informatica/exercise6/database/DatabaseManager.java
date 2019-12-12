package it.unipr.informatica.exercise6.database;

import java.util.List;
import java.util.ResourceBundle;
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
	private String databaseLocation;
	private String clientDriver;
	public DatabaseManager() {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("it/unipr/informatica/exercise6/configuration");
			this.clientDriver = bundle.getString("database.driver");
			this.databaseLocation = bundle.getString("database.url");
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
			 ResultSet resultSet = statement.executeQuery("select * from STUDENT order by ID");		
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
			 PreparedStatement statement = connection.prepareStatement("select * from STUDENT where FAMILYNAME like ? and NAME like ? order by ID");			 		
			){
			if (familyName == null) familyName = "";
			if (name == null) name = "";
			familyName = this.removeScriptContent(familyName);
			name = this.removeScriptContent(name);	
			statement.setString(1, "%" + familyName + "%");
			statement.setString(2, "%" + name + "%");
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
	
	public Student addStudent(String familyName, String name) throws SQLException{
		try(
			 Connection connection = DriverManager.getConnection(this.databaseLocation);
			 PreparedStatement statement = connection.prepareStatement("insert into STUDENT(FAMILYNAME,NAME) values(?, ?)", Statement.RETURN_GENERATED_KEYS);			 		
		   ){
			if (familyName == null || familyName == "") return null;
			if (name == null || name == "") return null;
			familyName = this.removeScriptContent(familyName);
			name = this.removeScriptContent(name);	
			statement.setString(1, familyName);
			statement.setString(2, name);
			statement.execute();
			try(
				ResultSet resultSet = statement.getGeneratedKeys();
			   ){
				resultSet.next();
				int id = resultSet.getInt(1);
				return new StudentImpl(id, familyName, name);
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
	
	public Student modifyStudent(int id, String familyName, String name) throws SQLException{
		try(
			 Connection connection = DriverManager.getConnection(this.databaseLocation);
			 PreparedStatement statement = connection.prepareStatement("update STUDENT set FAMILYNAME = ?, NAME = ? where ID = ?", Statement.RETURN_GENERATED_KEYS);			 		
		   ){
			if (familyName == null || familyName == "") return null;
			if (name == null || name == "") return null;
			familyName = this.removeScriptContent(familyName);
			name = this.removeScriptContent(name);			
			statement.setString(1, familyName);
			statement.setString(2, name);
			statement.setInt(3, id);
			statement.execute();
			return new StudentImpl(id, familyName, name);			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String removeScriptContent(String html) {
        if(html != null) {
        	html = html.replace("<script>", "");
        	html = html.replace("</script>", "");        	
       }
       return html;
    }
}
