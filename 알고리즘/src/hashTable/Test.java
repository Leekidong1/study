package hashTable;

import java.util.LinkedList;

/*
 * hashtable 만들기
 * 순서	1. Node 테이블 생성
 * 		2. LickedList<Node> 배열 생성
 * 		3. **key로 hashcode 생성
 * 		4. **hashcode로 index 생성
 * 		5. key로 LickedList<Node>에 있는 node의 value값 찾는 함수생성  
 * 		6. put함수 생성
 * 		7. get함수 생성
 */
class HashTabel{
	// 1번 Node테이블 생성
	class Node{
		private String key;
		private String value;
		
		public Node( String key, String value ) {
			this.key = key;
			this.value = value;
		}
		
		String value() {
			return value;
		}
		
		void value( String value ) {
			this.value = value;
		}
	}
	
	// 2번 LinkedList 배열 생성
	LinkedList<Node>[] data;
	
	public HashTabel(int size) {
		this.data = new LinkedList[size];
		
	}
	
	// 3번 key로 hashcode 생성
	int getHashCode(String key) {
		int hashcode = 0;
		for(char c : key.toCharArray()) {
			hashcode += c;
		}
		return hashcode;
	}
	
	// 4번 hashcode로 index 생성
	int convertToIndex(int hashcode) {
		return hashcode % data.length;
	}
	
	// 5번 key로 LickedList에 있는 node의 value값 찾는 함수생성
	Node searchKey(LinkedList<Node> list, String key) {
		if ( list == null ) return null;
		for(Node node : list) {
			if(node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}
	
	// 6번 put함수 생성
	void put(String key, String value) {
		int index = getIndex(key);
		LinkedList<Node> list = data[index];
		if ( list == null ) {
			list = new LinkedList<Node>();
			data[index] = list;
		}
		Node node = searchKey(list, key);
		if ( node == null ) {
			list.add(new Node(key, value));
		} else {
			node.value = value;
		}
	}
	
	// 7번 gut함수 생성
	String get(String key) {
		int index = getIndex(key);
		LinkedList<Node> list = data[index];
		Node node = searchKey(list, key);
		return node == null ? "Not found" : node.value();
	}
	
	// util - key로 index가져오기
	int getIndex(String key) {
		int hashcode = getHashCode(key);
		return convertToIndex(hashcode);
	}
}

public class Test {
	public static void main(String[] args) {
		HashTabel h = new HashTabel(3);
		h.put("sung", "She is pretty");
		h.put("jin", "She is a model");
		h.put("hee", "She is an angel");
		h.put("min", "She is cute");
		h.put("sung", "She is beautiful");
		System.out.println(h.get("sung"));
		System.out.println(h.get("jin"));
		System.out.println(h.get("hee"));
		System.out.println(h.get("min"));
		System.out.println(h.get("jae"));
	}
}
