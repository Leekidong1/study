package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringCalculatorTest {
	private StringCalculator sc;
	
	@BeforeEach
	public void setup() {
		sc = new StringCalculator();
	}
	
	@Test
	public void calculate1() {
		assertEquals(12, sc.calculate("//;\n1;2;3;6;\n"));
	}
	
	@Test
	public void calculate2() {
		assertEquals(12, sc.calculate("1:2:3:6"));
	}
	
	@Test
	public void calculate3() {
		assertEquals(6, sc.calculate("1,2:3"));
	}
	
	@Test
	public void calculate4() {
		assertEquals(6, sc.calculate("1;2:3"));
	}
	
	@Test
	public void calculate5() {
		assertEquals(0, sc.calculate(""));
	}
	
	@Test
	public void calculate6() throws Exception {
		sc.calculate("-1,2:3");
	}
}
