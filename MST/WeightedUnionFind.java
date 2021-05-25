package MST;

public class WeightedUnionFind {
	// 각 집합의 루트 노드(최소값)을 나타내는 parent 배열
	public int[] parent;

	// size = 요소의 개수
	WeightedUnionFind(int size) {
		this.parent = new int[size];

		// 초기 상태의 모든 집합의 요소 개수는 1
		for (int i = 0; i < size; i++) {
			parent[i] = -1;
		}
	}

	// 입력된 요소의 루트 노드 반환
	private int getParent(int x) {

		// x가 루트 요소이면, x반환
		if (parent[x] < 0)
			return x;

		/*
		 * x 가 루트 노드가 아니라면 x부모의 루트 노트를 재귀적으로 찾음.
		 */
		return parent[x] = getParent(parent[x]);

	}

	// 입력된 요소가 같은 집합에 속하는지 알려줌
	public boolean find(int x, int y) {
		x = getParent(x);
		y = getParent(y);

		if (x == y)
			return true;

		return false;
	}

	// 입력된 요소들이 속한 집합을 병합
	public void union(int x, int y) {
		x = getParent(x);
		y = getParent(y);

		// 각 집합의 루트 노드가 같으면 같은 집합임
		if (x == y) {
			return;
		}

		// 작은 트리가 큰 트리 밑에 들어가게 병합
		// parent[x], parent[y] 값은 음수이므로 값이 작은 경우가 더 높이가 큰 노드이다.
		if (parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		} else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
