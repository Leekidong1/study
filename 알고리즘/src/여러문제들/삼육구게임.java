package 알고리즘;

public class 삼육구게임 {

	public static void main(String[] args) {
		for(int i=1; i<=100; i++) {
			if(i<10) {
				if(i%3 == 0) {  //3 6 9
					System.out.println("짝");
				}else {
					System.out.println(i);
				}
			}else if(i>10 && (i%10)!= 0) {
				if((i%10)%3 ==0 && (i/10)%3 == 0) {
					System.out.println("짝짝");
				}else if((i%10)%3 ==0) {
					System.out.println("짝");
				}else if((i/10)%3 == 0) {
					System.out.println("짝");
				}else {
					System.out.println(i);
				}
			}else if(i/10%3 ==0) {
				System.out.println("짝");
			}else {
				System.out.println(i);
			}
		}
	}

}
