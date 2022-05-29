package LinkedList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

class NodeTest {
	LinkedList<Integer> node = null;
	
	NodeTest (int d) {
		node = new LinkedList<Integer>(Arrays.asList(d));
	}
	
	void append ( int d ) {
		node.add(d);
	}
	void remove ( int d ) {
		node.remove(node.indexOf(d));
	}
	
	void all() {
		int count = 0;
		Iterator<Integer> iter = node.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next());
			count ++;
			if(node.size() == count) break;
			System.out.print(" -> ");
		}
	}
	
	void removeOverlap() {
		HashSet<Integer> save = new HashSet<Integer>(node);
		System.out.print(save);
	}
}

public class Node {
	public static void main(String[] args) {
		NodeTest node = new NodeTest(1);
		node.append(5);
		node.append(2);
		node.append(3);
		node.append(4);
		node.append(3);
		node.append(3);
		node.append(3);
		node.append(4);
		node.all();
		System.out.println();
		node.removeOverlap();
	}
}
