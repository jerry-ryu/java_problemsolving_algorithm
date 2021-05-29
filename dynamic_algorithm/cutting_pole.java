package dynamic_algorithm;

import java.util.ArrayList;
import java.lang.Math;

public class cutting_pole {
	
	private static int cutpole(int[]value, int length) {
		
		ArrayList<Integer> max_value = new ArrayList<Integer>();
		max_value.add(0);
		for(int i = 1; i<=length; i++) {
			int q = -1;
			for(int j = 1; j<=i; j++) {
				q = Math.max(q, value[j] + max_value.get(i-j));
			}
			max_value.add(q);
		}
		
		return max_value.get(length);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] value = {0,1,5,8,9,10,17,17,20,24,30};
		
		System.out.println(cutpole(value,2));
		System.out.println(cutpole(value,3));
		System.out.println(cutpole(value,4));
		System.out.println(cutpole(value,7));
		
	}

}
