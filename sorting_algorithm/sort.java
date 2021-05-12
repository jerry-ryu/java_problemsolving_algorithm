package SortAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

public class sort {

	// 버블 정렬
	private void bubblesort(int[] numarr) {

		// 회전은 배열의 길이 -1만큼 반복된다
		for (int i = 1; i < numarr.length; i++) {

			// i번 회전할 때마다, 뒤에서부터 i개가 정렬되므로, numarr.length -i개만 비교하면 된다
			for (int j = 0; j < numarr.length - i; j++) {
				if (numarr[j] > numarr[j + 1]) { // 오름차순 정렬
					swap(numarr, j, j + 1);
				}
			}

		}

		print(numarr, "버블 정렬");
	}

	// 개선된 버블 정렬
	private void imporvedbubblesort(int[] numarr) {

		// 회전은 배열의 길이 -1만큼 반복된다
		for (int i = 1; i < numarr.length; i++) {

			boolean isswap = false;

			// i번 회전할 때마다, 뒤에서부터 i개가 정렬되므로, numarr.length -i개만 비교하면 된다
			for (int j = 0; j < numarr.length - i; j++) {
				if (numarr[j] > numarr[j + 1]) { // 오름차순 정렬
					swap(numarr, j, j + 1);
					isswap = true;
				}
			}

			if (isswap == false) {
				break;
			}
		}

		print(numarr, "개선된 버블 정렬");
	}

	// 삽입 정렬
	private void insertionsort(int[] numarr) {
		for (int i = 1; i < numarr.length; i++) {
			int target = numarr[i]; // 타겟 원소 설정
			int j = i - 1; // 타겟 원소보다 작은 idx를 가지는 원소들을 비교
			while (j >= 0 && target < numarr[j]) { // 타겟이 이전 원소보다 크기 전 까지 반복
				swap(numarr, j, j + 1); // 이전 원소를 한 칸씩 민다
				j--;
			}

			// j는 타겟원소보다 작은 원소의 idx를 의미하므로 target원소는 idx j+1에 들어가야한다
			numarr[j + 1] = target;
		}

		print(numarr, "삽입 정렬");
	}

	// 선택 정렬
	private void selectionsort(int[] numarr) {

		for (int i = 0; i < numarr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < numarr.length; j++) {
				// 최솟값을 가진 인덱스 찾기
				if (numarr[j] < numarr[min]) {
					min = j;
				}
			}
			// i 번째 값과 최솟값을 교환
			swap(numarr, i, min);
		}

		print(numarr, "선택 배열");
	}

	//병합 정렬 (Top-Down 형식, 재귀함수 사용) -1
	private void TopDown_mergesort(int[] numarr) {
		int[] tmparr = new int[numarr.length]; //임시배열 생성
		
		TopDown_innermergesort(numarr,0,numarr.length-1, tmparr);
		
		print(tmparr, "Top-Down 병합 정렬");
	}
	
	//병합 정렬 (Top-Down 형식, 재귀함수 사용) -2
	private void TopDown_innermergesort(int[] numarr, int start, int end, int[] tmparr) {
		
		
		
		if(start == end) { //부분 배열의 원소의 개수가 1개인 경우 더 이상 divide가 불가능하므로 return한다
			return;
		}
		
		int mid = (start+end)/2; //절반
		TopDown_innermergesort(numarr,start,mid,tmparr); //절반 중 왼쪽의 부분배열 divide and conquer
		TopDown_innermergesort(numarr,mid+1,end,tmparr);//절반 중 오른쪽의 부분배열 divide and conquer
		
		merge(numarr, start, mid, end, tmparr);
		
	}
	
	//병합 정렬 (Bottom-Up 형식, 재귀함수 사용 x) -1
	private void BottomUp_mergesort(int[] numarr) {
		int[] tmparr = new int[numarr.length]; //임시배열 생성
		
		BottomUp_innermergesort(numarr, tmparr);
		
		print(numarr, "Bottom-Up 병합 정렬");
		
	}
	
	//병합 정렬 (Bottom-Up 형식, 재귀함수 사용 x) -2
	private void BottomUp_innermergesort(int[] numarr, int[] tmparr) {
		
		for(int size = 1; size<numarr.length; size +=size) {
			//size = 나눌 배열의 크기, 1,2,4,8...로 증가한다
			for(int l = 0; l < numarr.length - size; l += (2 * size)) {
				/*
				 * 두개의 부분 배열을 합치는 것이다.
				 * Top-Down 형식에서 인덱스가 start~mid인 부분 배열과 
				 * 인덱스가 mid+1~end인 두 배열을 합친 것과 같이
				 * size 변수에 따라, 즉 low~mid 부분배열과 mid+1~high까지인 부분 배열을 병합한다.
				 * 이때, l + (2 * size) - 1(사이즈에 기반한 다음 부분배열의 크기)가 
				 * 원래 배열의 최대 인덱스보다 클 수 있으므로,
				 * Math.min함수를 통해 high 값을 제한해준다.
				 * l < numarr.length - size;에서 numarr.length - size부분은 
				 * 배열의 길이가 2의 제곱이 아닐경우에 어쩔 수 없이 생기는
				 * 하나 남은 배열을 방지해준다.
				 * 예를 들어, 배열의 길이가 6이고 size가 2라면, 
				 * for문은 인덱스가 0~1인 부분 배열과 인덱스가 2~3인 부분 배열을 합치고, 
				 * 4~6부분 배열이 홀로 남는다.
				 * 이때, numarr.length - size = 4이므로, 변수 ㅣ이 4보다 크거나 같아지지 않아서 
				 * 4~6배열이 merge함수에 들어가는 일을방지해준다.   
				 */
				int low = l;
				int mid = l + size - 1;
				int high = Math.min(l + (2 * size) - 1, numarr.length-1);
				merge(numarr, low, mid, high, tmparr);		// 병합(merging)작업
			}
		}
		
	}
	
	//병합정렬 병합(merging)함수(Top-Down ,Bottom-Up 형식 둘 다 사용 )
	private void merge(int[] numarr, int start, int mid, int end, int[] tmparr) {
		
		int left = start; //왼쪽 절반의 시작 지점
		int right = mid+1; //오른쪽 절반의 시작 지점
		int idx = left; //tmparr의 idx
		
		while(left<=mid || end>=right ) { //왼쪽이나 오른쪽에 원소가 남아있다면
			if( right>end || (left<= mid && numarr[left]<numarr[right]) ){ 
				// 오른쪽 원소를 다 사용했거나, (왼쪽 원소가 남아있고 왼쪽 원소가 더 작은 경우)
				tmparr[idx] = numarr[left]; //왼쪽 원소를 tmparr에 넣는다
				idx++;
				left++; //사용한 원소는 비교대상에서 제외
			}else {
				tmparr[idx] = numarr[right];//오른쪽 원소를 tmparr에 넣는다
				idx++;
				right++;//사용한 원소는 비교대상에서 제외
			}
		}	
		
		for (int i = start; i <= end; i++) { //정렬된 부분 배열을 기존의 배열에 복사하여 붙여준다
			numarr[i] = tmparr[i];
		}
		/*이 과정이 없다면, 부분배열을 정렬한 것이 원 배열에 반영되지 않아, 
		 * 합병하여 정렬하는 것이 반영이 되지 않는다
		 * 즉, 부분 배열을 정렬해놓고, 정렬 안되어있는 배열을 사용하는 셈이다
		 */
	}
	
	//힙 정렬(priority queue사용)
	private void priorityQueue_heapsort(int[] numarr) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		for(int i = 0; i<numarr.length; i++) {
			priorityQueue.add(numarr[i]); //priority queue에 각 원소 넣기
		}
		
		int i = 0;
		while(!priorityQueue.isEmpty()) {
			numarr[i] = priorityQueue.poll();
			//우선순위 순서대로(숫자가 작은 순서대로) 원소 반환
			//원래의 배열에 복사
			i++;
		}
		
		print(numarr, "우선순위 큐를 사용한 힙 정렬");
	}

	//힙 정렬(heap을 구현하여 사용)
	private void implementing_heap_heapsort(int numarr) {
		
	}
	
	
	// swap
	private void swap(int[] numarr, int i, int j) {
		int temp = numarr[i];
		numarr[i] = numarr[j];
		numarr[j] = temp;
	}

	// 출력
	private void print(int[] numarr, String sortname) {
		System.out.println(sortname + "로 정렬된 배열:" + Arrays.toString(numarr));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numarr = { 5, 6, 3, 1, 4, 2 }; // 정렬할 배열
		System.out.println("원래 배열:" + Arrays.toString(numarr));

		sort Sort = new sort();

		Sort.priorityQueue_heapsort(numarr);

	}
}
