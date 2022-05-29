package 여러문제들;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 탐색_dfs_bfs {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = 6;
		int line = 8;
		int startNum = 1;
		
		int[][] arr = new int[num+1][num+1];
		boolean[] check = new boolean[num+1];
		
//		for(int i=0; i<line; i++) {
//			int v1 = sc.nextInt();
//			int v2 = sc.nextInt();
//			
//			arr[v1][v2] = 1;
//			arr[v2][v1] = 1;
//		}
		arr[1][2] = 1;
		arr[2][1] = 1;
		arr[1][5] = 1;
		arr[5][1] = 1;
		arr[2][3] = 1;
		arr[3][2] = 1;
		arr[2][4] = 1;
		arr[4][2] = 1;
		arr[2][5] = 1;
		arr[5][2] = 1;
		arr[3][4] = 1;
		arr[4][3] = 1;
		arr[4][5] = 1;
		arr[5][4] = 1;
		arr[4][6] = 1;
		arr[6][4] = 1;
		
		dfs(arr,check,startNum);
//		bfs(arr,check,startNum);
	}
	
	public static void dfs(int[][] a, boolean[] check, int startNum){ // 깊이우선탐색
		Stack<Integer> stack = new Stack<Integer>();
		boolean flag;
		int num = a.length-1;
		
		stack.push(startNum);
		check[startNum] =true;
		System.out.println(startNum+" ");
		
		while(!stack.isEmpty()) {
			int startNum1 = stack.peek();
			flag = false;
			
			for(int i=1; i<=num; i++) {
				
				if(a[startNum1][i] == 1 && check[i] == false) {
					stack.push(i);
					System.out.println(i+" ");
					
					check[i] = true;
					flag = true;
					break;
				}
			}
			
			if(flag == false) {
				stack.pop();
			}
		}
		
	}
	public static void bfs(int[][] a, boolean[] check, int startNum){  // 너비우선탐색
		Queue<Integer> q = new LinkedList<>(); // Queue알고리즘에 LinkedList 씌운것.
		int num = a.length-1;
		
		q.add(startNum);
		check[startNum] =true;
		
		while(!q.isEmpty()) {
			startNum = q.poll();
			System.out.println(startNum+" ");
			
			for(int i=1; i<=num; i++) {
				if(a[startNum][i] ==1 && check[i] == false) {
					q.add(i);
					check[i] = true;
				}
			}
		}
	}
}
