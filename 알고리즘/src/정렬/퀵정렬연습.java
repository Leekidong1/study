package 정렬;

import java.util.Arrays;

public class 퀵정렬연습 {
	
	static void quickSort(int[] arr, int start, int end) {
		int right = sort(arr, start, end);
		if ( start < right - 1 ) {
			quickSort(arr, start , right - 1);
		}
		if ( right < end ) {
			quickSort(arr, right, end);
		}
	}
	
	static int sort(int[] arr, int start, int end) {
		int mid = arr[(start + end) / 2];
		while ( start <= end ) {
			while(arr[start] < mid) start++;
			while(arr[end] > mid) end--;
			if ( start <= end ) {
				swap(arr, start, end);
				start++;
				end--;
			}
		}
		return start;
	}
	
	static void swap(int[] arr, int start, int end) {
		int tmp = arr[start];
		arr[start] = arr[end];
		arr[end] = tmp;
	}
	
	public static void main(String[] args) {
		int[] arr = {3,9,4,7,5,0,1,6,8,2};
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
