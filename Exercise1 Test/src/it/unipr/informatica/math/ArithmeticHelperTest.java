package it.unipr.informatica.math;

import static org.junit.Assert.*;
import static it.unipr.informatica.math.ArithmeticHelper.gcd;
import static it.unipr.informatica.math.ArithmeticHelper.abs;
import static it.unipr.informatica.math.ArithmeticHelper.changeSign;


import org.junit.Test;

public class ArithmeticHelperTest {

	@Test
	public void testGcdLeftLessRight() {
		assertEquals(3, gcd(3,12));
	}
	
	@Test
	public void testGcdRightLessLeft() {
		assertEquals(12, gcd(144,12));
	}
	
	@Test
	public void testGcdRightEqualsLeft() {
		assertEquals(157, gcd(157,157));
	}
	
	@Test (expected = ArithmeticException.class)
	public void testGcdLeftIsZero() {
		gcd(0,67);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testGcdRightIsZero() {
		gcd(15,0);
	}
	
	@Test
	public void testGcdLeftIsNegative() {
		assertEquals(3, gcd(-3,6));
	}
	
	@Test
	public void testGcdRightIsNegative() {
		assertEquals(4, gcd(-4,8));
	}
	
	@Test
	public void testGcdBothAreNegative() {
		assertEquals(5, gcd(-5,-15));
	}
	
	@Test
	public void testAbsPositive() {
		assertTrue(abs(5) > 0);
	}
	
	@Test
	public void testAbsNegative() {
		assertTrue(abs(-5) > 0);
	}
	
	@Test
	public void testAbsIsZero() {
		assertTrue(abs(0) == 0);
	}
	
	@Test
	public void testChangeSignPositive() {
		assertTrue(changeSign(5) < 0);
	}
	
	@Test
	public void testChangeSignNegative() {
		assertTrue(changeSign(-5) > 0);
	}
	
	@Test
	public void testChangeSignIsZero() {
		assertTrue(changeSign(0) == 0);
	}
	
	
	

}
