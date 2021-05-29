package ProgrammersBj.ExpressAsN;

import java.util.*;

public class Mysolution {
	public int solution(int N, int number) {

		//N == number라면, N 1개를 써서 number를 만들었으므로 return 1
		if (N == number) {
			return 1;
		}

		/* 
		 * 중복되는 요소를 제거해주는 set자료구조를 9개 가지고있는 Arraylist 생성
		 * n번째 hashset의 의미: N을 n번 사용해서 만들 수 있는 숫자들의 모임
		 * 그러므로, 0번 hashset은 의미가 없지만, 숫자가 헷갈리므로 우선 0번은 넣었다.
		 */
		ArrayList<Set<Integer>> arr = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			arr.add(new HashSet<Integer>());
		}
		
		//  N 1개를 써서 만들 수 있는 수 => N
		arr.get(1).add(N); 

		//사칙 연산을 쓰지 않고 N 2개를 써서 만들 수 있는 수 => NN
		//사칙 연산을 쓰지 않고 N 3개를 써서 만들 수 있는 수 => NNN ...
		for (int i = 1; i < 9; i++) {
			Iterator<Integer> iter = arr.get(i - 1).iterator();
			while (iter.hasNext()) {
				arr.get(i).add(iter.next() * 10 + N);
			}
		}

		/* ex1) N 3개를 사용해서 만들수 있는 수 = 
		 * N 2개를 사용해서 만들 수 있는 수의 모임 (사칙연산) N 1개를 사용해서 만들 수 있는 수의 모임
		 * N 1개를 사용해서 만들 수 있는 수의 모임 (사칙연산) N 2개를 사용해서 만들 수 있는 수의 모임
		 * 
		 * ex2) N 4개를 사용해서 만들수 있는 수 = 
		 * N 3개를 사용해서 만들 수 있는 수의 모임 (사칙연산) N 1개를 사용해서 만들 수 있는 수의 모임
		 * N 2개를 사용해서 만들 수 있는 수의 모임 (사칙연산) N 2개를 사용해서 만들 수 있는 수의 모임
		 * N 1개를 사용해서 만들 수 있는 수의 모임 (사칙연산) N 3개를 사용해서 만들 수 있는 수의 모임
		 */
		for (int i = 2; i < 9; i++) {
			int j = i - 1;
			while (j >=0) {
				for (int num1 : arr.get(j)) {
					for (int num2 : arr.get(i-j)) {
						arr.get(i).add(num1 + num2);
						if (num1 - num2 > 0) {
							//최소 횟수를 구하는 것이고, N>0이므로 0보다 작아지면 의미 없는 수, 저장x
							arr.get(i).add(num1 - num2);
						}
						arr.get(i).add(num1 * num2);
						if (num1 % num2 == 0) {
							//N은 언제나 정수이므로, 실수는 의미 없는 수, 저장x
							arr.get(i).add(num1 / num2);
						}
					}
				}
				j--;
			}

			//number가 arr의 n번째 hashset에 있으면
			if (arr.get(i).contains(number)) {
				//number는 n개의 N을 활용하여 만들 수 있는 수이다.
				return i;
			}
		}
		
		/*number가 arr의 8번째 hashset에까지 없으면
			number는 1~8개의 N을 활용하여 만들 수 없는 수이다.
			-1 반환
		*/
		return -1;
	}

	public static void main(String[] args) {
		Mysolution sol = new Mysolution();
		System.out.println(sol.solution(5, 31168));
		System.out.println(sol.solution(4, 17));
		System.out.println(sol.solution(5, 12));
	}
}