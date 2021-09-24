package Circular_Queue.Queue1;

import java.util.Arrays;

/*
	원형큐 (Circular Queue)
	원형 큐는 선형 큐의 문제점을 보완하기 위한 자료구조입니다. 
	앞선 포스팅에서 선형큐의 문제점은 rear이 가르키는 포인터가 배열의 마지막 인덱스를 가르키고 있을 때 앞쪽에서 Dequeue로 발생한 배열의 빈 공간을 활용 할 수가 없었습니다. 
	원형큐에서는 포인터 증가 방식이 (rear+1)%arraysize 형식으로 변환하기 때문에 배열의 첫 인덱스부터 다시 데이터의 삽입이 가능해집니다.
	
*/

public class mainClass {

	public static void main(String[] args) {
		
		CircularQueue cq = new CircularQueue(4);
		
		boolean b = cq.Isempty();
		if(b == true) {
			System.out.println("큐 공간이 비어 있습니다");
		}
		
		cq.EnQueue(111);
		cq.EnQueue(222);
		cq.EnQueue(333);
		cq.DeQueue();
		cq.EnQueue(444);
		
		System.out.println(Arrays.toString(cq.circular_Queue));


	}

}
