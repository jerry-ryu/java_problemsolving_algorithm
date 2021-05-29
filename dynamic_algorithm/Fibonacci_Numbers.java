package dynamic_algorithm;

import java.util.ArrayList;

public class Fibonacci_Numbers {
	
	ArrayList<Integer> arr; //피보나치 수열을 저장한 배열
	int num;//출력할 개수
	
	Fibonacci_Numbers(int num){
		this.num = num;
		arr = new ArrayList<>();
		
		arr.add(0);
		arr.add(1);
		//피보나치 수열 생성
		for(int i=2; i<this.num; i++) {
			arr.add(arr.get(i-1) + arr.get(i-2));
		}
		
		//피보나치 수열 출력
		for(int i=0; i<this.num; i++) {
			System.out.print(arr.get(i));
			System.out.print(" ");
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Fibonacci_Numbers(10);
	}

}
