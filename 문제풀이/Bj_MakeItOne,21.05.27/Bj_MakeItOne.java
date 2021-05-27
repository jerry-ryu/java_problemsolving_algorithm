package ProgrammersBj.MakeItOne;

public class Bj_MakeItOne {
	
	int[] arr; // DP 각 숫자를 1로 만들기 위한 최소 연산 수를 저장한 배열
	
	public void solution(int num) {
		if(num==1) {
			System.out.println(0); 
			return; // 1은 그 자체로 1
		}
		if(num==2 || num==3) {
			System.out.println(1);
			return; // 2/2 = 1, 3/3 = 1
		}
		
		arr = new int[num+1];
		arr[1] = 0;
		arr[2] = 1;
		arr[3] = 1;
		
		/*
		 *  i가 3으로 나눠지면 추가연산 x
		 *  i-1이 3으로 나눠지면 -1을 해줘야하기 때문에 추가연산 = 1
		 *  i-2이 3으로 나눠지면 -2을 해줘야하기 때문에 추가연산 = 2
		 *  
		 *  i가 2로 나눠지면 추가연산 x
		 *  i-1이 2로 나눠지면 -1을 해줘야하기 때문에 추가연산 = 1
		 *  
		 *  i를 3의 배수나 2의 배수로 만들어 2로 나누는것과 3으로 나누는 것중 
		 *  어떤 연산이 가장 작은 연산값을 가지는지 파악
		 *  
		 *  ex. 10 = 2의배수 -> arr[10/2],
		 *       10-1 => 3의 배수 -> arr[9/3] +1
		 *       을 비교하여 작은 값을 저장
		 */
		
		for(int i = 4; i<=num; i++) {
			if(i%3 ==0) { 
				if(i%2==0) { // i가 2로 나눠지면
					arr[i] = Math.min(arr[i/3], arr[i/2]); 
				}else { // i-1이 2로 나눠지면
					arr[i] = Math.min(arr[i/3], arr[(i-1)/2]+1); 
				}
			}else if(i%3 ==1) {
				if(i%2==0) {
					arr[i] = Math.min(arr[(i-1)/3]+1, arr[i/2]);
				}else {
					arr[i] = Math.min(arr[(i-1)/3]+1, arr[(i-1)/2]+1); 
				}
			}else {
				if(i%2==0) {// i-2이 3으로 나눠지면
					arr[i] = Math.min(arr[(i-2)/3]+2, arr[i/2]); 
				}else {
					arr[i] = Math.min(arr[(i-2)/3]+2, arr[(i-1)/2]+1); 
				}
			}
			
			arr[i]++; //2또는 3으로 나눈 연산 횟수 추가
		}
		
		System.out.println(arr[num]);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bj_MakeItOne sol = new Bj_MakeItOne();
		sol.solution(107);
	}

}
