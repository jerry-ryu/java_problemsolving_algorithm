package ProgrammersBj.WayToSchool;


//재귀를 통해서 다음 행선지를 정하고, 도착하는 경우의 수 총합
public class MySolution {
	int m,n; //맵 크기 x = m , y = n;
	int[][] puddles; //웅덩이 위치 배열
	int answer; 
	
	public int solution(int m, int n, int[][] puddles) {
		this.m = m;
		this.n = n;
		this.puddles = puddles;
		answer = 0;
		
		//출발지와 도착지는 웅덩이가 아니라고 했으므로,
		// 출발지에서는 오른쪽 또는 아래로 갈 수 있음
		waytogo(2,1);
		waytogo(1,2);
		return answer;
	}
	
	public void waytogo(int x, int y) {
		//도착지에 도착하면 경우의 수 +1
		if(x ==m && y==n) {
			answer = (answer +1) % 1000000007 ;
			return;
		}
		
		// 간 곳이 웅덩이면 return
		for(int i = 0; i<puddles.length; i++) {
			if(puddles[i][0] == x && puddles[i][1] == y) {
				return;
			}
		}
		
		// x의끝에 도착하면 아래로만 갈 수 있음
		if(x==m) {
			waytogo(x,y+1);
		}
		// y의끝에 도착하면 오른쪽으로만 갈 수 있음
		else if(y==n) {
			waytogo(x+1,y);
		}else {
			waytogo(x,y+1);
			waytogo(x+1,y);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MySolution sol = new MySolution();
		int[][] puddles = {{2,2}};
		System.out.print(sol.solution(4, 3, puddles));
	}

}
