package 탐색;

import java.util.LinkedList;
import java.util.Stack;

class Queue<T>{
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
		if ( last != null) {
			last.next = node;
		}
		last = node;
		if ( first == null ) {
			first = last;
		}
	}
	
	public T remove() {
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
	
	public boolean isEmtpy() {
		return first == null;
	}
}

class Graph {
	
	class Node {
		int data;
		LinkedList<Node> adjacent;
		boolean marked;
		Node (int data) {
			this.data = data;
			this.marked = false;
			adjacent = new LinkedList<Node>();
		}
	}
	
	Node[] nodes;
	
	Graph(int size) {
		nodes = new Node[size];
		for (int i=0; i<size; i++) {
			nodes[i] = new Node(i);
		}
	}
	
	void addEdge(int i1, int i2) {
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		if ( !n1.adjacent.contains(n2) ) {
			n1.adjacent.add(n2);
		}
		if ( !n2.adjacent.contains(n1) ) {
			n2.adjacent.add(n1);
		}
	}
	
	void dfs() {
		dfs(0);
	}
	
	void dfs(int index) {
		Node root = nodes[index];
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.marked = true;
		while (!stack.isEmpty()) {
			Node r = stack.pop();
			for (Node n : r.adjacent) {
				if ( n.marked == false ) {
					n.marked = true;
					stack.push(n);
				}
			}
			visit(r);
		}
	}
	
	void bfs() {
		bfs(0);
	}
	
	void bfs(int index) {
		Node root = nodes[index];
		Queue<Node> queue = new Queue<Node>();
		queue.add(root);
		root.marked = true;
		
		while ( !queue.isEmtpy() ) {
			Node r = queue.remove();
			for ( Node n : r.adjacent ) {
				if ( n.marked == false ) {
					n.marked = true;
					queue.add(n);
				}
			}
			visit(r);
		}
	}
	
	void dfsR(Node r) {
		if ( r == null ) return;
		r.marked = true;
		visit(r);
		for ( Node n : r.adjacent ) {
			if ( n.marked == false ) {
				dfsR(n);
			}
		}
	}
	
	void dfsR(int index) {
		Node r = nodes[index];
		dfsR(r);
	}
	
	void dfsR() {
		dfsR(0);
	}
	
	void visit(Node n) {
		System.out.print(n.data + " ");
	}
}
/*
   0
  /
 1 -- 3    7
 |  / | \ /
 | /  |  5
 2 -- 4   \
           6 - 8
 */

public class DfsBfs {
	public static void main(String[] args) {
		Graph g = new Graph(9);
		// 위 그림보고 그래프 생성 (관계명시하기)
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 6);
		g.addEdge(5, 7);
		g.addEdge(6, 8);
		g.bfs(3);
	}
}
