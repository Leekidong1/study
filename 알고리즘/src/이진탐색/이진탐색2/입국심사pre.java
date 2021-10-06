package 이진탐색.이진탐색2;

import java.util.Arrays;

public class 입국심사pre {
	
	public static void main(String[] args) {
		long result = 0;
		int[] times = {10,7};
		long ppl = 6;
		
		Arrays.sort(times);
		
		long mintime = 0;
		long maxtime = times[times.length-1] * ppl;
		long findtime = 0;
		
		while(mintime <= maxtime) {
			findtime = (mintime+maxtime)/2;
			long endppl = 0;
			
			for(int i=0; i<times.length; i++) {
				endppl += findtime/times[i];
			}
			
			if(endppl < ppl) {
				mintime = findtime + 1;
			}else {
				maxtime = findtime - 1;
				result = findtime;
			}
		}
		
		System.out.println("답 :"+result);
		
	}
}
