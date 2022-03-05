package stack;

class Stack3<T>{
	class Node<T>{
		private T data;
		private Node<T> prev;
	}
	
	Node<T> top;
	
	public void push(T data) {
		Node<T> node = new Node<T>();
		node.prev = top;
		node.data = data;
		top = node;
	}
	
	public T pop() {
		if ( top == null ) return null;
		T data = top.data;
		top = top.prev;
		return data;
	}
	
	public T peek() {
		if ( top == null ) return null;
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

public class StackTest3 {
	public static void main(String[] args) {
		Stack3<Integer> s = new Stack3<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}
