package Circular_Queue.Queue1;

import java.util.Arrays;

public class MainClassPre {
	public static void main(String[] args) {
		Practice p = new Practice(4);
		
		p.input(10);
		p.input(20);
		p.input(30);
		p.input(40);
		p.moveFront();
		p.input(40);
		p.moveFront();
		p.input(50);
		p.moveFront();
		p.input(60);
		System.out.println(Arrays.toString(p.arr));
	}
}
