package it.unipr.informatica.exercise8;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame {
	private JDialog dialog;
	
	public LoginFrame() {
		//Def. pulsante di login
		JButton button = new JButton();
		button.setText("Login");
		//Def. pannello in fondo al frame
		JPanel southPanel = new JPanel();
		southPanel.add(button);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//Def. pannello in alto al frame
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(4, 1));
		northPanel.add(new JLabel("Username :"));   
		northPanel.add(new JTextField(32));
		northPanel.add(new JLabel("Password :"));
		northPanel.add(new JPasswordField(32));
		//Def. logo
		URL url = getClass().getResource("/logo.jpg");
		ImageIcon logo = new ImageIcon(url);
		//Def. pannello centrale
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(northPanel, BorderLayout.NORTH);
		centerPanel.add(southPanel, BorderLayout.SOUTH);
		//Def. pannello icona a sx
		JPanel iconPanel = new JPanel();
		iconPanel.setLayout(new BorderLayout());
		iconPanel.add(new JLabel(logo));
		//Def. pannello generale
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(iconPanel, BorderLayout.WEST);
		contentPanel.add(centerPanel, BorderLayout.CENTER);
		//Def. frame
		this.dialog = new JDialog();
		this.dialog.setTitle("Exercise 8");
		this.dialog.getContentPane().add(contentPanel);
		this.dialog.pack();
		//Def. eventi
	}
	
	public void show() {
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
	}
}

