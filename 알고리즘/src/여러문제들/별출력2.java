package 여러문제들;

public class 별출력2 {

	public static void main(String[] args) {
		int num = 5;
		
		for(int i=num; i>=1; i--) { // 5 4 3 2 1
			for(int k=0; k<num-i; k++) { // 0 1 2 3 4
				System.out.print(" ");
			}
			for(int j=i; j>=1; j--) { // 5 4 3 2 1
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
