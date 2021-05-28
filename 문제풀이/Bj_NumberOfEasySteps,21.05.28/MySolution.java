package ProgrammersBj.Bj_NumberOfEasySteps;

import java.io.*;

public class MySolution {

	//목표 n번째 자리까지, 현재  now자리, arr: now-1번째 자리의 0~9로 시작하는 계단 숫자들의 개수
	public void solution(int n, int now, int[] arr) {
		
		//목표 자릿수에 도달하면, 1~9로 시작하는 값을 합침 (0으로 시작하는 숫자는 없으므로)
		if(n==now) {
			int answer=0;
			for(int i = 1; i<10;i++) {
				answer = (answer + arr[i])%1000000000;
			}
			System.out.print(answer);
			return;
		}
		
		//0~9로 시작하는 계단 숫자들의 개수를 위한 임시 배열
		int[] tmp = new int[10];
		
		for(int i = 0; i<10;i++) {
			//0으로 시작하면 그 전 수가 1이 될 수 밖에 없다
			if(i==0) {
				tmp[i] = arr[1];
				//9로 시작하면 그 전 수가 8이 될 수 밖에 없다
			}else if(i==9) {
				tmp[i] = arr[8];
				// 0이나 9가 아닌 , i로 시작하면, 그 전수가 i-1이나, i+1일 수 있다.
			}else {
				tmp[i] = (arr[i-1] + arr[i+1])%1000000000;
			}
		}
		// int의 범위 때문에 그리고 정답을 1,000,000,000으로 나눈 나머지를 출력하는것이기 때문에
		// %1000000000을 계산시마다 해준다.
		solution(n,now+1,tmp);
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] arr = {1,1,1,1,1,1,1,1,1,1};
		MySolution sol = new MySolution();
		sol.solution(n,1,arr);
	}

}
