package ProgrammersBj.Bj_MakeItOne;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_Improved_solution {

	public void solution(int num) {
		if (num == 1) {
			System.out.println(0);
			return; // 1은 그 자체로 1
		}

		int[] arr = new int[num + 1];

		for (int i = 2; i <= num; i++) {
			if (i % 3 == 0) {
				if (i % 2 == 0) {
					// 2로도 3으로도 나눠지는 경우
					// 2로 나눈 값, 3으로 나눈 값, -1한 값 중 최소
					arr[i] = 1 + Math.min(arr[i / 3], Math.min(arr[i / 2], arr[i - 1]));
				} else {
					// 3으로 나눠지는 경우
					// 3으로 나눈 값, -1한 값 중 최소
					arr[i] = 1 + Math.min(arr[i / 3], arr[i - 1]);
				}
			} else {
				if (i % 2 == 0) {
					// 2으로 나눠지는 경우
					// 2으로 나눈 값, -1한 값 중 최소
					arr[i] = 1 + Math.min(arr[i / 2], arr[i - 1]);
				} else {
					arr[i] = 1 + arr[i - 1];
				}
			}
		}

		System.out.println(arr[num]);
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		BJ_Improved_solution sol = new BJ_Improved_solution();
		sol.solution(n);
		
	}

}
