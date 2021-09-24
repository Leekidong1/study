package ChainHashtable;

import java.util.LinkedList;
import java.util.List;

public class ChainHashtable<T>{
	private List<T>[] table; // 방마다 담을 List배열 선언
	
	public ChainHashtable(int max_size) {
		this.table = new List[max_size]; // List배열 길이정하기 (궁금?? 맞나?)
	}

	private int hashCode(T data) {	// 0,1,2 방 번호 만드는과정
		int hashcode = Math.abs(data.hashCode()); // 음수는 양수로 hashCode로 변환과정
		return hashcode % this.table.length; // (핵심**) hashCode에서 List 길이만큼 나누면 0,1,2중 값 1개 나옴
	}
	
	public boolean put(T data) { // 추가
		int hashcode = hashCode(data); // 0,1,2 방번호 중에서 번호 하나
		if (this.table[hashcode] == null) // 해당데이타 방번호에 List가 없다면, LinkedList를 만들어준다.
			this.table[hashcode] = new LinkedList<T>();
		this.table[hashcode].add(data); // 해당데이타 방번호에 List가 있다면, 데이터 add(추가)한다.
		return true;
	}
	
	public T get(T data) { // 검색
		List<T> list = this.table[hashCode(data)]; // data의 방번호에 해당하는 List를 가져옴
		if (null == list) return null;  // List없으면 null값 리턴
		for (T e : list) // List에 있는 값과 찾는 data가 같다면 해당 List를 return;
			if (e.equals(data)) 
				return e;
		return null;
	}

	public boolean remove(T data) { // 삭제
		List<T> list = this.table[hashCode(data)]; // data의 방번호에 해당하는 List를 가져옴.
		if (null == list) return false; // List없으면 false 리턴;
		return list.remove(data); // 가져온 List에서 data를 삭제
	}
	
	public String toString() {
		 StringBuffer sb = new StringBuffer();	// 일반String은 이미만든 객체 수정불가. Buffer은 가능.
		 int index = 0;
		 for (List<T> list : this.table) { // 0,1,2번방 돌면서 List가져옴
			 sb.append('[').append(index++).append("]"); // 0,1,2번방을 => [0],[1],[2]으로 표기
			 if (list != null) 
				for (T e : list) // List 안에 모든데이터(서울,강원도...등) 돌면서 찾는다.
					sb.append(" -> ").append(e.toString()); // "-> 데이터명" 형태로 데이터 출력
			 		sb.append('\n'); // 개행해준다.
		 }
		 return sb.toString(); // 궁금?? 뭐지?? 예상-> 수정된 글을 return 함.
	}
}
