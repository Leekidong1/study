package queue;

class Queue3<T>{
	class Node<T>{
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	Node<T> first;
	Node<T> last;
	
	public void enqueue(T data) {
		Node<T> node = new Node<T>(data);
		if ( last != null ) {
			last.next = node;
		}
		last = node;
		if ( first == null ) {
			first = last;
		}
	}
	
	public T dequeue() {
		if ( first == null ) {
			last = null;
			return null;
		}
		T data = first.data;
		first = first.next;
		return data;
	}
	
	public T peek() {
		if ( first == null ) {
			last = null;
			return null;
		}
		return first.data;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}

public class QueueTest3 {
	public static void main(String[] args) {
		
	}
}
