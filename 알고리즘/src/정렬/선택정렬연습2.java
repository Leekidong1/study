package 정렬;

import java.util.Arrays;

public class 선택정렬연습2 {
	
	static void sorting(int[] arr) {
		for (int i=0; i<arr.length-1; i++) {
			for (int j=i+1; j<arr.length; j++) {
				if(arr[i] > arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	} 
	
	public static void main(String[] args) {
		int[] arr = {5,3,8,6,7,1,9,4,2};
		sorting(arr);
		System.out.println(Arrays.toString(arr));
	}
}
