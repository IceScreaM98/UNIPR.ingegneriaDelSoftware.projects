package it.unipr.informatica.exercise5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");   //dobbiamo specificare il driver che vogliamo caricare nel CLASS-PATH
		}
		catch(Throwable t) {
			t.printStackTrace();
			System.exit(-1);
		}
		String fn = "Il";
		String n = "Gabibbo";
		try (
			Connection connection = DriverManager.getConnection("jdbc:derby://127.0.0.1/Example");     //se vogliamo usare postgres.mysql,.... dobbiamo cambiare url
			PreparedStatement statement = connection.prepareStatement("insert into STUDENT(FAMILYNAME, NAME) values (?, ?)")){
			statement.setString(1, fn);
			statement.setString(2, n);
			statement.execute();
			//statement.execute("insert into STUDENT(FAMILYNAME, NAME) values ('" + fn + "', '" + n + "')");   //Statement statement = connection.createStatement();
			//statement.execute("insert into STUDENT(FAMILYNAME, NAME) values ('Rossi','Mario')");
			//statement.execute("insert into STUDENT(FAMILYNAME, NAME) values ('Neri','Cesare')");
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		try (
			Connection connection = DriverManager.getConnection("jdbc:derby://127.0.0.1/Example");     //se vogliamo usare postgres.mysql,.... dobbiamo cambiare url
			Statement statement = connection.createStatement()){
			ResultSet resultSet = statement.executeQuery("select * from STUDENT");
			while(resultSet.next()) {
				int id = resultSet.getInt("ID");
				String familyName = resultSet.getString("FAMILYNAME");
				String name = resultSet.getString("NAME");
				System.out.println("Record " + id + ": " + familyName + " " + name);
			}
		}
		catch(Throwable t) {
			t.printStackTrace();
			System.exit(-1);
		}
		//resultSet.close();  //dobbiamo sempre rilasciare le risorse (non serve se utilizziamo i blocchi try-catch)
		//statement.close();		
		//connection.close();
		
	}
}