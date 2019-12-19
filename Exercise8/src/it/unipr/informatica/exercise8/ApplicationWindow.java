package it.unipr.informatica.exercise8;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class ApplicationWindow {
	private JFrame frame;
	private AllStudentsPanel allStudentsPanel;
	private Application app;
	
	public ApplicationWindow(Application app) {
		ImageIcon addIcon = new ImageIcon(this.getClass().getResource("/plus.png"));
		ImageIcon deleteIcon = new ImageIcon(this.getClass().getResource("/remove.png"));
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(exitMenuItem);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);

		JMenu studentMenu = new JMenu("Student");
		JMenuItem addMenuItem = new JMenuItem("Add", addIcon);		
		JMenuItem deleteMenuItem = new JMenuItem("Delete", deleteIcon);
		studentMenu.add(addMenuItem);
		studentMenu.add(deleteMenuItem);
		menuBar.add(studentMenu);
		
		JToolBar toolBar = new JToolBar("Exercise 8");
		
		JButton addButton = new JButton(addIcon);
		addButton.setFocusable(false);
		toolBar.add(addButton);
		
		JButton deleteButton = new JButton(deleteIcon);
		deleteButton.setFocusable(false);
		toolBar.add(deleteButton);
		
		this.app = app;
		this.frame = new JFrame();
		this.frame.setTitle("Exercise 8");
		this.allStudentsPanel = new AllStudentsPanel(app);
		this.allStudentsPanel.refresh();
		this.frame.getContentPane().setLayout(new BorderLayout());
		this.frame.getContentPane().add(allStudentsPanel, BorderLayout.CENTER);
		this.frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.frame.setJMenuBar(menuBar);
		this.frame.pack();
		this.frame.setLocationByPlatform(true);
		
		addButton.addActionListener((e) -> {
			this.allStudentsPanel.addStudent();
		});
		
		addMenuItem.addActionListener((e) -> {
			this.allStudentsPanel.addStudent();
		});
		
		deleteButton.addActionListener((e) -> {
			this.allStudentsPanel.deleteStudent();
		});
		
		deleteMenuItem.addActionListener((e) -> {
			this.allStudentsPanel.deleteStudent();
		});
		
		exitMenuItem.addActionListener((e) -> {
			this.app.exit();
		});
	}
	
	public void show() {
		this.allStudentsPanel.refresh();
		this.frame.setBounds(0, 0, 500, 500);
		this.frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	

}
