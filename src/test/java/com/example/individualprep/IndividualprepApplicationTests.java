package com.example.individualprep.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticUtilityTest {

	private ArithmeticUtility arithmeticUtility;

	@BeforeEach
	void setUp() {
		arithmeticUtility = new ArithmeticUtility();
	}

	@Test
	@DisplayName("Should correctly add two numbers")
	void testAdd() {
		assertEquals(10.0, arithmeticUtility.add(7.0, 3.0), "7 + 3 should be 10");
		assertEquals(-1.0, arithmeticUtility.add(2.0, -3.0));
	}

	@Test
	@DisplayName("Should correctly subtract two numbers")
	void testSubtract() {
		assertEquals(4.0, arithmeticUtility.subtract(7.0, 3.0));
	}

	@Test
	@DisplayName("Should correctly multiply two numbers")
	void testMultiply() {
		assertEquals(21.0, arithmeticUtility.multiply(7.0, 3.0));
		assertEquals(0.0, arithmeticUtility.multiply(7.0, 0.0));
	}

	@Test
	@DisplayName("Should correctly divide two numbers")
	void testDivide() {
		assertEquals(2.5, arithmeticUtility.divide(5.0, 2.0));
	}

	@Test
	@DisplayName("Should throw IllegalArgumentException when dividing by zero")
	void testDivideByZero() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			arithmeticUtility.divide(10.0, 0.0);
		});
		assertEquals("Division by zero is not allowed.", exception.getMessage());
	}

	@ParameterizedTest
	@CsvSource({
			"2.0, 3, 8.0",   // 2^3 = 8
			"5.0, 0, 1.0",   // 5^0 = 1
			"2.0, -1, 0.5",  // 2^-1 = 0.5
			"4.0, 2, 16.0"   // 4^2 = 16
	})
	@DisplayName("Should correctly calculate exponents")
	void testExponent(double base, int power, double expected) {
		assertEquals(expected, arithmeticUtility.exponent(base, power));
	}
}

class VectorUtilityTest {

	private VectorUtility vectorUtility;
	private static final double DELTA = 1e-9; // Tolerance for floating-point comparisons

	@BeforeEach
	void setUp() {
		vectorUtility = new VectorUtility();
	}

	@Test
	@DisplayName("Should add two vectors of the same dimension")
	void testAddSuccess() {
		double[] v1 = {1.0, 2.0, 3.0};
		double[] v2 = {4.0, 5.0, 6.0};
		double[] expected = {5.0, 7.0, 9.0};

		assertArrayEquals(expected, vectorUtility.add(v1, v2), DELTA);
	}

	@Test
	@DisplayName("Should multiply vector by a scalar")
	void testScalarMultiply() {
		double[] v1 = {1.0, -2.0, 3.5};
		int scalar = 2;
		double[] expected = {2.0, -4.0, 7.0};

		assertArrayEquals(expected, vectorUtility.multiply(v1, scalar), DELTA);
	}

	@Test
	@DisplayName("Should calculate the dot product correctly")
	void testDotProduct() {
		double[] v1 = {1.0, 3.0, -5.0};
		double[] v2 = {4.0, -2.0, -1.0};
		// (1*4) + (3*-2) + (-5*-1) = 4 - 6 + 5 = 3
		assertEquals(3.0, vectorUtility.dotProduct(v1, v2), DELTA);
	}

	@Test
	@DisplayName("Should calculate the L2 norm (magnitude)")
	void testNorm() {
		double[] v = {3.0, 4.0}; // 3-4-5 triangle
		assertEquals(5.0, vectorUtility.norm(v), DELTA);
	}

	@Test
	@DisplayName("Should throw exception when adding vectors of different dimensions")
	void testAddDimensionMismatch() {
		double[] v1 = {1.0, 2.0};
		double[] v2 = {1.0, 2.0, 3.0};

		assertThrows(IllegalArgumentException.class, () -> vectorUtility.add(v1, v2));
	}

	@Test
	@DisplayName("Should throw exception for norm of empty vector")
	void testEmptyVectorNorm() {
		double[] empty = {};
		assertThrows(IllegalArgumentException.class, () -> vectorUtility.norm(empty));
	}
}