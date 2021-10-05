package 정렬;

import java.util.Arrays;

public class 선택정렬 {
	/*	정의 : 주어진 리스트 중에 최소값을 찾고, 그 값을 맨 앞에 위치한 값과 교체한다. 이후 맨 처음 위치를 뺀 나머지 리스트를 같은 방법으로 교체한다.
	 * 	<선택정렬 장단점> 
	 * 		장점 : 교환 횟수는 상당히 적다. 따라서 교환이 많이 이루어져야 하는 역순 정렬같은 자료 상태에서 가장 효율적
	 * 		단점 : 정렬을 위해 비교하는 횟수는 많다
	 */
	public static void main(String[] args) {
		int[] data = {1,2,3,4,5};
		
		int temp = 0;
		for(int i=0; i<data.length-1; i++) {
			for(int j=i+1; j<data.length; j++) {
				if(data[i] < data[j]) {	// 기호가 > 이면 오름차순이고, 기호가 <이면 내림차순 이다.
					temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(data));
	}

}
