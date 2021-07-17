package ProgrammersBj.Bj_Virus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MySolution_useUnionFind {
	
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
			connection[i][0] = a; 
			connection[i][1] = b;
		}
		
		UnionFind uf = new UnionFind(count);
		
		for(int i = 0; i<connect; i++) {
			uf.union(connection[i][0],connection[i][1]);
		}
		
		for(int i = 1; i<count; i++) {
			if(uf.getParent(uf.parent[i])==0){answer ++;}
		}
		
		System.out.println(answer);
	}
}
 class UnionFind {
	//각 집합의 루트 노드(최소값)을 나타내는 parent 배열
	public int[] parent;
	
	//size = 요소의 개수
	public UnionFind(int size){
		this.parent = new int[size];
		
		//초기 상태에는 자기 자신이 집합의 가장 작은 값
		for(int i = 0; i<size; i++) {
			parent[i] = i;
		}
	}
	
	//입력된 요소의 루트 노드 반환
	public int getParent(int x) {
		
		//각 집합의 루트 노드가 자기 자신이라면, 자기 자신 반환
		if(x == parent[x]) return x; 
		
		/*루트 노드가 자기 자신이 아니라면 x 부모의 루트노트를
		* 재귀적으로 찾음.
		*/
		return parent[x] = getParent(parent[x]);
		
	}

	
	//입력된 요소들이 속한 집합을 병합
	public void union(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		
		 if(x > y) {
	            parent[x] = y;
	        } else {
	            parent[y] = x;
	        }
	}
}

