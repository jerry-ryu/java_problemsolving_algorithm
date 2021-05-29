package ProgrammersBj.IntegerTriangle;


public class Mysolution {
	
	public int solution(int[][] triangle) {
		for(int i = 1; i<triangle.length; i++) {
			for(int j = 0; j<=i;j++) {				
				if(j==0) {
					triangle[i][j] += triangle[i-1][0];
				}else if(j == i) {
					triangle[i][j] += triangle[i-1][i-1];
				}else {
					triangle[i][j] += Math.max(triangle[i-1][j-1],triangle[i-1][j]);
				}
			}
		}
		
		int max = triangle[triangle.length-1][0];
		for(int i = 1; i<triangle[triangle.length-1].length; i++) {
			if(max<triangle[triangle.length-1][i]) {
				max = triangle[triangle.length-1][i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] triangle = {{7},{3,8},{8,1,0},{2,7,4,4}};
		Mysolution sol = new Mysolution();
		System.out.print(sol.solution(triangle));
	}

}
