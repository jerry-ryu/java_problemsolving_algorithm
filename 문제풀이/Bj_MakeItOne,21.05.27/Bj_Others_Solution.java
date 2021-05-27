package ProgrammersBj.MakeItOne;

import java.util.Scanner;

public class Bj_Others_Solution {

	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt(), dp[] = new int[N + 1];

		for (int i = 2; i <= N; ++i)
			dp[i] = Math.min(dp[i - 1], Math.min(i % 3 == 0 ? dp[i / 3] : N + 1, i % 2 == 0 ? dp[i / 2] : N + 1)) + 1;

		System.out.println(dp[N]);
	}
}
