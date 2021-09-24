package 마방진;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int deg;
		while(true){
			try{
				System.out.print("마방진의 차수를 입력하세요. : ");
				deg = scan.nextInt();

				if(deg%2==1)break;
				System.out.println("홀수만 입력해야 합니다.");

			}catch(InputMismatchException e){
				System.out.println("숫자만 입력해야 합니다.");
				return;
			}
		}
        scan.close();

		int array[][] = new int[deg][deg];
		int row,col;
		int cnt;

		row=0;
		col=(deg/2);  //마방진을 시작할 위치 지정

		for(cnt=1; cnt<=deg*deg; cnt++){
			array[row][col]=cnt;
			if(cnt%deg==0){
				row++;
				if(row==deg)row=0;

			}else{
				row--;col++;
				if(row==-1)row=deg-1;
				if(col==deg)col=0;

			}

		}

		//출력
		for(row=0;row<=deg-1;row++){
			for(col=0;col<=deg-1;col++){
				System.out.printf("%3d",array[row][col]);
			}
			System.out.println();
		}
	}

}
