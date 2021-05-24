package Programmers.ConnectingIslands;

import java.util.ArrayList;
import java.util.Iterator;

//영역을 넓혀가는 방식
class Prim_Solution {
    public int solution(int n, int[][] costs) {
    	
    	//섬과 간선을 나타내는 2차원 배열 island 생성
        int[][] island = new int[n][n];

        //한쪽 섬을 시점, 다른 쪽 섬을 종점으로 생각하고 섬을 연결하는 비용(간선) 저장
        for (int[] cost : costs) {
            if (cost.length > 0) {
                island[cost[0]][cost[1]] = cost[2];
                island[cost[1]][cost[0]] = cost[2];
            }
        }

        //연결되어있는 섬을 저장하는 배열 connected
        ArrayList<Integer> connected = new ArrayList<>();
        Iterator<Integer> it;
        //0번째 섬부터 연결을 시작한다
        connected.add(0);

        
        /*
         *  섬을 모두 연결하는 최소 비용을 구하기 위해서는
         *  비용이 낮은 간선을 선택해야하고, 간선의 개수가 적어야한다.
         *  섬을 모두 이을 수 있는 최소 간선의 개수는 섬의 수 -1이며,
         *  어느 경우에도 선택된 간선의 수가 최소 간선 수를 넘으면 
         *  최소 비용이 될 수 없다.
         */
        
        int sum = 0;
        
        //연결된 섬이 n이 되면 종료 == 간선의 수가 섬의 개수 -1이면 종료
        while (connected.size() < n) {
            long min = -1;
            int minI = -1, minJ = -1;

            it = connected.iterator();
            while (it.hasNext()) {
            	/*
            	 * 시점이 될 수 있는 섬들은 종점이 될 수 없다.
                 * 0번 섬부터 시작하여, 이어진 섬들(시점이 될 수 있는 섬)에서 
                 * 이어지지 않은 섬(종점이 될 수 있는 섬)들로 간선을 이을 것이므로
                 * 이어진 섬을 다시 잇는 것은 불필요하게 간선을 택한 것이다.
            	 */
                int start = it.next();
                for (int end = 0; end < n; end++) {
                	// 시점이 되었던 섬들은 종점이 될 수 없다.
                    if (!connected.contains(end)) {
                        if (island[start][end] > 0) {
                        	//시점인 섬과 연결될 수 있는 모든 종점의 섬 중에 간선의 값이 가장 작은 섬을 선택한다.
                            if (island[start][end] < min || min == -1) {
                                min = island[start][end];
                                minI = start;
                                minJ = end;
                            }
                        }
                    }
                }
            }
            sum += min;
            //시점 후보로 종점이 된 섬 추가
            connected.add(minJ);
            island[minI][minJ] = 0;
            island[minJ][minI] = 0;
        }
        return sum;
    }
    
    public static void main(String[] args) {
    	
    	int[][] costs = {{0,2,1},{1,2,2},{2,3,2},{0,4,2},{2,4,2}} ;
    	 Prim_Solution sol = new  Prim_Solution();
    	System.out.println(sol.solution(5,costs));
    	
    }
}
