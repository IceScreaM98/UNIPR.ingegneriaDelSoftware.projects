package it.unipr.informatica.exercise8;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import it.unipr.informatica.exercise8.database.DatabaseManager;

public class Application implements Runnable{
	private ApplicationWindow applicationWindow;
	private DatabaseManager databaseManager;
	
	@Override
	public void run() {
		LoginDialog login = new LoginDialog(this);
		login.show();
		
		this.databaseManager = new DatabaseManager();
		this.applicationWindow = new ApplicationWindow(this);
		this.applicationWindow.show();
		
		//System.out.println(login.getUsername());
		//System.out.println(login.getPassword());
			
	}
	
	public void exit() {
		if (!this.areYouSure())
			return;
		System.exit(0);
	}
	
	
	public DatabaseManager getDatabaseManager() {
		return this.databaseManager;
	}
	
	public ApplicationWindow getApplicationWindow() {
		return this.applicationWindow;
	}
	
	public void error(String message) {
		JOptionPane.showMessageDialog(applicationWindow.getFrame(), message, "Exercise 8", JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean areYouSure() {
		int response = JOptionPane.showConfirmDialog(applicationWindow.getFrame(), "Are you sure?", "Exercise 8", JOptionPane.OK_CANCEL_OPTION);
		return response == JOptionPane.OK_OPTION;
	}
	
	public boolean areYouSure(JDialog dialog) {
		int response = JOptionPane.showConfirmDialog(dialog, "Are you sure?", "Exercise 8", JOptionPane.OK_CANCEL_OPTION);
		return response == JOptionPane.OK_OPTION;
	}
}
