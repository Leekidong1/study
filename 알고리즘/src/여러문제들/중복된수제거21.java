package 알고리즘;

public class 중복된수제거21 {

	public static void main(String[] args) {
		int[] arr = {1,2,3,1,1,2,3,4,5,5,6,8,7,5,10,12,11,11,10,14,13,16,40,32,16};
		boolean[] swit = new boolean[100];
		
		for(int i=0; i<arr.length; i++) {
			swit[arr[i]] = true;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(swit[i] == true) {
				System.out.print(i+" ");
			}
		}
	}

}
