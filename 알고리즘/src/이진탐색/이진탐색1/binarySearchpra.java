package 이진탐색.이진탐색1;

import java.util.Arrays;

public class binarySearchpra {

	public static void main(String[] args) {
		
		int array[] = { 45, 23, 7, 53, 14, 4, 0, 47, 10 };
		int num = 47;
		Arrays.sort(array);
		
		System.out.println(Arrays.toString(array));
		
		int min=0;
		int max = array.length-1;
		int mid = 0;
		
		while(min<=max) {
			
			mid = (min+max)/2;
			
			if(array[mid] == num) {
				break;
			}
			
			if(array[mid] < num) {
				min = mid + 1;
			}else if(array[mid] > num){
				max = mid - 1;
			}
			
			System.out.println("mid :"+ mid);
		}
		
		System.out.println("최종 인덱스 :"+ mid);
	}

}
