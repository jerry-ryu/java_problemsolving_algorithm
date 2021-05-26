package Programmers.SpeedTrap;

import java.util.Arrays;
import java.util.Comparator;



public class MySolution {

	public int solution(int[][] routes) {

		int answer = 0;

		//고속도로를 나간 지점을 기준으로 오름차순 정렬
		Arrays.sort(routes, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}

		});
		
		int start = -1; //카메라를 설치한 지점의 index값
		int count = 0; //카메라에 찍힌 차의 개수
		int[] arr = {0,0}; //초기화하기 위한 변수(like null)

		while (count != routes.length) {
			int camera = 0;
			int tmp = start;

			/* 반복 횟수를 줄이기 위해서 tmp + 1부터 탐색한다.
			 *  tmp = start이고, start가 카메라를 설치한 지점의 index값이므로
			 *  tmp 이하의 값은 이미 카메라에 찍힌 차들이다.
			 */
			
			for (int i = tmp + 1; i < routes.length; i++) {
				if (start == tmp) { //카메라를 설치할 지점을 찾지 못했으면,
					if (routes[i]!=arr) { // 이미 차가 찍혀서 초기화 되어있는지 확인
											  // 초기화 되어있지 않으면
						camera = routes[i][1]; //차가 나가는 지점에 카메라 설치
						answer++;
						routes[i] = arr; //routes[i]은 카메라에 찍힌 차이므로 초기화
						count++;
						start = i;
					}
				}
				else {
					if (routes[i]!=arr) { //초기화 되어있지 않으면(차가 카메라에 찍히지 않았으면)
						
						/*
						 * routes는 이미 고속도로를 나간 지점을 기준으로 오름차순 정렬되어 있고.
						 * 카메라를 차가 나간 시점에 설치하였으므로,
						 * 들어온 지점이 카메라 설치 지점보다 작기만 하면,
						 * 차가 들어온 지점 < 카메라 < 차가 나간 지점(정렬로서 보장)
						 * 이 성립한다.
						 */
						
						if (routes[i][0] <= camera) {
							routes[i] = arr; //카메라에 차가 찍혔으므로 초기화
							count++;
						}
					}
				}

			}

		}

		return answer;

	}

	public static void main(String[] args) {
		int[][] routes = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, 3 } };
		// int[][] routes = { { -10, 10 }, { 9, 11 }, { 11, 17 }, { 13, 17 } };
		MySolution sol = new MySolution();
		System.out.println(sol.solution(routes));

	}
}
