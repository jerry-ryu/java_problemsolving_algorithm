package study;

//1339

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class two {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, ans = Integer.MIN_VALUE; // n은 단어의 개수
	private static String[] words; // 단어 배열
	private static List<Character> alphabetList = new ArrayList<>(); //존재하는 알파벳 리스트
	private static int[] val; //
	private static boolean[] visited = new boolean[10]; // 0~9까지의 서로 다른 숫자 방문했는지
	
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
		
		
		// 브루트 포스로 모든 조합 다 보기
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
