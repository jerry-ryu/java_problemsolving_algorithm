package study;

//1339

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class two {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, ans = Integer.MIN_VALUE; // n�� �ܾ��� ����
	private static String[] words; // �ܾ� �迭
	private static List<Character> alphabetList = new ArrayList<>(); //�����ϴ� ���ĺ� ����Ʈ
	private static int[] val; //
	private static boolean[] visited = new boolean[10]; // 0~9������ ���� �ٸ� ���� �湮�ߴ���
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		words = new String[N];
		for(int i = 0; i<N; i++) {
			words[i] = br.readLine();
			for(int j = 0; j<words[i].length(); j++) {
				if(!alphabetList.contains(words[i].charAt(j))){
					alphabetList.add(words[i].charAt(j));
				}
			}
		}
		
		val = new int[alphabetList.size()];
		
		dfs(0);
		System.out.println(ans);
		br.close();
	}
	
	private static void dfs(int depth) {
		if(depth == alphabetList.size()) {
			int sum = 0;
			for(int i = 0; i<N; i++) {
				int num = 0;
				for(int j = 0; j < words[i].length(); j++) {
					num *= 10;
					num += val[alphabetList.indexOf(words[i].charAt(j))];
				}
				sum += num;
			}
			ans = Math.max(ans,sum);
			return;
		}
		
		
		// ���Ʈ ������ ��� ���� �� ����
		for(int i = 0; i<= 9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			val[depth] = i;
			dfs(depth +1);
			val[depth] = 0;
			visited[i] = false;
		}
	}
	
}
