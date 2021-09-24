package Circular_Queue.Queue1;

public class Practice {
	int front = 0;
	int rear = 0;
	int maxsize = 0;
	int[] arr;
	
	Practice(int num){
		this.maxsize = num;
		arr = new int[maxsize];
	}
	
	public boolean isFull() {
		if((rear+1)%maxsize == front) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isEmpty() {
		if(rear == front) {
			return true;
		}else {
			return false;
		}
	}
	
	public void input(int input) { // EnQueue
		if(isFull()) {
			System.out.println("배열이 모두 찼습니다.");
			return;
		}
		
		rear = (rear+1)%maxsize;
		arr[rear] = input;
		System.out.println("첫번째idx :"+front+" 마지막idx :"+rear);
	}
	
	public int moveFront() { // DeQueue
		if(isEmpty()) {
			System.out.println("배열이 비어있습니다.");
			return -1;
		}
		
		front = (front+1)%maxsize;
		return arr[front];
	}
}
