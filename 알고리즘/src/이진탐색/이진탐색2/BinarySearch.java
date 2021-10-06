package 이진탐색.이진탐색2;


public class BinarySearch {

    /*
     * 테이블 항목
     */
    static private class Entry {

        int key; // 비교의 대상이되는 키
        Object data; // 정보

        /**
         * 항목을 생성
         *
         * @param key 키
         * @param data 키 key에 대응하는 데이터
         */
        private Entry (int key, Object data)
        {
            this.key = key;
            this.data = data;
        }
    }

    
    final static int MAX = 5; // 데이터의 최대 개수
    Entry []table = new Entry[MAX]; // 데이터를 저장하는 배열
    int n = 0; // table에 등록되어있는 데이터의 개수

    /*
     * 키 key에 대응하는 데이터를 찾아
     *
     * @param key 키
     * @return 키 key에 대응하는 데이터. 키가 없으면 null을 반환
     */
    public Object search (int key)
    {
        int low = 0; 				
        int high = n - 1; 			

        while (low <= high) {		
            int middle = (low + high) / 2; 	// 중간으로 나눈다
            if (key == table[middle].key) 
                return table[middle].data; // 발견
            else if (key < table[middle].key) 
                high = middle - 1; 
            else // key> table [middle] .key이다
                low = middle + 1; 
        }
        return null; // 찾을 수 없습니다
    }
    
    public static void main(String[] args) {
    	
    	BinarySearch bs = new BinarySearch();
    	
    	bs.table[0] = new Entry(0, "aaaaa");
    	bs.n++;
    	bs.table[1] = new Entry(2, "bbbbb");
    	bs.n++;
    	bs.table[2] = new Entry(3, "ccccc");
    	bs.n++;
    	bs.table[3] = new Entry(6, "ddddd");
    	bs.n++;
    	bs.table[4] = new Entry(8, "eeeee");
    	bs.n++;
    	
    	String findObj = (String)bs.search(6);
    	System.out.println(findObj);
    	
    } 
    
}