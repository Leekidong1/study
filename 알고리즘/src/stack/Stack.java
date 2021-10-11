package stack;

public class Stack {
	
	private int[] arr; 
	private int top;
	
	public Stack(int length) {
		this.arr = new int[length];
		top = 0;
	}
	
	public void push(int num) {
		if(top < arr.length) {
			arr[top] = num;
			top++;
		}else {
			System.out.println("full of Stack");
			return;
		}
		for(int node : arr) {
			System.out.print(node+" ");
		}
		System.out.println(" index : "+ top);
	}
	public void pop() {
		if(top > 0) {
			System.out.println(top-1+"번 째 값인"+arr[top-1]+"을 pop합니다.");
			arr[--top] = 0;
		}else {
			System.out.println("no more datas");
			return;
		}
		for(int node : arr) {
			System.out.print(node+" ");
		}
		System.out.println(" index : "+ top);		
	}
	public int peek() {
		if(arr != null) {
			return arr[top-1];
		}
		return -1;
	}
	public void isEmpty() {
		if(top == 0) {
			System.out.println("Stack이 비어있습니다.");
		}else {
			System.out.println("Stack에 값들이 들어있습니다");
		}
	}
	
	public static void main(String[] args) {
		Stack arr = new Stack(5);
		arr.push(4);
		arr.push(3);
		arr.push(2);
		arr.push(1);
		arr.push(6);
		arr.pop();
		arr.pop();
		arr.pop();
		int peek = arr.peek();
		System.out.println("peek번호 : "+ peek);
		arr.pop();
		arr.isEmpty();
	}
}
