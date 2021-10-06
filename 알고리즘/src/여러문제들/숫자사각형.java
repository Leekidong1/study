package 알고리즘;

public class 숫자사각형 {

	public static void main(String[] args) {
		
		int num = 5;
		
		for(int i=1; i<=(num*num); i++) {
			System.out.printf("%5d",i);
			if(i%num==0) {
				System.out.println();
			}
		}
	}

}
