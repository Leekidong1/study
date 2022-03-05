package tree;

class Binary1{
	
	int[] arr = {1,2,3,4,5,6,7,8,9};
	
	public int searchNum(int num) {
		int first = 0;
		int end = arr.length-1;
		int result = 0;
		while ( end > first ) {
			int mid = (first+end) / 2;
			
			if ( arr[mid] == num ) {
				result = mid;
				break;
			}
			
			if ( arr[mid] > num ) {
				end = mid-1; 
			} else {
				first = mid+1;
			}
		}
		return result;
	}
}



public class BinarySearch1 {

	public static void main(String[] args) {
		Binary1 b = new Binary1();
		System.out.println(b.searchNum(8));
	}

}
