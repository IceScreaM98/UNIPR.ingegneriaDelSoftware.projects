package it.unipr.informatica.exercise8;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class AllStudentsPanel extends JPanel{
		private List<Student> allStudents;
		private DatabaseManager databaseManager;
	
		public AllStudentsPanel(Application app) {
			this.databaseManager = app.getDatabaseManager();
			this.loadStudents();
			JTable table = new JTable();
			table.setModel(new Model());
			JScrollPane scrollPane = new JScrollPane(table);
			this.setLayout(new BorderLayout());
			this.add(scrollPane, BorderLayout.CENTER);
		}
		
		private void loadStudents() {
			try {
				this.allStudents = this.databaseManager.getAllStudents();
			}
			catch(Throwable t) {
				t.printStackTrace();
				this.allStudents = new ArrayList<Student>();
			}
		}
		
		private class Model implements TableModel{
			private List<TableModelListener> listeners = new ArrayList<TableModelListener>();
			
			@Override
			public int getColumnCount() {
				return 3; //matricola + nome + cognome
			}
			
			@Override
			public int getRowCount() {
				return 0;
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
						s = AllStudentsPanel.this.databaseManager.modifyStudent(s.getId(), (String) aValue, s.getName());
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
		}
}
