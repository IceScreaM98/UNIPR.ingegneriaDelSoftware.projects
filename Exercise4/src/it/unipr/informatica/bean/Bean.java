package it.unipr.informatica.bean;

import java.util.HashMap;
import java.util.Map;

public class Bean {
	private Map<String, Object> properties = new HashMap<>();
	
	public Object get(String propertyName) {
		return this.properties.get(propertyName);
	}
	
	public void set(String propertyName, Object value) {
		this.properties.put(propertyName, value);
	}
}
