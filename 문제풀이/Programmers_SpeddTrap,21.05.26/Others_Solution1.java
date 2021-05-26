package Programmers.SpeedTrap;

import java.util.Arrays;

public class Others_Solution1 {
	public int solution(int[][] routes) {
		//차량이 나가는 지점을 기준으로 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int ans = 0; //카메라 개수
        int last_camera = Integer.MIN_VALUE;
        
        for (int[] a : routes) {
        	 //마지막 카메라가 설치된 지점이 차량의 진입 지점보다 작다면 새로운 카메라를 설치해야함 
            if (last_camera < a[0]) {
                ++ans;
                //새로운 카메라는 차량이 나가는 지점에
                last_camera = a[1];
            }
        }
        return ans;
    }
}
