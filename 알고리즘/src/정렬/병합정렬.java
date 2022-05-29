package 정렬;

import java.util.Arrays;

public class 병합정렬 {
	
	void sort(int[] arr, int first, int last) {
		if(first < last) {
			int mid = (first + last) / 2;
	
			sort(arr, first, mid);
			sort(arr, mid+1, last);
			merge(arr, first, mid, last);
			System.out.println(Arrays.toString(arr));
		}
	}
	
	void merge(int[] arr, int f, int m, int last) {
		int[] temp = new int[arr.length];
		int sfirst = f;
		int slast = m+1;
		int tmpIdx = f;
		
		while ( (sfirst <= slast) && (slast <= last) ) {
			if ( arr[sfirst] < arr[slast] ) {
				temp[tmpIdx] = arr[sfirst];
				tmpIdx++;
				sfirst++;
			} else {
				temp[tmpIdx] = arr[slast];
				tmpIdx++;
				slast++;
			}
		}
		
		while ( sfirst <= m ) {
			temp[tmpIdx] = arr[sfirst];
			sfirst++;
			tmpIdx++;
		}
		
		while ( slast <= last ) {
			temp[tmpIdx] = arr[slast];
			slast++;
			tmpIdx++;
		}
		
		for ( int i=f; i<=last; i++ ) {
			arr[i] = temp[i];
		}
	}
	
	public static void main(String[] args) {
		
		병합정렬 mergeSort = new 병합정렬();
		int[] arr = {40,20,50,10,30,70};
		
		mergeSort.sort(arr, 0, arr.length-1);
	}
}
