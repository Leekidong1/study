package Circular_Queue.Queue2;

import java.util.Scanner;

class User{
	
	private int userNum;
	private String id;
	private String pw;
	
	User(int userNum, String id, String pw){
		this.userNum = userNum;
		this.id = id;
		this.pw = pw;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", id=" + id + ", pw=" + pw + "]";
	}
	
}



public class Practice {
	static Scanner scn = new Scanner(System.in);
	User[] list;
	int front;
	int rear;
	
	Practice(int idx){
		list = new User[idx];
		front = 0;
		rear = 0;
	}
	
	public void input() {
		
		if(isFull()) {
			System.out.println("배열이 모두 꽉찼습니다.");
			return;
		}
		
		System.out.println("<회원정보입력>");
		System.out.print("ID :");
		String id = scn.next();
		System.out.print("비밀번호 :");
		String pw = scn.next();
		
		rear = (rear+1)%list.length;
		list[rear] = new User(rear,id,pw);
		
	}
	
	public void search() { // 이진검색
		System.out.println("<검색회원번호>");
		System.out.print("회원번호 입력:");
		int num = scn.nextInt();
		
		int min=0; // 최소 index 
		int max=list.length-1; // 최고 index 
		int mid=0; // 가운데 index 
		while(min<=max) { // 최대인덱스가 최소인덱스보다 크거나 같을때까지 찾는다.
			mid = (min+max)/2; // 가운데 index 
			System.out.println("mid :"+mid);
			if(list[mid].getUserNum() == num) { // 가운데인덱스의 회원번호가 찾는 회원번호가 같다면 출력한다.
				System.out.println(list[mid].toString());
				return;
			}else if(list[mid].getUserNum() < num) { // 찾는 회원번호가 가운데인덱스의 회원번호보다 크다면
				min = mid + 1; // 가운데인덱스의 다음인덱스가 최소인덱스로 설정된다.
			}else { // 찾는 회원번호가 가운데인덱스의 회원번호보다 작다면, 가운데인덱스 이전 인덱스가 최고인덱스로 설정된다. 
				max = mid - 1;
			}			// 결론 : 찾는 인덱스의 범위가 반씩 줄여간다.
		}
		
		System.out.println("검색하신 회원번호가 존재하지 않습니다.");
	}
	
	public void print() {
		System.out.println("<전체회원정보>");
		for(int i=0; i<list.length; i++) {
			if(list[i] != null) {
			System.out.println(i+"번째 정보: "+list[i].toString());
			}
		}
	}
	
	public boolean isFull() {
		if((rear+1)%list.length == front) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		if(rear == front) {
			return true;
		}
		return false;
	}
	
	public User deQueue() {
		if(isEmpty()) {
			System.out.println("배열이 비어있습니다.");
			return null;
		}
		front = (front+1)%list.length;
		return list[front];
	}
	
	public static void main(String[] args) {
		Practice bsc = new Practice(4);
		
		while(true) {
			System.out.println("회원정보관리");
			System.out.println("1.회원등록");
			System.out.println("2.회원검색");
			System.out.println("3.회원출력");
			System.out.println("4.회원삭제");
			System.out.println("5.종료");
			System.out.print("입력(예:1)>>");
			String choose = scn.next();
			
			switch(choose) {
			case "1":bsc.input();break;
			case "2":bsc.search();break;
			case "3":bsc.print();break;
			case "4":bsc.deQueue();break;
			case "5": 
				System.out.println("시스템종료");
				System.exit(0);
			}
		}

	}

}
