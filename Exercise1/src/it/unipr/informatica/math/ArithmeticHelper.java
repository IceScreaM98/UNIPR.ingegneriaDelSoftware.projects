package it.unipr.informatica.math;

public class ArithmeticHelper {
	
	public static int gcd(int a, int b) {
		if (a == 0 || b == 0) throw new ArithmeticException("Error: GCD must have 2 parameters different from 0");
		a = ArithmeticHelper.abs(a);
		b = ArithmeticHelper.abs(b);
		return gcd_aux(a,b);
	}
	
	private static int gcd_aux(int a, int b) {
		if (b == 0) return a;
		return gcd_aux(b, a % b);
	}
		
	
	public static int abs(int x) {
		if (x < 0) return -x;
		else return x;
	}
	
	public static int changeSign(int x) {
		return -x;
	}

}
