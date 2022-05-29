package 정렬;

import java.util.Arrays;

public class 선택정렬연습 {
	
	static int[] arr = {3,8,2,6,4,9,1};
	
	static void sorting() {
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i] > arr[j]) {
					swap(i,j);
				}
			}
		}
	}
	
	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static void print() {
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		print();
		sorting();
		print();
	}

}
