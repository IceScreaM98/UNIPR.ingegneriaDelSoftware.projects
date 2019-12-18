package it.unipr.informatica.exercise8;

import it.unipr.informatica.exercise8.database.DatabaseManager;

public class Application implements Runnable{
	private ApplicationWindow applicationWindow;
	private DatabaseManager databaseManager;
	
	@Override
	public void run() {
		LoginDialog login = new LoginDialog();
		login.show();

		this.databaseManager = new DatabaseManager();
		this.applicationWindow = new ApplicationWindow(this);
		this.applicationWindow.show();
		
		//System.out.println(login.getUsername());
		//System.out.println(login.getPassword());
		//System.out.println("Window closed");		
	}
	
	
	public DatabaseManager getDatabaseManager() {
		return this.databaseManager;
	}
}
