package 여러문제들;

public class 팩토리얼연습 {
	
	private int sum;
	
	private void factorial(int num) {
		if(num > 1) {
			if ( sum == 0 ) {
				sum = num * (num-1);
			} else {
				sum *= (num-1);
			}
			factorial( num-1 );
		}
	}
	
	private void print() {
		System.out.println(sum);
	}
	
	public static void main(String[] args) {
		팩토리얼연습 p = new 팩토리얼연습();
		p.factorial(7);
		p.print();
	}
}
