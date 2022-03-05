package 정렬;

import java.util.Arrays;

public class 삽입정렬연습 {
	
	static void sorting(int[] arr) {
		int j;
		for (int i=1; i<arr.length; i++) {
			j = i;
			while ( j > 0 ) {
				if ( arr[j] > arr[j-1] ) {
					int tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
				}
				j--;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {5,3,8,6,7,1,9,4,2};
		sorting(arr);
		System.out.println(Arrays.toString(arr));
	}

}
