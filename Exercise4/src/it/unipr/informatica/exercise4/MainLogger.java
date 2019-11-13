package it.unipr.informatica.exercise4;

import it.unipr.informatica.aspect.Logging;
import it.unipr.informatica.exercise4.model.Student;
import it.unipr.informatica.exercise4.model.impl.StudentImpl;

public class MainLogger {
	public static void main(String[] args) {
		Student s1 = new StudentImpl(1, "Rossi", "Mario");
		Student s2 = new StudentImpl(1, "Neri", "Cesare");
		System.out.println("Name s1: " + s1.getFamilyName() + " " + s1.getName());
		System.out.println("Name s2: " + s2.getFamilyName() + " " + s2.getName());
		s2 = Logging.apply(s2);   //faccio senza fare il cast a Student poichè Logger è parametrico
		System.out.println("Name s2: " + s2.getFamilyName() + " " + s2.getName());
	}
}
