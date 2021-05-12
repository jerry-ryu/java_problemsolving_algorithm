package SortAlgorithm;

import java.util.ArrayList;

public class heap {

	private ArrayList<Integer> heaparray;
	
	heap(){
		 heaparray = new ArrayList<>();
	}
	
	//부모 노드 인덱스 찾기
	private int getparent(int index) { 
		
		// 부모 노드 = 자식 노드 /2
		return index/2;
	}
	
	//왼쪽 자식 노드 인덱스 찾기
	private int getleftchild(int index) {
		
		//왼쪽 자식 =  부모* 2
		if(index*2 >= heaparray.size()) {
			return -1;
		}else {
			return index*2;
		}
	}
	
	//오른쪽 자식 노드 인덱스 찾기
	private int getrightchild(int index) {
		
		//오른쪽 자식 =  부모* 2 +1
		if(index*2 +1 >= heaparray.size()) {
			return -1;
		}else {
			return index*2 +1;
		}
	}
	
	//heap 삽입
	private void add(int num) {
		heaparray.add(num);
		//resize(); heap재정렬 함수
	}
}
