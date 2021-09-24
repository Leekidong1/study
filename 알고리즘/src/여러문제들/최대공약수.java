package 알고리즘;

public class 최대공약수 {

	public static void main(String[] args) {
		
		int smallNum = 12;
		int bigNum = 18;
		int max =0;
		for(int i=1; i<bigNum; i++) {
			if(bigNum % i==0) {
				if(smallNum % i==0) {
					max = i;
				}
			}
		}
		
		System.out.println(max);
	}

}
