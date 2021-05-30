package ProgrammersBj.Thievery;

public class Mysolution {

	public int solution(int[] money) {
		
		int[][] arr = new int[money.length][2];
		//arr[i][0] -> 첫번째 원소를 선택한 경우
		//arr[i][1] -> 첫번째 원소를 선택하지 않은 경우
		arr[0][0]= 0;
		arr[0][0] = 0;
		arr[1][0] = money[0];
		arr[1][1] = 0;
		
		//원형 구조로 생각하지 말고, 일자 구조로 생각한다. -> 맨 끝 원소와 맨 처음 원소의 연관성 무시
		// arr[i][0], arr[i][1]에서 고려해주었기 때문에
		for(int i = 2; i<money.length; i++) {
			// i원소 선택 = i-1 원소 선택 못함
			
			//Max( i원소  + i-2이하의 원소의 최대값 , i-1 원소의 최대값)
			arr[i][0] = Math.max(money[i-1] + arr[i-2][0],arr[i-1][0]);
			arr[i][1] = Math.max(money[i-1] + arr[i-2][1], arr[i-1][1]);
		}
		
		// Max(1.맨 끝(index end) 원소 + 첫 번째 원소가 선택 안된 index end-2 이하의 원소 
		//			2. 첫 번째 원소가 선택 안된 index end-1 이하의 원소
     	//			3. 첫 번째 원소가 선택된 index end-1 이하의 원소
		//       )
		int a = Math.max(Math.max(money[money.length-1] + arr[money.length-2][1], arr[money.length-1][0]),arr[money.length-1][0]);
		
		return a;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] money = {1,2,3,1};
		Mysolution sol = new Mysolution();
		System.out.println(sol.solution(money));
	}

}
