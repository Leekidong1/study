package 알고리즘;

public class 십진수를이진수로 {

	public static void main(String[] args) {
		int inputNum = 25;
		String total = "";
		while(inputNum > 0) {
			int num = inputNum % 2;
			inputNum = inputNum / 2;
			System.out.println(num+" "+inputNum);
			total = num+total;
		}
		
		System.out.println(total);
	}

}
