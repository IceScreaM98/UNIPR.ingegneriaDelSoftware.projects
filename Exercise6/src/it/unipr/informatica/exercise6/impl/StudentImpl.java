package it.unipr.informatica.exercise6.impl;

import it.unipr.informatica.exercise6.model.Student;

public class StudentImpl implements Student{
	private int id;
	private String familyName;
	private String name;
	
	public StudentImpl(int id, String familyName, String name) {
		this.id = id;
		this.familyName = familyName;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getFamilyName() {
		return this.familyName;
	}
	
	public String getName() {
		return this.name;
	}
}
