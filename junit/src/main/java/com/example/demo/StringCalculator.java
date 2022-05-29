package com.example.demo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StringCalculator {
	
	int calculate(String input) {
		String[] arr = null;
		int sum = 0;
		
		/*validation*/
		String str = input.trim();
		if ( str == null || str.isEmpty() ) return 0;
		
		/*split*/
		arr = split(arr, str);
	
		/*calculate*/
		for ( int i=0; i<arr.length; i++ ) {
			isNum(arr, i);
			int num = Integer.parseInt(arr[i]);
			if ( num < 0 ) throw new RuntimeException();
			sum += num;
		}
		
		return sum;
	}
	
	private String[] split(String[] arr, String str) {
		Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(str);
		if ( matcher.find() ) {
			String delimeter = matcher.group(1);
			System.out.println("커스텀 구분자 : " + delimeter);
			arr = matcher.group(2).split(delimeter);
			System.out.println("숫자들 : " + Arrays.toString(arr));
			return arr;
		}
		return str.split(",|:|;");
	}
	
	/*check number*/
	private void isNum(String[] arr, int i) {
		for (int j=0; j<arr[i].length(); j++) {
			char c = arr[i].charAt(j);
			if ( !(c > 47 && c < 58) ) {
				System.out.println(c + " 는 숫자아님");
				throw new RuntimeException();
			}
		}
	}
}
