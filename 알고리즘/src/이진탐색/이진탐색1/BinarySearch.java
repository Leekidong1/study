package 이진탐색.이진탐색1;

import java.util.Arrays;

public class BinarySearch {

	static int binarySearch(int[] arr, int num) {
		Arrays.sort(arr);
		

		int l = 0, r = arr.length-1;

		while(l <= r) {
		    // 중간 위치 구함
			int mid = (l + r)/2;

		    // 위치를 찾았을 경우
			if(arr[mid] == num) return mid;

		    // 중간 값이 찾고자하는 값보다 작을 경우 == 중간 값 보다 오른쪽에 위치해있다
		    if(arr[mid] < num) {
		    	l = mid + 1;
		    }else {	// 중간 값이 찾고자하는 값보다 큰 경우 == 중간 값 보다 왼쪽에 위치해있다
		    	r = mid - 1;
		    }
		    
		    System.out.println("mid:" + mid);
		}

		  // 찾는 값이 없을 경우 -1을 반환(-1이라는 index는 없기 때문)
		return -1;
	}
    
    public static void main(String[] args) {
    	
    	int array[] = { 0, 4, 7, 10, 14,  23, 45, 47, 53 };
    	
    	int mid = binarySearch(array, 47);
    	System.out.println("mid:" + mid);
    }
}




