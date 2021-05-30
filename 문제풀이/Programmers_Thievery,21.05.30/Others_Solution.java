package ProgrammersBj.Thievery;

import java.util.Arrays;

public class Others_Solution {

	int[] price;

    public int solution(int[] money) {
    	
    	// 첫 원소~ 마지막-1 원소까지의 배열
        price = Arrays.copyOf(money, money.length-1);
        int first = f(price.length-1);

        // 두번째 원소~ 마지막 원소까지의 배열
        price = Arrays.copyOfRange(money, 1, money.length);
        int second = f(price.length-1);

        /*첫원소를 택하면 자동적으로 마지막 원소를 택하지 못한다.
         * 반대로 마지막 원소를 택하면 첫 원소를 택하지 못한다.
         * 그러므로, Max(첫 원소~ 마지막-1 원소까지의 최댓값,
         * 두번째 원소~ 마지막 원소까지의 최댓값)이 원형 모형의 최댓값이다.
         */
        
        return Math.max(first, second);
    }

    
    /*이전 값에 대한 배열을 생성하지 않고
     * n1,n2 변수로 대체하여 공간효율성을 높였다
     */
    int f(int n) {
        int n2 = price[0]; // 현재 index가 i라면, i-2이하의 최대값
        int n1 = Math.max(price[0], price[1]); // i-1 이하의 최대값
        int result = 0;
        for(int i=2; i<=n; i++) {
        	//Max(i원소의 값+ i-2이하의 최대값,i-1 이하의 최대값 )
            result = Math.max(n2 + price[i], n1);
         // i-2, i-1 이하의 최대값 교환
            n2 = n1; 
            n1 = result;
        }
        return result;
    }

}
