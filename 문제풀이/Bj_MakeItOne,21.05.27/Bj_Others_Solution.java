package ProgrammersBj.Bj_MakeItOne;

import java.util.Scanner;

public class Bj_Others_Solution {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt(), dp[] = new int[N + 1];

		for (int i = 2; i <= N; ++i)
			/*
			 * 기본적으로 작동 알고리즘은 같지만, 삼항 연산자를 활용하여 획기적으로 짧게 코드를 구현했다.
			 */
			dp[i] = Math.min(dp[i - 1], Math.min(i % 3 == 0 ? dp[i / 3] : N + 1, i % 2 == 0 ? dp[i / 2] : N + 1)) + 1;

		System.out.println(dp[N]);
	}
}
