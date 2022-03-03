package queue;

class Queue1<T> {
	class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data){
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
		T node = first.data;
		first = first.next;		
		return node;
	}
	
	public T peek() {
		if ( first == null ) return null;
		return first.data;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}


public class QueueTest1 {
	public static void main(String[] args) {
		Queue1<Integer> q = new Queue1<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.err.println(q.isEmpty());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.err.println(q.isEmpty());
	}
}
