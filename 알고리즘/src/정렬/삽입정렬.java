package 정렬;

import java.util.Arrays;

public class 삽입정렬 {
	
	int[] arr = {1,10,5,8,7,6,4,3,2,9};
	
	void sort() {
		int j ;
		for(int i=1; i<arr.length; i++) {
			j = i;
			while(arr[j-1] > arr[j]) {
				swap(j);
				if(j > 0)j--;
			}
		}
	}
	
	void swap(int j) {
		int temp = arr[j];
		arr[j] = arr[j-1];
		arr[j-1] = temp;
	}
	
	void print() {
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		삽입정렬 s = new 삽입정렬();
		s.sort();
		s.print();
	}
}
