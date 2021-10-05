package 여러문제들;

public class 거듭제곱 {

	public static void main(String[] args) {
		int n = 5;
		int m = 7;
		int sum=1;
		for(int i=0; i<m; i++) { // 0 1 2
			sum = sum*n;
		}
		System.out.println(sum);
	}

}
