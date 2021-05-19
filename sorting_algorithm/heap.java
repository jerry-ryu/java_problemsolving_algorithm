package SortAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class heap {

	private ArrayList<Integer> heaparray;
	
	heap(){
		 heaparray = new ArrayList<>();
	}	
	
	//부모 노드 인덱스 찾기
	private int getparents(int idx) { 
		
		if(idx==0){ //루트 노드의 부모는 없음
			return -1;
		}
		
		int index =idx+1;
		// 부모 노드 = (자식 노드+1) /2 -1
		return index/2 -1;
	}
	
	//왼쪽 자식 노드 인덱스 찾기
	private int getleftchild(int idx) {
		
		
		int index =idx+1;
		//왼쪽 자식 =  (부모+1)* 2 -1
		if(index*2 -1>= heaparray.size()-1) {
			return -1; //자식이 없으면 -1 반환
		}else {
			return index*2 -1;
		}
	}
	
	//오른쪽 자식 노드 인덱스 찾기
	private int getrightchild(int idx) {
		
		int index =idx+1;
		//오른쪽 자식 =  (부모+1)* 2
		if(index*2 >= heaparray.size()-1) {
			return -1; //자식이 없으면 -1 반환
		}else {
			return index*2;
		}
	}
	
	//heap 삽입
	void add(int num) {
		heaparray.add(num); //우선 맨끝에 삽입
		
		int me = heaparray.size()-1;
		int parents = this.getparents(me);
		
		//heap 다시 정렬
		//부모가 없을 때까지 반복	
		while(parents != -1 ) {		
			if(heaparray.get(me)<heaparray.get(parents)) { //부모보다 내가 작으면 부모와 나를 바꿈
				Collections.swap(heaparray, me, parents);
			}else {
				break;
			}
			
			me = parents;
			parents = this.getparents(me);
		}
	}
	
	//heap 삭제
	int delete() {
		//맨 앞의 원소를 맨뒤의 원소와 바꾸고 삭제
		Collections.swap(heaparray, 0, heaparray.size()-1);
		int delete = heaparray.remove(heaparray.size()-1); //삭제된 원소는 가장 작은 값임
		
		int me = 0;
		int leftchild = this.getleftchild(me);
		int rightchild = this.getrightchild(me);
		
		/*자식이 leftchild부터 채워지므로, leftchild가 없는 경우에는 rightchild도 없지만
		 * rightchild가 없는 경우에도 leftchild는 있을 수 있다
		 */
		
		//heap 다시 정렬
		//leftchild가 없을 경우 (모든 자식이 없을 경우)에만 종료
		while(leftchild != -1) {
			// rightchild가 있고, 내가 자식보다 크고, rightchild가 자식들 중에 가장 작은 값일 경우에만 
			// rightchild와 me 교환
			if(rightchild != -1 && heaparray.get(me) >heaparray.get(rightchild) 
					&& heaparray.get(leftchild) > heaparray.get(rightchild)){
				Collections.swap(heaparray, me, rightchild);
				me = rightchild;
			}
			// 내가 자식보다 크고, leftchild가 자식들중에 가장 작은 값인 경우에만
			// leftchild와 me 교환
			else if(heaparray.get(me) >heaparray.get(leftchild)) {
				
				Collections.swap(heaparray, me, leftchild);
				me = leftchild;
			}else {
				break;
			}
			
			leftchild = this.getleftchild(me);
			rightchild = this.getrightchild(me);
			
		}
		
		return delete;
	}
	
	//isEmpty
	boolean isEmpty() {
		if(heaparray.size()==0) {
			return true;
		}else {
			return false;
		}
		
	}
}

	
