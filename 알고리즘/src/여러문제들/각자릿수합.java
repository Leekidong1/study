package 여러문제들;

public class 각자릿수합 {

	public static void main(String[] args) {
		
		int num = 1242;
		int sum = 0;
		
		while(num>0) {
			sum += num % 10;
			num = num/10;
		}
		System.out.println(sum);
		
	}

}
