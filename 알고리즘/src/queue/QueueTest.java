package queue;

class QueueClass<T>{
	class Node<T>{
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	private Node<T> first;
	private Node<T> last;
	
	public void add(T data) {
		Node<T> node = new Node<T>(data);
		if( last != null) {
			last.next = node;
		}
		last = node;
		if( first == null ) {
			first = last;
		}
	}
	
	public T remove() {
		if ( first == null ) return null;
		T data = first.data;
		if ( first.next != null )System.out.println("앞 : " + data + ", 뒤 : " + first.next.data);
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
	
	public boolean isEmtpy() {
		return first == null;
	}
}

public class QueueTest {
	public static void main(String[] args) {
		QueueClass<Integer> q = new QueueClass<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.isEmtpy());
		System.out.println(q.remove());
		System.out.println(q.isEmtpy());
	}
}
