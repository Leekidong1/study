package 여러문제들;

import java.util.Arrays;

public class 피보나치연습 {
	
	private static int count;
	
	private static void pibonachi(int num, int num1) {
		if (count < 10) {
			int num2 = num1 + num;
			System.out.print(num2 + ", ");
			num = num1;
			num1 = num2;
			count++;
			pibonachi(num, num1);
		}
	}
	
	public static void main(String[] args) {
		int num = 1;
		int num1 = 1;
		System.out.print(num + ", " + num1 + ", ");
		pibonachi(num, num1);
	}
}
