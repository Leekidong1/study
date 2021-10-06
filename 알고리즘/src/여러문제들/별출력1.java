package 알고리즘;

public class 별출력1 {

	public static void main(String[] args) {
		int num = 5;
		
		for(int i=1; i<=num; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
