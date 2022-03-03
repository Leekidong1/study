package queue;

class Queue2<T> {
	class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	private Node<T> first;
	private Node<T> last;
	
	public void enqueue(T data) {
		Node<T> node = new Node<T>(data);
		if ( last != null) {
			last.next = node;
		}
		last = node;
		if ( first == null ) {
			first = last;
		}
	}
	
	public T dequeue() {
		if ( first == null ) return null;
		T data = first.data;
		first = first.next;
		if ( first == null ) {
			last = null;
		}
		return data;
	}
	
	public T peek() {
		if ( first == null ) return null;
		return first.data;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}


public class QueueTest2 {
	public static void main(String[] args) {
		Queue2<Integer> q = new Queue2<Integer>();
		q.enqueue(5);
		q.enqueue(4);
		q.enqueue(3);
		q.enqueue(2);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.isEmpty());
	}
}
