package Programmers.ConnectingIslands;

import java.util.Comparator;
import java.util.PriorityQueue;

class MySolution {

    public int solution(int n, int[][] costs) {
        
    	int answer = 0;
    	
        //연결되어 있는 섬 중 가장 작은 값을 나타내는 connect 배열
    	//처음엔 연결된 다리가 없으므로, 연결되어 있는 섬 중 가장 작은 값은 자기 자신이다
        int[] connect = new int[n];
        for(int i = 0; i<n; i++) {
        	connect[i] = i;
        }
        
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
        
        int allconnect = -1;
        
        //모든 섬이 연결될때까지 반복
        while(allconnect!=0) {
        	//비용이 가장 작은 순서대로 반환
        	int[] tmp = heap.poll();
        	
        	//서로 연결되어있는 섬이 아니면 서로 연결
        	if(connect[tmp[0]]!= connect[tmp[1]] ) {
        		if(connect[tmp[0]]>connect[tmp[1]]) {
        			update(connect, connect[tmp[1]],connect[tmp[0]]);
        		}else {
        			update(connect, connect[tmp[0]],connect[tmp[1]]);
        		}
        		
        		
        		
        		answer = answer + tmp[2];
        		
        		//connect 배열의 모든 원소가 0(connect[0]의 값, 
        		//노드중에 가장 작은 값)이면, 모든 노드가 연결된 것이다
        		allconnect = connect[0];
        		for(int con : connect) {
        			if(con != allconnect) {
        				allconnect = -1;
        				break;
        			}
        		}
        	}
        }
        
        
        return answer;
    }
    
    //모든 이어진 섬들의 값을 가장 최소 노드 값으로 최신화
    public void update(int[] connect, int newone, int oldone) {
    	for(int i  = 0; i< connect.length; i++) {
    		if(connect[i]==oldone) {
    			connect[i] = newone;
    		}
    	}
    }
    
    public static void main(String[] args) {
    	
    	int[][] costs = {{0,2,1},{1,2,2},{2,3,2},{0,4,2},{2,4,2}} ;
    	MySolution sol = new MySolution();
    	System.out.println(sol.solution(5,costs));
    	
    }
}