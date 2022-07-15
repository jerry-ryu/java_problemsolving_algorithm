package study;

import java.util.Scanner;	

public class Æç¸°µå·Ò {
	
	public static void main(String args[]) {
		Scanner sc  = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		int len = str.length();
		boolean bool = true;
		
		for(int i = 0; i<len/2; i++) {
			if(str.charAt(i) != str.charAt(str.length() -i-1)){
				bool = false;
			}
		}
		
		if(bool) {
			System.out.println(1);
		}else {
			System.out.println(-1);
		}
	}
}
