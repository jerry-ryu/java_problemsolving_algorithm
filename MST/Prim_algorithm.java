package MST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim_algorithm {

	PriorityQueue<int[]> pq[];
	ArrayList<Integer> visited = new ArrayList<>();
	int nodes; // 노드 개수
	int[][] costs; // [노드1,노드2,비용]으로 이루어진 이차원배열

	public Prim_algorithm(int nodes, int[][] costs) {
		this.nodes = nodes;
		this.costs = costs;
		pq = new PriorityQueue[nodes];
		for (int i = 0; i < nodes; i++) {
			pq[i] = new PriorityQueue<int[]>(new Comparator<int[]>() {

				@Override
				public int compare(int[] a, int[] b) {
					// TODO Auto-generated method stub
					return a[1] - b[1];

				}

			});
		}
		for (int i = 0; i < costs.length; i++) {
			int[] tmp = new int[2];
			tmp[0] = costs[i][0];
			tmp[1] = costs[i][2];
			pq[costs[i][0]].add(tmp);
			tmp[0] = costs[i][1];
			tmp[1] = costs[i][2];
			pq[costs[i][1]].add(tmp);
		}
	}

	public int solution() {
		int answer = 0;

		this.visited.add(0);

		while (visited.size() < this.nodes) {

			int[] selected = { 0, 1000 };

			while (!visited.isEmpty()) {
				for (int i : visited) {
					while (!pq[i].isEmpty()) {
						int[] tmp = pq[i].poll();
						if (!visited.contains(tmp[0])) {
							if (tmp[1] < selected[1]) {
								selected = tmp;
								break;
							}
						}
					}
				}
			}
			
			visited.add(selected[0]);
			answer += selected[1];
		}

		return answer;
	}
	
	public static void main(String[] args) {
    	
    	int[][] costs = {{0,2,1},{1,2,2},{2,3,2},{0,4,2},{2,4,2}} ;
    	Prim_algorithm sol = new Prim_algorithm(5,costs);
    	System.out.println(sol.solution());
    	
    }
}
