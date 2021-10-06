package 알고리즘;

public class 대소문자변환 {

	public static void main(String[] args) {
		String str = "helloWrolD";
		
		String total="";
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if('a' <= ch && ch <= 'z') {
				total += (char)(ch - 32);
			}else if('A' <= ch && ch <= 'Z') {
				total += (char)(ch + 32);
			}
		}
		System.out.println(total);
	}

}
