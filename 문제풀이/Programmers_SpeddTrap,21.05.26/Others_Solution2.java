package Programmers.SpeedTrap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class Others_Solution2 {
	 public class Car implements Comparable <Car>{
	        boolean isIn;
	        int spot;
	        int index;

	        public Car(boolean isIn, int spot, int index) {
	            this.isIn = isIn;
	            this.spot = spot;
	            this.index = index;
	        }

	        @Override
	        //차량의 들어온, 나간 시점을 기준으로 비교
	        public int compareTo(Car o) {
	            if (spot < o.spot) {
	                return -1;
	            }
	            else if (spot > o.spot) {
	                return +1;
	            }
	            else {
	                return 0;
	            }
	        }
	    }

	    public int solution(int[][] routes) {
	        int answer = 0;
	        int n = routes.length;
	        ArrayList<Car> list = new ArrayList<>();

	        for (int i = 0; i < n; i++) {
	            list.add(new Car(true, routes[i][0], i)); //들어옴, 들어온 지점, 차량 번호
	            list.add(new Car(false, routes[i][1], i)); //나감, 나간 지점, 차량 번호
	        }
	        Collections.sort(list);

	        //차량이 카메라에 찍혔는지 검사하는 배열
	        boolean[] handled = new boolean[n];
	        Stack<Car> stack = new Stack<>();

	        Iterator<Car> iterator = list.iterator();
	        while(iterator.hasNext()) {
	            Car car = iterator.next();
	            
	            //들어온 차들을 모두 stack에 저장
	            if (car.isIn) {
	                stack.push(car);
	            }
	            else {
	            	//카메라에 안찍힌 차가 나가면,
	                if (!handled[car.index]) {
	                	//카메라를 차가 빠져나가는 지점에 설치하므로,
	                	// stack에 저장된 차들은 아직 고속도로를 달리고 있는 차
	                	// = 카메라에 찍히는 차들
	                    while(!stack.isEmpty()) {
	                        handled[stack.pop().index] = true;
	                    }
	                    answer++;
	                }
	            }
	        }

	        return answer;
	    }
}
