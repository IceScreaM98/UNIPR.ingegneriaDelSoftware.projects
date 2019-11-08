package it.unipr.informatica.exercise4.model.impl;
import it.unipr.informatica.exercise4.model.Student;

public class StudentImpl implements Student{
	private String familyName;
	private String name;
	private int id;

	public StudentImpl(int id, String familyName, String name) {
		this.familyName = familyName;
		this.name = name;
		this.id = id;
	}
	
	@Override
	public String getFamilyName() {
		return familyName;
	}


	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
