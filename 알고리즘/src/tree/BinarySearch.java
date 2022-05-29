package tree;

public class BinarySearch {
	
	int[] arr = {1,2,3,4,5,6,7,8,9,10};
	int num = 8;
	
	void search() {
		int start = 0;
		int end = arr.length-1;
		int result = 0;
		while ( start < end ) {
			int mid = (start + end) / 2;
			if ( num == arr[mid] ) {
				result = mid;
				break;
			}
			if ( arr[mid] > num ) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		BinarySearch b = new BinarySearch();
		b.search();
	}
}
