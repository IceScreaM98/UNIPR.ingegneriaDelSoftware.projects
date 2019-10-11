package it.unipr.informatica.exercise1;
import it.unipr.informatica.math.Rational;

public class Main {
	public static void main(String[] args) {
		Rational r1 = new Rational(-4,16);
		
		System.out.println("R1 = " + r1);
		
		Rational r2 = new Rational(4,-16);
		
		System.out.println("R2 = " + r2);
		
		if (r1.equals(r2)) System.out.println("R1 == R2");
		else System.out.println("R1 != R2");
		
		System.out.println("R1 hashcode: " + r1.hashCode());
		System.out.println("R1 float value: " + r1.floatValue());
		System.out.println("R1 long value: " + r1.longValue());
		System.out.println("R1 double value: " + r1.doubleValue());
		System.out.println("R2 hashcode: " + r2.hashCode());
		System.out.println("R2 long value: " + r2.longValue());	
		System.out.println("R1 + R2 = " + r1.add(r2));
		System.out.println("R1 - R2 = " + r1.subtract(r2));
		System.out.println("R1 * R2 = " + r1.multiply(r2));
		System.out.println("R1 / R2 = " + r1.divide(r2));
		System.out.println("R1 compare to R2 = " + r1.compareTo(r2));
		
	}
}
