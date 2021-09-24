package ChainHashtable;

/*
해시 테이블에 유지하려는 데이터의 예
인스턴스 고유의 hashCode ()를 돌려주는 것으로,
equals () 메소드를 구현하고있는 것.
이들은 Object 클래스에 구현이 있지만,
무시하고 구현한다.
그렇게함으로써 설계자가 의도 한대로
동작을 바꿀 수있다. 
 */
public class MyData {
	
	private final String name;
	
	public MyData(String x) {
		name = x;
	}
	 
	public String getName() {
		return name;
	}
	public boolean equals(Object x) { 
//		MyData y;
//		try {
//			y = (MyData) x;
//		} catch (ClassCastException e) {
//			return false;
//		}
//		return this.name.equals(y.getName());
		if(x instanceof MyData) {	// x객체가 MyData 클래스 타입이라면,
			return this.name.equals(((MyData) x).getName()); // MyData의 name과 x객체의 getName()은 같다. == true
		}
		return false;
	}
 
	public int hashCode() { //  hashCode 오버라이딩해서 name의 hashCode를 다시 정의해준다. 
		int intkey = 0;
		for (byte e : name.getBytes()) {  //문자열에서 각 문자들을 byte로 변환한다.
			intkey += e; // 각 문자들의 Ascii코드 값을 더해준다.
		}
		return intkey; // name를 byte값으로 변경하여 정수값으로 return
	}
	public String toString() { // toString 오버라이딩해서 name값으로 재정의한다.
		return name;
	} 
}
