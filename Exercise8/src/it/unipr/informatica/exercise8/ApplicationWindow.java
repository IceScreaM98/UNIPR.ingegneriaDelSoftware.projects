package it.unipr.informatica.exercise8;

import java.util.List;

import javax.swing.JFrame;

public class ApplicationWindow {
	private JFrame frame;

	
	public ApplicationWindow(Application app) {
		this.frame = new JFrame();
		this.frame.setTitle("Exercise 8");
		AllStudentsPanel allStudentsPanel = new AllStudentsPanel(app);
		this.frame.getContentPane().add(allStudentsPanel);
	}
	
	public void show() {
		this.frame.setBounds(0, 0, 500, 500);
		this.frame.setVisible(true);
	}
}
