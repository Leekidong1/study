package 정렬;

import java.util.Arrays;

public class 퀵정렬 {
	
	private static void quickSort(int[] arr,int startIdx,int endIdx) {
		int partRight = partition(arr,startIdx,endIdx);
		if(startIdx < partRight - 1) {
			quickSort(arr,startIdx,partRight - 1);
		}
		if(partRight < endIdx) {
			quickSort(arr,partRight,endIdx);
		}
	}
	
	private static int partition(int[] arr,int startIdx,int endIdx) {
		int mid = arr[(startIdx+endIdx)/2];
		while(startIdx <= endIdx) {
			while(arr[startIdx] < mid) startIdx++;
			while(arr[endIdx] > mid) endIdx--;
			if(startIdx <= endIdx) {
				int temp = arr[startIdx];
				arr[startIdx] = arr[endIdx];
				arr[endIdx] = temp;
				
				startIdx++;
				endIdx--;
			}
		}
		return startIdx; 
	}
	
	public static void main(String[] args) {
		int[] arr = {3,9,4,7,5,0,1,6,8,2};
		int startIdx = 0;
		int endIdx = arr.length-1;
		
		quickSort(arr,startIdx,endIdx);
		
		System.out.println(Arrays.toString(arr));
	}

}
