package hashTable;

import java.util.LinkedList;

class HashTable2{
	class Node{
		private String key;
		private String value;
		Node(String key, String value){
			this.key = key;
			this.value = value;
		}
		
		public void setKey(String key) {
			this.key = key;
		}
		
		public String getKey(String key) {
			return key;
		}
	}
	
	LinkedList<Node>[] datas;
	
	HashTable2(int size){
		datas = new LinkedList[size];
	}
	
	public void put(String key, String value) {
		int hashcode = getHashcode(key);
		int index = getIndex(hashcode);
		
		LinkedList<Node> list = datas[index];
		if ( list == null ) {
			list = new LinkedList<Node>();
			datas[index] = list;
		}
		
		Node node = searchNode(list, key);
		if ( node == null ) {
			list.add(new Node(key, value));
		} else {
			node.value = value;
		}
	}
	
	public String get(String key) {
		int hashcode = getHashcode(key);
		int index = getIndex(hashcode);
		
		LinkedList<Node> list = datas[index];
		Node node = searchNode(list, key);
		return node == null ? "Not found" : node.value;
	}
	
	private int getHashcode(String key) {
		int hashcode = 0;
		for(char c : key.toCharArray()) {
			hashcode += c;
		}
		return hashcode;
	}
	
	private int getIndex(int hashcode) {
		return hashcode % datas.length;
	}
	
	private Node searchNode(LinkedList<Node> list, String key) {
		if ( list == null ) return null;
		for ( Node node : list ) {
			if ( node.key.equals(key) ) {
				return node;
			}
		}
		return null;
	}
}


public class HashTableTest1 {

	public static void main(String[] args) {
		HashTable2 h = new HashTable2(5);
		h.put("nakachi", "amie aiko");
		h.put("서", "일덕");
		h.put("이", "춘우");
		System.out.println(h.get("nakachi"));
	}

}
