package Programmers.ConnectingIslands;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Kruskal_solution {
	
	
	//부모 노드를 가져옴
	public int getParents(int set[], int x) {
		if(set[x] == x) return x;
		//재귀 함수를 통해 그래프 중에 가장 작은 부모 노드의 값을 가져온다
		return set[x] = getParents(set, set[x]); 
	}
	
	//부모 노드 병합
	void unionParent(int set[], int a, int b) {
		a = getParents(set, a);
		b = getParents(set,b);
		if(a<b) set[b] = a;
		else set[a]=b;
	}
	
	//같은 부모를 가지는지 확인
	boolean find(int set[], int a, int b) {
		a = getParents(set,a);
		b = getParents(set,b);
		if(a==b) return false;
		else return true;
	}
	
	public int solution(int n, int[][] costs) {
		int answer = 0;
		
		 //연결되어 있는 섬 중 가장 작은 값을 나타내는 connect 배열
        int[] connect = new int[n];
        for(int i = 0; i<n; i++) {
        	connect[i] = i;
        }
        
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
        	if(find(connect, connect[tmp[0]], connect[tmp[1]])) {
        		unionParent(connect, connect[tmp[0]], connect[tmp[1]]);
        		answer = answer + tmp[2];
        	}
        }
		
		return answer;
	}
	  public static void main(String[] args) {
	    	
	    	int[][] costs = {{0,2,1},{1,2,2},{2,3,2},{0,4,2},{2,4,2}} ;
	    	Kruskal_solution sol = new Kruskal_solution();
	    	System.out.println(sol.solution(5,costs));
	    	
	    }
}
