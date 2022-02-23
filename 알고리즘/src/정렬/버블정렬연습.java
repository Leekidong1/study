package 정렬;

import java.util.Arrays;

public class 버블정렬연습 {
	
	static int arr[] = {7,3,5,4,10,2,1,6,8,9};
	
	static void sort(int last) {
		if(last > 0) {
			for ( int i=0; i<last; i++ ) {
				if ( arr[i] > arr[i+1] ) {
					swap(i);
				}
			}
			sort(last-1);
		}
	}
	
	static void swap(int i) {
		int temp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = temp;
	}
	
	static void print() {
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		sort(arr.length-1);
		print();
	}
}
