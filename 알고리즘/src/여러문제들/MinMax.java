package 여러문제들;

public class MinMax {
	
	private static void simple(int[] arr) {
		
		int minNum = arr[0];
		int maxNum = arr[0];
		
		for (int i=1; i<arr.length; i++) {
			if ( arr[i] < minNum ) {
				minNum = arr[i];
			} else {
				maxNum = arr[i];
			}
		}
		
		System.out.println("기본 MinMax구하기");
		System.out.println("최소값 : " + minNum + " 최대값 : " + maxNum);
	}
	
	private static void recursion(int[] arr, int index, int min, int max) {
		
		if ( arr.length > index ) {
			if ( min > arr[index] ) {
				min = arr[index];
			} else {
				max = arr[index];
			}		
			recursion(arr, index+1, min, max);
		}
		
		if ( index == arr.length-1 ) {
			System.out.println("재귀함수이용 MinMax구하기");
			System.out.println("최소값 : " + min + " 최대값 : " + max);
		}
	}
	
	
	public static void main(String[] args) {
		int[] arr = {24,75,92,83,61,48,97,50,11,115,1,222};
		simple(arr);
		recursion(arr, 1, arr[0], arr[0]);
	}
}
