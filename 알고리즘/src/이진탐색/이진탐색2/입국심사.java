package 이진탐색.이진탐색2;

import java.util.Arrays;

public class 입국심사 {

	public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = (long) n * times[times.length - 1]; //가장 최악의 경우의(오래걸리는) 시간
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0; // 총 심사한 인원
            for (int i = 0; i < times.length; i++) { //빨리 심사하는 심사관 순으로 심사처리
                sum += mid / times[i];
            }
            System.out.println("총검사인원 :"+sum);
            System.out.println("최소시간 :"+left);
            System.out.println("찾는최소시간 :"+mid);
            System.out.println("최대시간 :"+right);
            if (sum < n) { // 해야할 인원보다 심사처리 못함 -> 시간 더 필요
                left = mid + 1;
            } else { // 해야할 인원보다 심사처리 많이함 -> 시간을 줄여서 더 최고 경우의 시간을 만든다.
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
	

	public static void main(String[] args) {
		입국심사 a = new 입국심사();
		int[] times = {7,10};
		
		long answer = a.solution(6, times);
		System.out.println(answer);
	}

}
