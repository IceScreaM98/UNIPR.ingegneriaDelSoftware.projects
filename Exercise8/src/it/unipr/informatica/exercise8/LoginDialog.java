package it.unipr.informatica.exercise8;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LoginDialog {
	private JDialog dialog;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String username;
	private String password;
	
	public LoginDialog() {
		//Def. pulsante di login
		JButton button = new JButton();
		button.setText("Login");
		//Def. pannello in fondo al frame
		JPanel southPanel = new JPanel();
		southPanel.add(button);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//Def. pannello in alto al frame
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(6, 1));
		northPanel.add(new JLabel("Username :"));  
		this.usernameField = new JTextField(32);
		northPanel.add(this.usernameField);
		northPanel.add(new JLabel("Password :"));
		this.passwordField = new JPasswordField(32);
		northPanel.add(this.passwordField);
		//Def. Look & Feel
		northPanel.add(new JLabel("Look & Feel"));
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		String lafs[] = new String[infos.length];
		LookAndFeel laf = UIManager.getLookAndFeel();
		int selected = 0;
		for (int i = 0; i < lafs.length; i++) {
			if (infos[i].getClassName() == laf.getClass().getName())
				selected = i;
			lafs[i] = infos[i].getName();
		}
		JComboBox<String> lafComboBox = new JComboBox<>(lafs);
		lafComboBox.setSelectedIndex(selected);
		northPanel.add(lafComboBox);
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
		//Def. eventi (listener)
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDialog.this.checkCredentials();		
			}
		});
		
		lafComboBox.addActionListener((e) -> {
			int index = lafComboBox.getSelectedIndex();
			String className = infos[index].getClassName();
			try {
				UIManager.setLookAndFeel(className);
				SwingUtilities.updateComponentTreeUI(this.dialog);
				this.dialog.pack();
			}
			catch(Throwable t) {
				t.printStackTrace();
				Toolkit.getDefaultToolkit().beep();
			}
		});
	}
	
	public void show() {
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	private void checkCredentials() {
		this.username = this.usernameField.getText();
		this.password = this.passwordField.getText();
		if (this.username.length() == 0) {
			Toolkit.getDefaultToolkit().beep();
			this.usernameField.requestFocus();
		}
		this.dialog.dispose();
	}
}

