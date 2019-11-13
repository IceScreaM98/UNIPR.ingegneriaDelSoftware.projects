package it.unipr.informatica.exercise4;


import it.unipr.informatica.bean.BeanFactory;
import it.unipr.informatica.exercise4.model.Student;


public class MainBean {
	public static void main(String[] args) {
		Student s1 = (Student) BeanFactory.create(Student.class);
		s1.setId(1);
		s1.setFamilyName("Rossi");
		s1.setName("Mario");
		Student s2 = (Student) BeanFactory.create(Student.class);
		s2.setId(2);
		s2.setFamilyName("Neri");
		s2.setName("Cesare");		
		System.out.println("Name s1: " + s1.getFamilyName() + " " + s1.getName());
		System.out.println("Name s2: " + s2.getFamilyName() + " " + s2.getName());
	}
}
