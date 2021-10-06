package Circular_Queue.Queue2;

import java.util.Arrays;

public class mainClass {

	public static void main(String[] args) {
		
		CircularQueue cq = new CircularQueue();
		cq.newQueue(4);
		
		cq.enqueue('A');
		cq.enqueue('B');
		cq.enqueue('C');
		cq.enqueue('D');
		cq.peek();
		cq.dequeue();
		cq.peek();
		System.out.println(Arrays.toString(cq.QArray));
	}

}
