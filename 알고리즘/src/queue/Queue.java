package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {

	List<Integer> arr = new ArrayList<Integer>();
	
	public void enqueue(int num) {
		arr.add(num);
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println(" size : "+arr.size());
	}
	
	public int dequeue() {
		if(arr.isEmpty()) {
			System.out.println("Queue is empty");
		}
		int result = -1;
		try {
			result = arr.remove(0); 
		}catch(Exception e) {}
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println(" size : "+arr.size());
		return result;
	}
	
	public boolean isEmpty() {
		return arr.isEmpty();
	}
	
	public static void main(String[] args){
		Queue queue = new Queue();
		queue.enqueue(4);
		queue.enqueue(3);
		queue.enqueue(2);
		queue.enqueue(1);
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		System.out.println(queue.isEmpty());
	}

}
