package 설탕배달;

public class 설탕배달 {
	/*
	 * 사탕가게에 N키로그램 배달
	 * 3키로 봉지, 5키로 봉지
	 * 목적 : 적은 봉지수 배달
	 * 예시: 18키로 설탕 => 5키로 3개 3키로 1개
	 * 3키로 6개 / 5키로 3개,3키로1개 
	 */
	public static void main(String[] args) {
		int kg = 4;
		int[] bag = {3,5};
		int result = 0;
		int count3kg = 0;
		int count5kg = 0;
		
		while(result>-1) {
			result = kg - (bag[0]*count3kg);
			 
			count5kg = result/bag[1];
			 
			if(result%bag[1] == 0) {
				int total = count3kg+count5kg;
				System.out.println(total);
				return;
			}
			count3kg++;
		}
	}

}


class CodeRunner{
	public static void main(String[] args){
        
        int sugar = 3000; 
        
        if(sugar%5 ==0) { 
            System.out.println(sugar/5); 
            return; 
        }else { 
            int five = sugar/5; 
            for(int i=five; i>0; i--) { 
                int tempsugar = sugar-(i*5); 
                if(tempsugar %3 == 0) { 
                    System.out.println(i+(tempsugar/3)); 
                    return; 
                } 
            } 
        } 
        
        if(sugar%3==0) { 
            System.out.println(sugar/3); 
        }
        else { 
            System.out.println(-1); 
        }
        
	}
}