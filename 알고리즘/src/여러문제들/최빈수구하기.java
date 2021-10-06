package 알고리즘;

public class 최빈수구하기 {

	public static void main(String[] args) {
		
		int[] nums = {1,2,2,3,1,4,2,2,4,3,5,3,2};
		int[] check = new int[10];
		
		for(int i=0; i<nums.length; i++) {
			int index = nums[i];
			check[index] = check[index]+1;
		}
		for(int i=0; i<check.length; i++) {
			if(check[i] != 0)
			System.out.println(i+" "+check[i]);
		}
		
		int max = check[0];
		int num=0;
		for(int i=0; i<check.length; i++) {
			if(max<check[i]) {
				max = check[i];
				num = i;
			}
		}
		System.out.println("최빈수 :"+num+", 횟수 :"+max);
	}

}
