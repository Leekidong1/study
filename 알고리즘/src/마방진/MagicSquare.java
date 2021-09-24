package 마방진;

public class MagicSquare {
	/*
	  	마방진 알고리즘
		가로, 세로, 대각선의 각 합이 모두 같도록 만들어진 정사각형 배열이라고 한다.
	*/

	public static void main(String[] main){
		
		int array[][] = new int[5][5];
		int row,col;
		int cnt;

		row=0;col=(5/2);  //마방진을 시작할 위치 지정

		for(cnt=1;cnt<=25;cnt++){
			array[row][col]=cnt;
			if(cnt%5==0){
				row++;
				if(row==5)row=0;
			}else{
				row--;col++;
				if(row==-1)row=4;
				if(col==5)col=0;
			}
		}

		//출력
		for(row=0;row<=4;row++){
			for(col=0;col<=4;col++){
				System.out.printf("%3d",array[row][col]);
			}
			System.out.println();
		}
	}

}
