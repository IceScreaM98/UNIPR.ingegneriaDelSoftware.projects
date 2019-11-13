package it.unipr.informatica.exercise4.model.impl;
import java.util.HashMap;
import java.util.Map;

import it.unipr.informatica.exercise4.model.Student;

public class StudentImpl implements Student{
	
	//devono avere metodo equals() e hashCode() e gli oggetti devono essere immutabili HASHMAP
	//TREEMAP
	private Map<String, Object> properties = new HashMap<>();
	//private String familyName;
	//private String name;
	//private int id;

	public StudentImpl(int id, String familyName, String name) {
		this.properties.put("id", id);
		this.properties.put("familyName", familyName);
		this.properties.put("name", name);
	}
	
	@Override
	public String getFamilyName() {
		return (String) this.properties.get("familyName");
	}


	public void setFamilyName(String familyName) {
		this.properties.put("familyName", familyName);
	}
	
	@Override
	public String getName() {
		return (String) this.properties.get("name");
	}

	public void setName(String name) {
		this.properties.put("name", name);
	}

	@Override
	public int getId() {
		return (int) this.properties.get("id");
	}

	public void setId(int id) {
		this.properties.put("id", id);
	}
	
	
}
