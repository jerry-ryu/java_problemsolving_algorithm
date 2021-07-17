package ProgrammersBj.Bj_Virus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import MST.UnionFind;

public class MySolution_importUnionFind {
	
	public static void main(String[] args) throws Exception {
		int answer = 0;
		int count = 0; //총 컴퓨터 수	
		int connect = 0; //직접 연결된 컴퓨터 쌍 개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		count = Integer.parseInt(tmp);
		
		tmp = br.readLine();
		connect = Integer.parseInt(tmp);
		
		// 직접연결된 컴퓨터 번호쌍을 저장한 이차원 배열
		int[][] connection = new int[connect][2];
		
		for(int i =0; i<connect; i++) {
			tmp = br.readLine();
			String[] limit = tmp.split(" ");
			
			int a = Integer.parseInt(limit[0])-1;//0번 부터 시작;
			int b = Integer.parseInt(limit[1])-1;
			if(a<b) {
			connection[i][0] = a; 
			connection[i][1] = b;
			}else {
				connection[i][0] = b; 
				connection[i][1] = a;
			}
		}
		
		UnionFind uf = new UnionFind(count);
		
		for(int i = 0; i<connect; i++) {
			uf.union(connection[i][0],connection[i][1]);
		}
		
		for(int i = 1; i<count; i++) {
			uf.getParent(i);
			if(uf.parent[i]==0){answer ++;}
		}
		
		System.out.println(answer);
	}
}
