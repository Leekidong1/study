package 알고리즘;

public class 숫자사각형2 {
	public static void main(String[] args) {
		
		int input = 4;
		
		int[][] arr = new int[input][input];
		int num =0;
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				num++;
				arr[i][j] = num;
			}
		}
		
		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				System.out.printf("%4d",arr[j][i]);
			}
			System.out.println();
		}
	}
}
