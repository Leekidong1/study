package stack;

class StackClass<T>{
	class Node<T> {
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
		if ( top == null ) {
			return (T) "stack에 값이 없습니다.";
		}
		T item = top.data;
		top = top.prev;
		return item;
	}
	
	public T peek() {
		if ( top == null ) {
			return (T) "stack에 값이 없습니다.";
		}
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
}

public class StackTest {

	public static void main(String[] args) {
		StackClass<Integer> s = new StackClass<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		System.out.println(s.pop());
	}
}
