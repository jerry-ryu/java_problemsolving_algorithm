package MST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim_algorithm {

	PriorityQueue<int[]> pq[];
	//어떤 정점이 트리에 포함되었는지 나타내는 배열
	ArrayList<Integer> visited = new ArrayList<>();
	int nodes; // 노드 개수
	int[][] costs; // [노드1,노드2,비용]으로 이루어진 이차원배열

	@SuppressWarnings("unchecked")
	public Prim_algorithm(int nodes, int[][] costs) {
		this.nodes = nodes;
		this.costs = costs;
		
		//각 정점의 인접 정점까지의 비용을 저장하는 힙을 정점의 개수만큼 생성
		pq = new PriorityQueue[nodes]; 
		for (int i = 0; i < nodes; i++) {
			pq[i] = new PriorityQueue<int[]>(new Comparator<int[]>() {

				@Override
				public int compare(int[] a, int[] b) {
					// TODO Auto-generated method stub
					// 간선의 가중치(비용)을 기준으로 정렬
					return a[1] - b[1];

				}

			});
		}
		
		//힙에 데이터 추가
		//ex. [1,2,3]이면, 노드 1과 관련된 힙에는 [2,3]을 노드 2와 관련된 힙에는 [1,3]을 저장
		for (int i = 0; i < costs.length; i++) {
			int[] tmp1 = new int[2];

			tmp1[0] = costs[i][1];
			tmp1[1] = costs[i][2];
			pq[costs[i][0]].add(tmp1);
			
			int[] tmp2 = new int[2];
			tmp2[0] = costs[i][0];
			tmp2[1] = costs[i][2];
			pq[costs[i][1]].add(tmp2);
		}
	}

	public int solution() {
		int answer = 0;

		// 임의의 정점을 트리에 포함
		this.visited.add(0);

		// 모든 정점이 트리에 포함되면 알고리즘 종료
		while (visited.size() < this.nodes) {

			int[] selected = { 0, 1000 };
			
				//트리에 포함된 노드의 인접한 노드들 중 가중치(비용)이 가장 작은 노드 선택
				for (int i : visited) {
					while (!pq[i].isEmpty()) {
						if(!visited.contains(pq[i].peek()[0])) {
						int[] tmp = pq[i].peek();
							if (tmp[1] < selected[1]) {
								selected = tmp;
								break;
							}
							break;
						}else {
							pq[i].poll();
						}
					}
			}
			
			//간선이 이어준 노드를 트리에 포함	
			visited.add(selected[0]);
			answer += selected[1]; //비용 업데이트
		}
			
			

		return answer;
	}
	
	public static void main(String[] args) {
    	
    	int[][] costs = {{0, 1, 5}, {0, 3, 2}, {0, 4, 3}, {1, 4, 1}, {3, 4, 10}, {1, 2, 2}, {2, 5, 3}, {4, 5, 4}} ;
    	Prim_algorithm sol = new Prim_algorithm(6,costs);
    	System.out.println(sol.solution());
    	
    }
}
