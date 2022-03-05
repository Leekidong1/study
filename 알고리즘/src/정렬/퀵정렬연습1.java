package 정렬;

import java.util.Arrays;

public class 퀵정렬연습1 {

	static void quickSort(int[] arr, int start, int end) {
		int mid = sorting(arr, start, end);
		if ( start < mid-1 ) {
			quickSort(arr, start, mid-1);
		}
		if ( mid < end ) {
			quickSort(arr, mid, end);
		}
	}
	
	static int sorting(int[] arr, int start, int end) {
		int mid = arr[(start + end) / 2];
		while(start <= end) {
			while(arr[start] < mid) start++;
			while(arr[end] > mid) end--;
			if( start <= end ) {
				int tmp = arr[start];
				arr[start] = arr[end];
				arr[end] = tmp;
				start++;
				end--;
			}
		}
		return start;
	}
	
	public static void main(String[] args) {
		int[] arr = {3,9,4,7,5,0,1,6,8,2};
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

}
