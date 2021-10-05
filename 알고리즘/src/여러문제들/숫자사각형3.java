package 여러문제들;

public class 숫자사각형3 {

	public static void main(String[] args) {
		
		String input = "421314218";
		int arr[] = new int[10];
		
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			int idx = ch-48;
			arr[idx]++;
		}
		
		for(int i=0; i<arr.length; i++) {
			System.out.println(i+" : "+arr[i]);
		}
	}

}
