package 정렬;

import java.util.Arrays;

public class 버블정렬 {
	/*	정의 : 앞뒤index를 비교해서 서로 swap해가면서 last index 결정부터 시작하여 first index까지 값의 자리를 바꿔준다. 
	 * 	<버블정렬 장단점> 
	 * 		1.장점 : 알고리즘이 단순하며, 메모리가 제한적인 경우 성능 상의 이점
	 * 		2.장점 : 데이터를 하나씩 정밀하게 비교할 수 있는 점
	 * 		3.단점 : 비교횟수가 많아져 비효율적인 성능을 가지는 점
	 */
	public static void sort(int[] arr, int length) {
		int temp=0;
		if(length >= 0) {
			for(int i=1; i<length; i++) {
				if(arr[i-1] > arr[i]) {
					temp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = temp;
				}
				sort(arr, length-1);
			} 
		}else {
			return;
		}
	}
	
	public static void buble(int[] arr, int length) {
		int temp=0;
		for(int i=0; i<length; i++) {
			for(int j=0; j<(length-1)-i; j++) {
				if(arr[j] < arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr  = {4,6,2,3,5,1};
		int length = arr.length;
		
		sort(arr, length);
		System.out.println(Arrays.toString(arr));
		buble(arr, length);
		System.out.println(Arrays.toString(arr));
	}

}
