package ProgrammersBj.Bj_WineTasting;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MySolution {
	
	public void solution(int n, int[] wine) {
		int[] dp = new int[n+1]; //
		int answer=0;
		
		if(n == 1 || n==2) {
			for(int i = 0; i<n; i++) {
				answer += wine[i];
			}
			System.out.print(answer);
			return;
		}
		
		dp[0] = 0; //포도주가 없을때 최대값 = 0
		dp[1] = wine[0]; //포도주가 1개일 때 최대값 = 1개 선택
		dp[2] = wine[0] + wine[1]; //포도주가 2개일 때 최대값 = 2개 모두 선택
		
		
		/* 포도주가 3개일 때부터, '연속으로 놓여 있는 3잔을 모두 마실 수는 없다.'
		 * 규칙에 의해, 3가지 행동중 하나를 할 수 있다.
		 * 1. 추가된(i번째) 포도주 선택 안하기 
		 * 		--> i번째 포도주가 추가되지 않을 경우와 최댓값이 같음
		 * 2. 추가된(i번째) 포도주 선택하기, i-1번째 포도주 선택 x
		 * 		--> 최댓값 = i-2번째 까지의 최댓값 + i번째 포도주
		 * 3. 추가된(i번째) 포도주와 i-1번째 포도주 선택하기, i-2번째 포도주 선택 x
		 * 		--> 최댓값 = i-3번째 까지의 최댓값 + i번째 포도주 + i-1번째 포도주
		 */
		for(int i = 2; i<n; i++) {
			dp[i+1] = 
			Math.max(dp[i],Math.max((wine[i] + wine[i-1] + dp[i-2]),(wine[i] + dp[i-1])));
		}
		
		System.out.print(dp[n]);
	}

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		//입력 빠르게 받기 위해 Scanner가 아닌 BufferedReader로 받음
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		MySolution sol = new MySolution();
		sol.solution(n,arr);
	}
}
