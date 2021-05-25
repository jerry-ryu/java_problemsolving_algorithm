package MST;

import java.util.Comparator;
import java.util.PriorityQueue;

import MST.WeightedUnionFind;

public class Kruskal_algorithm {
	
	int nodes; //노드 개수
	int[][] costs; // [노드1,노드2,비용]으로 이루어진 이차원배열
	WeightedUnionFind wuf;
	
	public Kruskal_algorithm(int nodes, int[][] costs) {
		this.nodes = nodes;
		this.costs = costs;
		 wuf = new WeightedUnionFind(this.nodes);
	}
	
	public int solution() {
		
		int answer = 0;
		
		//비용 값을 기준으로 최소 힙 구현 
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] a, int[] b) {
				// TODO Auto-generated method stub
				return a[2] - b[2];
						
			}
        	
        });
        
        for(int[] value : costs) {
        	heap.add(value);
        }
        
      //힙이 빌때까지(모든 간선 값을 볼때까지) 반복
        while(!heap.isEmpty()) {
        	int[] tmp = heap.poll();
        	if(!wuf.find(tmp[0],tmp[1])) {
        		wuf.union(tmp[0],tmp[1]);
        		answer = answer + tmp[2];
        	}
        }
		
		return answer;
	}
	
	public static void main(String[] args) {
    	
    	int[][] costs = {{0,2,1},{1,2,2},{2,3,2},{0,4,2},{2,4,2}} ;
    	Kruskal_algorithm sol = new Kruskal_algorithm(5,costs);
    	System.out.println(sol.solution());
    	
    }
}
