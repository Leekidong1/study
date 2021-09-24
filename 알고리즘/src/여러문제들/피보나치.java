package 알고리즘;

public class 피보나치 {

	public static void main(String[] args) {
		
		int[] arr = new int[10];
		
		arr[0] = 1;
		arr[1] = 1;
		
		for(int an=2; an<arr.length; an++) {
			arr[an] = arr[an-1]+arr[an-2];
//			arr[an-1] = arr[an];
//			arr[an-2] = arr[an-1];
		}
		
//		for(int i : arr)System.out.println(i);
		
		int a = 1;
		int b = 1;
		System.out.println(a);
		System.out.println(b);
		for(int i=3; i<10; i++) {
			int c = a+b;
			System.out.println(c);
			b = a;
			a = c;
		}
	}

}
