package hashTable;

import java.util.LinkedList;

class HashTable1{
	class Node{
		private String key;
		private String value;
		
		Node(String key, String value){
			this.key = key;
			this.value = value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	LinkedList<Node>[] datas;
	
	public HashTable1(int size) {
		datas = new LinkedList[size];
	}
	
	private int getHashCode(String key) {
		int hashcode = 0;
		for ( char c : key.toCharArray() ) {
			hashcode += c;
		}
		return hashcode;
	}
	
	private int getIndex(int hashcode) {
		return hashcode % datas.length;
	}
	
	private Node searchKey(LinkedList<Node> list, String key) {
		if( list == null ) return null;
		for ( Node node : list ) {
			if ( node.key.equals(key) ) {
				return node;
			}
		}
		return null;
	}
	
	public void put(String key, String value) {
		int hashcode = getHashCode(key);
		int index = getIndex(hashcode);
		
		LinkedList<Node> list = datas[index];
		if ( list == null ) {
			list = new LinkedList<Node>();
			datas[index] = list;
		} 
		
		Node node = searchKey(list, key);
		if( node == null ) {
			list.add(new Node(key, value));
		} else {
			node.value = value;
		}
	}
	
	public String getValues(String key) {
		int hashcode = getHashCode(key);
		int index = getIndex(hashcode);
		
		LinkedList<Node> list = datas[index];
		Node node = searchKey(list, key);
		return node == null ? "Not found" : node.value;
	}
	
}


public class HashTableTest {
	public static void main(String[] args) {
		HashTable1 h = new HashTable1(5);
		h.put("lee", "kidong");
		h.put("jin", "miyoung");
		h.put("kim", "건우");
		System.out.println(h.getValues("hh"));
	}
}
