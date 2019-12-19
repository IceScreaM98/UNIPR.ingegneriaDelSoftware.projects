package it.unipr.informatica.exercise8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import it.unipr.informatica.exercise8.database.DatabaseManager;
import it.unipr.informatica.exercise8.model.Student;

@SuppressWarnings("serial")
public class AllStudentsPanel extends JPanel{
		private List<Student> allStudents = new ArrayList<>();
		private DatabaseManager databaseManager;
		private Application app;
		private JTable table;
		private Model tableModel;
	
		public AllStudentsPanel(Application app) {
			this.databaseManager = app.getDatabaseManager();
			//this.loadStudents();
			this.app = app;
			this.table = new JTable();
			this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.tableModel = new Model();
			this.table.setModel(this.tableModel);
			JScrollPane scrollPane = new JScrollPane(table);
			this.setLayout(new BorderLayout());
			this.add(scrollPane, BorderLayout.CENTER);
		}
		
		public void refresh() {
			new Thread() {
				@Override
				public void run() {
					AllStudentsPanel.this.loadStudents();
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							AllStudentsPanel.this.tableModel = new Model();	
							AllStudentsPanel.this.table.setModel(AllStudentsPanel.this.tableModel);
						}
					});
				};
			}.start();
		}
		
		private void loadStudents() {
			try {
				this.allStudents = this.databaseManager.getAllStudents();
			}
			catch(Throwable t) {
				t.printStackTrace();
				this.app.error("Cannot connect to database");
				this.allStudents = new ArrayList<Student>();
			}
		}
		
		public void addStudent() {
			JPanel northPanel = new JPanel();
			northPanel.setLayout(new GridLayout(4, 1));
			
			northPanel.add(new JLabel("Family Name"));
			JTextField familyNameField = new JTextField(50);
			
			northPanel.add(familyNameField);
			northPanel.add(new JLabel("Name :"));
			
			JTextField nameField = new JTextField(50);
			northPanel.add(nameField);
			
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(northPanel, BorderLayout.NORTH);
			
			ApplicationWindow applicationWindow = this.app.getApplicationWindow();
			int response = JOptionPane.showConfirmDialog(applicationWindow.getFrame(), contentPanel, "Exercise 8", JOptionPane.OK_CANCEL_OPTION);
			if (response != JOptionPane.OK_OPTION)
				return;
			String familyName = familyNameField.getText();
			String name = nameField.getText();
			if (familyName.length() == 0 || familyName.length() > 50 || name.length() == 0 || name.length() > 50) {
				this.app.error("Invalid field");
				return;
			}
			this.tableModel.addStudent(familyName, name);
		}
		
		public void deleteStudent() {
			int rowIndex = this.table.getSelectedRow();
			if (rowIndex < 0) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			if (!this.app.areYouSure()) 
				return;
			this.tableModel.deleteStudent(rowIndex);
		}
		
		private class Model implements TableModel{
			private List<TableModelListener> listeners = new ArrayList<TableModelListener>();
			
			@Override
			public int getColumnCount() {
				return 3; //matricola + nome + cognome
			}
			
			@Override
			public int getRowCount() {
				return AllStudentsPanel.this.allStudents.size();
			}
			
			@Override
			public String getColumnName(int columnIndex) {
				switch(columnIndex) {
				case 0:
					return "ID";
				case 1:
					return "Family Name";
				default:
					return "Name";
				}
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex) {
				case 0:
					return int.class;
				case 1:
					return String.class;
				default:
					return String.class;			
				}
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnIndex != 0;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Student s = allStudents.get(rowIndex);
				switch(columnIndex) {
				case 0:
					return s.getId();
				case 1:
					return s.getFamilyName();
				default:
					return s.getName();
				}
			}
			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	
				try {
					Student s = AllStudentsPanel.this.allStudents.get(rowIndex);
					if (columnIndex == 1) 
						s = AllStudentsPanel.this.databaseManager.modifyStudent(s.getId(), (String) aValue, s.getName());
					else 
						s = AllStudentsPanel.this.databaseManager.modifyStudent(s.getId(), s.getFamilyName(), (String) aValue);
					AllStudentsPanel.this.allStudents.set(rowIndex, s);	
					this.notifyValueChanged(rowIndex, columnIndex);
				}
				catch(Throwable t) {
					t.printStackTrace();
				}
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {	
				this.listeners.add(l);
			}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {
				this.listeners.remove(l);
			}
			
			private void notifyValueChanged(int rowIndex, int columnIndex) {
				TableModelEvent e = new TableModelEvent(this, rowIndex, rowIndex, columnIndex);
				for (TableModelListener l : this.listeners) {
					l.tableChanged(e);
				}
			}
			
			private void notifyTableChanged() {
				TableModelEvent e = new TableModelEvent(this);
				for (TableModelListener l : this.listeners) {
					l.tableChanged(e);
				}
			}
			
			public void addStudent(String familyName, String name) {
				try {
					Student s = AllStudentsPanel.this.databaseManager.addStudent(familyName, name);
					AllStudentsPanel.this.allStudents.add(s);	
					this.notifyTableChanged();
				}
				catch(Throwable t) {
					t.printStackTrace();
					AllStudentsPanel.this.app.error("Cannot connect to database");
				}
			}
			
			public void deleteStudent(int index) {
				try {
					Student s = AllStudentsPanel.this.allStudents.get(index);
					AllStudentsPanel.this.databaseManager.deleteStudent(s.getId());
					AllStudentsPanel.this.allStudents.remove(index);
					this.notifyTableChanged();
				}
				catch(Throwable t) {
					t.printStackTrace();
					AllStudentsPanel.this.app.error("Cannot connect to database");
				}
			}
		}
}
