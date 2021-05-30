package ProgrammersBj.WayToSchool;

//DP를 사용하여, 임의의 점까지 갈수 있는 경우의 수 계산
//그것을 활용하여 다음 임의의 점까지 갈 수 있는 경우의 수 계산
public class MySolution2 {
	
	public int solution(int m, int n, int[][] puddles) {
		
		// 어떤 점까지 갈 수 있는 경우의 수를 나타낸 배열
		int[][] map = new int[n][m];
		
		//웅덩이 표시: -1
		for(int[] i : puddles) {
			map[i[1]-1][i[0]-1] = -1;
		}
		
		// 오른쪽이나 아래로 밖에 가지 못하므로,
		// 가장 자리에서 웅덩이가 있는 경우에는 그 후부터 경우의 수 0
		int k = 0;
		while(k<m && map[0][k] != -1  ) {
			map[0][k] = 1; 
			k++;
		}
		k=0;
		while( k<n &&map[k][0] != -1  ) {
			map[k][0] = 1; 
			k++;
		}
		
		// 경우의 수 종합
		for(int i = 1; i<n; i++) {
			for(int j = 1; j<m; j++) {
				
				//현재 위치가 웅덩이면 그냥 넘어감
				if(map[i][j] == -1) {
					continue;
				}
				

				if(map[i][j-1] == -1 ) {
					if(map[i-1][j] == -1) {
						// 위, 왼쪽이 웅덩이면 경우의 수 0
						map[i][j] = 0;
					}else {
						//왼쪽이 웅덩이면 경우의 수 = 위쪽의 경우의 수
						map[i][j] = map[i-1][j];
					}
				}else {
					if(map[i-1][j] == -1) {
						//위쪽이 웅덩이면 경우의 수 = 왼쪽의 경우의 수
						map[i][j] = map[i][j-1];
					}else {
						// 웅덩이가 없으면 경우의 수 = 왼쪽의 경우의 수+ 위쪽의 경우의 수
						map[i][j] =(map[i-1][j] + map[i][j-1]) % 1000000007;
					}
				}
				
			}
		}
		
		// 목적지까지의 경우의 수
		return map[n-1][m-1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySolution2 sol = new MySolution2();
		int[][] puddles = {{2,2},{3,1}};
		System.out.print(sol.solution(4,3,puddles));
	}

}
