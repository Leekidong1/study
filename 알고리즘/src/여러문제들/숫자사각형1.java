package 여러문제들;

public class 숫자사각형1 {

	public static void main(String[] args) {
		int idx = 5;
		
		int[][] arr = new int[idx][idx];
		
		for(int i=0; i<idx; i++) {
			if(i%2==0) {
				for(int j=0; j<idx; j++) {
					arr[i][j] = i*idx+ j+1;
				}
			}else if(i%2==1) {
				for(int j=idx-1; j>=0; j--) {
					arr[i][j] = (i+1)*idx - j;
//					arr[i][j] = i*idx + idx -j;
				}
			}
		}
		
		for(int i=0; i<idx; i++) {
			for(int j=0; j<idx; j++) {
				System.out.printf("%4d",arr[i][j]);
			}
			System.out.println();
		}
	}

}
