package MST;

public class UnionFind {
	//각 집합의 루트 노드(최소값)을 나타내는 parent 배열
	public int[] parent;
	
	//size = 요소의 개수
	UnionFind(int size){
		this.parent = new int[size];
		
		//초기 상태에는 자기 자신이 집합의 가장 작은 값
		for(int i = 0; i<size; i++) {
			parent[i] = i;
		}
	}
	
	//입력된 요소의 루트 노드 반환
	private int getParent(int x) {
		
		//각 집합의 루트 노드가 자기 자신이라면, 자기 자신 반환
		if(x == parent[x]) return x; 
		
		/*루트 노드가 자기 자신이 아니라면 x 부모의 루트노트를
		* 재귀적으로 찾음.
		*/
		return parent[x] = getParent(parent[x]);
		
	}
	
	//입력된 요소가 같은 집합에 속하는지 알려줌
	public boolean find(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		
		if(x==y) return true;
		
		return false;
	}
	
	//입력된 요소들이 속한 집합을 병합
	public void union(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		
		if(x!=y) {
			parent[y] = x;
		}
	}
}
