package 알고리즘;

public class 팩토리얼 {

	public static void main(String[] args) {
		
		int num = 5;
		int result = 1;
		for(int i=1; i<=num; i++) {
			result *= i;
		}
		System.out.println(result);
	}

}
