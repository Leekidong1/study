package 알고리즘;

public class 소수판별 {

	public static void main(String[] args) {
		boolean swit = false;
		int num = 41;
		for(int i=2; i<num; i++) {
			if(num%i == 0) {
				swit = true;
			}
		}
		if(swit == true) {
			System.out.println(num+"은 약수입니다.");
		}else {
			System.out.println(num+"은 소수입니다.");
		}
	}

}
