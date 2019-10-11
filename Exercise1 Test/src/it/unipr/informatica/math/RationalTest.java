package it.unipr.informatica.math;

import static org.junit.Assert.*;


import org.junit.Test;

public class RationalTest {

	@Test
	public void testNumeratorIsNegative() {
		assertEquals(new Rational(1,-2), new Rational(-1,2));		
	}
	
	@Test
	public void testDenominatorIsNegative() {
		assertEquals(new Rational(-1,2), new Rational(1,-2));
	}
	
	@Test
	public void testBothAreNegative() {
		assertEquals(new Rational(1,2), new Rational(-1,-2));
	}
	
	@Test
	public void testNumeratorIsZero() {
		assertEquals(new Rational(0,-5), new Rational(0,100));
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDenominatorIsZero() {
		new Rational(5,0);
	}

}
