package dynamic_algorithm;

import java.lang.Math;

public class Longest_Common_Subsequence {

	private static int lcs(String a, String b) {
		
		StringBuffer A = new StringBuffer(a);
		StringBuffer B = new StringBuffer(b);
		
		int[][] mem = new int[A.length()+1][B.length()+1];
		
		for(int i = 1; i<=A.length(); i++) {
			for(int j = 1; j<=B.length(); j++) {
				if(A.charAt(i-1)==B.charAt(j-1)) {
					mem[i][j] = 1 + mem[i-1][j-1];
				}else {
					mem[i][j] = Math.max(mem[i][j-1],mem[i-1][j]);
				}
			}
		}
		
		
		return mem[A.length()][B.length()];
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(lcs("ABCBDAB", "BDCABA"));
	}

}
