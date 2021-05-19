package SortAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class heap {

	private ArrayList<Integer> heaparray;
	
	heap(){
		 heaparray = new ArrayList<>();
	}	
	
	//부모 노드 인덱스 찾기
	private int getparents(int index) { 
		
		// 부모 노드 = 자식 노드 /2
		return index/2;
	}
	
	//왼쪽 자식 노드 인덱스 찾기
	private int getleftchild(int idx) {
		
		int index =idx+1;
		//왼쪽 자식 =  (부모+1)* 2 -1
		if(index*2 -1>= heaparray.size()-1) {
			return -1;
		}else {
			return index*2 -1;
		}
	}
	
	//오른쪽 자식 노드 인덱스 찾기
	private int getrightchild(int idx) {
		
		int index =idx+1;
		//오른쪽 자식 =  (부모+1)* 2
		if(index*2 >= heaparray.size()-1) {
			return -1;
		}else {
			return index*2;
		}
	}
	
	//heap 삽입
	private void add(int num) {
		heaparray.add(num);
		
		int me = heaparray.size()-1;
		int parents = this.getparents(me);
		while(parents !=0 ) {
			if(heaparray.get(me)<heaparray.get(parents)) {
				Collections.swap(heaparray, me, parents);
			}else {
				break;
			}
		}
	}
	
	//heap 삭제
	private int delete() {
		Collections.swap(heaparray, 0, heaparray.size()-1);
		int delete = heaparray.remove(heaparray.size()-1);
		
		int me = 0;
		int leftchild = this.getleftchild(me);
		int rightchild = this.getrightchild(me);
		
		while(leftchild != -1) {
			if(heaparray.get(me) >heaparray.get(leftchild) && heaparray.get(leftchild) < heaparray.get(rightchild)){
				Collections.swap(heaparray, me, leftchild);
			}else if(heaparray.get(me) >heaparray.get(rightchild)) {
				Collections.swap(heaparray, me, rightchild);
			}else {
				break;
			}
		}
		
		return delete;
	}
}

	
