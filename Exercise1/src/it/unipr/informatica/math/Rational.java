package it.unipr.informatica.math;

@SuppressWarnings("serial")
//TODO serializable
public final class Rational extends Number implements Cloneable, Comparable<Rational>{
	private int numerator;
	private int denominator;
	
	public Rational(int numerator, int denominator) {
		this.initialize(numerator,denominator);
	}
	
	private void initialize(int numerator, int denominator) {
		if (denominator == 0) throw new ArithmeticException("Error: Denominator cannot be 0");  
		
		if (denominator < 0) {
			numerator = ArithmeticHelper.changeSign(numerator);
			denominator = ArithmeticHelper.changeSign(denominator);
		}
		
		if (numerator == 0) {
			this.numerator = numerator;
			this.denominator = 1;
		}
		else {
			int greaterCommonDenominator = ArithmeticHelper.gcd(numerator,  denominator);
			
			this.numerator = numerator / greaterCommonDenominator;
			this.denominator = denominator / greaterCommonDenominator;				
		}
	}
	
	public int getNumerator() {
		return this.numerator;
	}
	
	public int getDenominator() {
		return this.denominator;
	}
	
	public Rational(int numerator) {
		this(numerator,1);
	}
	
	public Rational add(Rational right) {
		int numerator = this.numerator * right.denominator + this.denominator * right.numerator;
		int denominator = this.denominator * right.denominator;
		
		Rational r = new Rational(numerator,denominator);
		
		return r;
	}
	
	public Rational subtract(Rational right) {
		int numerator = this.numerator * right.denominator - this.denominator * right.numerator;
		int denominator = this.denominator * right.denominator;
		
		Rational r = new Rational(numerator,denominator);
		
		return r;
	}
	
	public Rational multiply(Rational right) {
		int numerator = this.numerator * right.numerator;
		int denominator = this.denominator * right.denominator;
		
		Rational r = new Rational(numerator,denominator);
		
		return r;
	}
	
	public Rational divide(Rational right) {
		int numerator = this.numerator * right.denominator;
		int denominator = this.denominator * right.numerator;
		
		Rational r = new Rational(numerator,denominator);
		
		return r;
	}
	
	public Rational inverse() {		
		Rational r = new Rational(denominator, numerator);
		
		return r;
	}
	
	public int sign() {
		if (this.numerator < 0) return -1;
		if (this.numerator == 0) return 0;
		return 1;
	}
	
	@Override
	public double doubleValue() {
		return (double) this.numerator / this.denominator;
	}
	
	@Override
	public float floatValue() {
		return (float) this.numerator / this.denominator;
	}
	
	@Override
	public int intValue() {
		return this.numerator / this.denominator;
	}
	
	@Override
	public long longValue() {
		return (long) this.numerator / this.denominator;
	}
	
	@Override
	public String toString() {
		if (this.denominator == 1) return Integer.toString(this.numerator);
		return this.numerator + "/" + this.denominator;
	}
	
	@Override
	public int hashCode() {
		return ArithmeticHelper.abs((this.numerator+this.denominator)*(this.numerator-this.denominator));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		
		if (this.getClass() != Rational.class || obj.getClass() != Rational.class) return false;
		// if(!(Obj instanceof Razionale)) return false;   basta questo se Rational è final
		
		Rational r = (Rational) obj;
		
		return (this.numerator == r.numerator && this.denominator == r.denominator);
	}
	
	@Override
	protected Object clone() {
		return this;
	}
	
	@Override
	public int compareTo(Rational right) {
		Rational r = this.subtract(right);
		return r.sign();
	}
	
	

}
