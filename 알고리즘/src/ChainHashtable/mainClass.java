package ChainHashtable;

public class mainClass {
	
	private final static String[] test_data = {
			 "서울", "경기도", "강원도", "충청도", "경상도", "전라도", "제주도"};

	public static void main(String[] args) {
		
		System.out.println("데이터를 추가합니다");		
		ChainHashtable<String> hashtable1 = new ChainHashtable<String>(3);
		
		for (String e : test_data) 
			hashtable1.put(e);
		
		System.out.print(hashtable1.toString());
		
		System.out.println("--------------------------------");
		
		System.out.println("강원도를 삭제합니다");
		hashtable1.remove("강원도");
		
		System.out.print(hashtable1.toString());
		
		System.out.println("--------------------------------");
		
		System.out.println("데이터를 추가합니다");				
		ChainHashtable<MyData> hashtable2 = new ChainHashtable<MyData>(3);
		
		for (String e : test_data) 
			hashtable2.put(new MyData(e));
		
		System.out.print(hashtable2.toString());
		
		System.out.println("--------------------------------");
		
		System.out.println("강원도를 삭제합니다");		
		hashtable2.remove(new MyData("강원도"));
		System.out.print(hashtable2.toString()); 
	}

}
