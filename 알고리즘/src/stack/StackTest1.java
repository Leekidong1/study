package stack;

class Stack1<T>{
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


public class StackTest1 {
	public static void main(String[] args) {
		Stack1<Integer> s = new Stack1<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
	}
}
