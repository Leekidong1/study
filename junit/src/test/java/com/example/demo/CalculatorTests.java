package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorTests {
	private Calculator cal = new Calculator();
	
	@BeforeEach
	public void setup() {
		cal = new Calculator();
		System.out.println("before");
	}
	
	@Test
	public void add() {
		assertEquals(3, cal.add(1, 2));
		System.out.println("add");
	}

	@Test
	public void divide() {
		assertEquals(1, cal.divide(2, 2));
		System.out.println("divide");
	}
}
