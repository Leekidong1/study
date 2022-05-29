package 여러문제들;

public class 별출력3 {

	public static void main(String[] args) {
		int num = 10;
		for(int i=1; i<=num; i++) {
			for(int k=num-i; k>0; k--) {
				System.out.print(" ");
			}
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
