package SortAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.Math;

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

		print(numarr, "선택 정렬");
	}

	// 병합 정렬 (Top-Down 형식, 재귀함수 사용) -1
	private void TopDown_mergesort(int[] numarr) {
		int[] tmparr = new int[numarr.length]; // 임시배열 생성

		TopDown_innermergesort(numarr, 0, numarr.length - 1, tmparr);

		print(tmparr, "Top-Down 병합 정렬");
	}

	// 병합 정렬 (Top-Down 형식, 재귀함수 사용) -2
	private void TopDown_innermergesort(int[] numarr, int start, int end, int[] tmparr) {

		if (start == end) { // 부분 배열의 원소의 개수가 1개인 경우 더 이상 divide가 불가능하므로 return한다
			return;
		}

		int mid = (start + end) / 2; // 절반
		TopDown_innermergesort(numarr, start, mid, tmparr); // 절반 중 왼쪽의 부분배열 divide and conquer
		TopDown_innermergesort(numarr, mid + 1, end, tmparr);// 절반 중 오른쪽의 부분배열 divide and conquer

		merge(numarr, start, mid, end, tmparr);

	}

	// 병합 정렬 (Bottom-Up 형식, 재귀함수 사용 x) -1
	private void BottomUp_mergesort(int[] numarr) {
		int[] tmparr = new int[numarr.length]; // 임시배열 생성

		BottomUp_innermergesort(numarr, tmparr);

		print(numarr, "Bottom-Up 병합 정렬");

	}

	// 병합 정렬 (Bottom-Up 형식, 재귀함수 사용 x) -2
	private void BottomUp_innermergesort(int[] numarr, int[] tmparr) {

		for (int size = 1; size < numarr.length; size += size) {
			// size = 나눌 배열의 크기, 1,2,4,8...로 증가한다
			for (int l = 0; l < numarr.length - size; l += (2 * size)) {
				/*
				 * 두개의 부분 배열을 합치는 것이다. Top-Down 형식에서 인덱스가 start~mid인 부분 배열과 인덱스가 mid+1~end인 두
				 * 배열을 합친 것과 같이 size 변수에 따라, 즉 low~mid 부분배열과 mid+1~high까지인 부분 배열을 병합한다. 이때, l +
				 * (2 * size) - 1(사이즈에 기반한 다음 부분배열의 크기)가 원래 배열의 최대 인덱스보다 클 수 있으므로, Math.min함수를
				 * 통해 high 값을 제한해준다. l < numarr.length - size;에서 numarr.length - size부분은 배열의 길이가
				 * 2의 제곱이 아닐경우에 어쩔 수 없이 생기는 하나 남은 배열을 방지해준다. 예를 들어, 배열의 길이가 6이고 size가 2라면, for문은
				 * 인덱스가 0~1인 부분 배열과 인덱스가 2~3인 부분 배열을 합치고, 4~6부분 배열이 홀로 남는다. 이때, numarr.length -
				 * size = 4이므로, 변수 ㅣ이 4보다 크거나 같아지지 않아서 4~6배열이 merge함수에 들어가는 일을방지해준다.
				 */
				int low = l;
				int mid = l + size - 1;
				int high = Math.min(l + (2 * size) - 1, numarr.length - 1);
				merge(numarr, low, mid, high, tmparr); // 병합(merging)작업
			}
		}

	}

	// 병합정렬 병합(merging)함수(Top-Down ,Bottom-Up 형식 둘 다 사용 )
	private void merge(int[] numarr, int start, int mid, int end, int[] tmparr) {

		int left = start; // 왼쪽 절반의 시작 지점
		int right = mid + 1; // 오른쪽 절반의 시작 지점
		int idx = left; // tmparr의 idx

		while (left <= mid || end >= right) { // 왼쪽이나 오른쪽에 원소가 남아있다면
			if (right > end || (left <= mid && numarr[left] < numarr[right])) {
				// 오른쪽 원소를 다 사용했거나, (왼쪽 원소가 남아있고 왼쪽 원소가 더 작은 경우)
				tmparr[idx] = numarr[left]; // 왼쪽 원소를 tmparr에 넣는다
				idx++;
				left++; // 사용한 원소는 비교대상에서 제외
			} else {
				tmparr[idx] = numarr[right];// 오른쪽 원소를 tmparr에 넣는다
				idx++;
				right++;// 사용한 원소는 비교대상에서 제외
			}
		}

		for (int i = start; i <= end; i++) { // 정렬된 부분 배열을 기존의 배열에 복사하여 붙여준다
			numarr[i] = tmparr[i];
		}
		/*
		 * 이 과정이 없다면, 부분배열을 정렬한 것이 원 배열에 반영되지 않아, 합병하여 정렬하는 것이 반영이 되지 않는다 즉, 부분 배열을
		 * 정렬해놓고, 정렬 안되어있는 배열을 사용하는 셈이다
		 */
	}

	// 힙 정렬(priority queue사용)
	private void priorityQueue_heapsort(int[] numarr) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		for (int i = 0; i < numarr.length; i++) {
			priorityQueue.add(numarr[i]); // priority queue에 각 원소 넣기
		}

		int i = 0;
		while (!priorityQueue.isEmpty()) {
			numarr[i] = priorityQueue.poll();
			// 우선순위 순서대로(숫자가 작은 순서대로) 원소 반환
			// 원래의 배열에 복사
			i++;
		}

		print(numarr, "우선순위 큐를 사용한 힙 정렬");
	}

	// 힙 정렬(heap을 구현하여 사용)
	private void implementing_heap_heapsort(int[] numarr) {
		heap Heap = new heap();
		for (int i = 0; i < numarr.length; i++) {
			Heap.add(numarr[i]); // priority queue에 각 원소 넣기
		}

		int i = 0;
		while (!Heap.isEmpty()) {
			numarr[i] = Heap.delete();
			// 숫자가 작은 순서대로 원소 반환
			// 원래의 배열에 복사
			i++;
		}
		print(numarr, "구현된 heap을 사용한 힙 정렬");

	}

	// 제자리 힙 정렬(in-place heap sort) -1
	private void in_place_heapsort(int[] numarr) {
		for (int i = 1; i < numarr.length - 1; i++) {
			in_place_heapsort_add(i, numarr);
		}

		for (int i = numarr.length - 1; i > 0; i--) {
			in_place_heapsort_delete(i, numarr);
		}

		print(numarr, "제자리 힙 정렬");
	}

	// 제자리 힙 정렬(in-place heap sort) -2 (부모 idx, 자식 idx 찾기)
	private int in_place_heapsort_find(int type, int idx, int ref_point) {
		// type 0: 부모찾기, type 1: 왼쪽 자식 찾기, type 3: 오른쪽 자식찾기
		switch (type) {
		case 0:
			if (idx == 0) { // 루트 노드의 부모는 없음
				return -1;
			}

			// 부모 노드 = (자식 노드+1) /2 -1
			return (idx + 1) / 2 - 1;

		/*
		 * ref_point는 배열 안에서 정의된 heap의 마지막 idx +1 그러므로 leftchild나 rightchild가 ref_point와
		 * 같거나 크면 heap의 범위를 넘어갔다고 생각해야함
		 */

		case 1:
			// 왼쪽 자식 = (부모+1)* 2 -1
			if ((idx + 1) * 2 - 1 >= ref_point) {
				return -1; // 자식이 없으면 -1 반환
			} else {
				return (idx + 1) * 2 - 1;
			}
		case 2:
			// 오른쪽 자식 = (부모+1)* 2
			if ((idx + 1) * 2 >= ref_point) {
				return -1; // 자식이 없으면 -1 반환
			} else {
				return (idx + 1) * 2;
			}
		}
		return -1;

	}

	// 제자리 힙 정렬(in-place heap sort) -3 (배열 안에서 정의된 heap에 원소 넣기)
	private void in_place_heapsort_add(int ref_point, int[] numarr) {

		int me = ref_point;
		int parents = this.in_place_heapsort_find(0, me, ref_point);

		// heap 다시 정렬
		// 부모가 없을 때까지 반복
		while (parents != -1) {
			if (numarr[me] > numarr[parents]) { // 부모보다 내가 작으면 부모와 나를 바꿈
				swap(numarr, me, parents);
			} else {
				break;
			}

			me = parents;
			parents = this.in_place_heapsort_find(0, me, ref_point);
		}

	}

	// 제자리 힙 정렬(in-place heap sort) -4 (배열 안에서 정의된 heap에서 원소 빼기)
	private void in_place_heapsort_delete(int ref_point, int[] numarr) {

		swap(numarr, ref_point, 0);

		int me = 0;
		int leftchild = this.in_place_heapsort_find(1, me, ref_point);
		int rightchild = this.in_place_heapsort_find(2, me, ref_point);

		while (leftchild != -1) {
			// rightchild가 있고, 내가 자식보다 크고, rightchild가 자식들 중에 가장 작은 값일 경우에만
			// rightchild와 me 교환
			if (rightchild != -1 && numarr[me] < numarr[rightchild] && numarr[leftchild] < numarr[rightchild]) {
				swap(numarr, me, rightchild);
				me = rightchild;
			}
			// 내가 자식보다 크고, leftchild가 자식들중에 가장 작은 값인 경우에만
			// leftchild와 me 교환
			else if (numarr[me] < numarr[leftchild]) {

				swap(numarr, me, leftchild);
				me = leftchild;
			} else {
				break;
			}

			leftchild = this.in_place_heapsort_find(1, me, ref_point);
			rightchild = this.in_place_heapsort_find(2, me, ref_point);

		}
	}

	// 계수 정렬
	private void countingsort(int[] numarr, int max, int min) {
		// 카운팅 배열 길이 = 원소들의 최댓값 - 원소들의 최소값 + 1
		final int counting_length = max - min + 1;
		int[] counting = new int[counting_length];
		// 출력 배열의 길이 = 입력 배열의 길이
		int[] output = new int[numarr.length];

		// 카운팅 배열에 원소들의 출현 횟수 누적
		for (int i = 0; i < numarr.length; i++) {
			counting[numarr[i] - min]++;
		}
		// 직전 요소들의 값 더해줌
		for (int i = 1; i < counting.length; i++) {
			counting[i] = counting[i] + counting[i - 1];
		}
		// 입력 배열의 역순으로 요소들을 출력배열에 채워 넣어준다
		for (int i = numarr.length - 1; i >= 0; i--) {
			/*
			 * counting 배열에서 1번 출현한 원소는 1로 표현되므로, output배열의 index로 사용할때 --연산자를 통해 미리 -1을
			 * 해준다. numarr[i]-min을 통해 counting 배열의 index 0에 있는 값이 원래 배열 최솟값의 출현횟수가 되도록 조정한다
			 */
			output[--counting[numarr[i] - min]] = numarr[i];
		}

		print(output, "계수 정렬");
	}

	// 퀵 정렬(재귀함수 사용) -1
	private void recursive_quicksort(int[] numarr) {

		recursive_innerquicksort(numarr, 0, numarr.length - 1);

		print(numarr, "재귀적 퀵 정렬");

	}

	// 퀵 정렬(재귀함수 사용) -2
	private void recursive_innerquicksort(int[] numarr, int firststart, int firstend) {
		
		if(firststart>=firstend) {
			return;
		}
		
		int start = firststart;
		int end = firstend;

		// 효율적인 pivot 값을 얻기 위해서 첫값, 마지막값, 중간값 중에
		// 크기가 중간인 값을pivot으로 선택
		int[] tmp = new int[3];
		tmp[0] = numarr[start];
		tmp[1] = numarr[(start + end) / 2];
		tmp[2] = numarr[end];
		Arrays.sort(tmp);

		int pivot = tmp[1];

		//pivot보다 작은 숫자는 pivot 왼쪽으로, pivot보다 큰 숫자는 pivot 오른쪽으로
		while (start <= end) {
			while (numarr[start] < pivot) 
				//numarr[start] < pivot이면, pivot왼쪽에 pivot보다 작은 수가 있다는 뜻
				start++;
			while (numarr[end] > pivot)
				//numarr[end] > pivott이면, pivot오른쪽에 pivot보다 큰 수가 있다는 뜻
				end--;
			
			//'pivot보다 작은 숫자는 pivot 왼쪽으로, pivot보다 큰 숫자는 pivot 오른쪽으로'가
			//만족되지 않는 경우 숫자 교환
			if(start <= end) {
				swap(numarr, start,end);
				start ++;
				end --;
			}
		}
		
		recursive_innerquicksort(numarr,firststart, start-1); //pivot왼쪽 원소들 다시 재귀로 정렬
		recursive_innerquicksort(numarr,start,firstend); //pivot오른쪽 원소들 다시 재귀로 정렬

	}

	
	//퀵 정렬(stack 사용, 재귀함수 x)
	private void stack_quicksort(int[] numarr) {
		Stack<Integer> startidx = new Stack<>();
		Stack<Integer> endidx = new Stack<>();
		
		startidx.add(0);
		endidx.add(numarr.length-1);
		
		while(!endidx.isEmpty()){ 
			//startidx와 endidx 스택은 한 쌍이므로 endidx가 비었다는 것은
			//startidx도 비었다는 것을 의미한다 ==> 정렬할 것이 더 이상 없다
			
			int firststart = startidx.pop();
			int firstend = endidx.pop();
			
			int start = firststart;
			int end = firstend;
			
			int[] tmp = new int[3];
			tmp[0] = numarr[start];
			tmp[1] = numarr[(start + end) / 2];
			tmp[2] = numarr[end];
			Arrays.sort(tmp);

			int pivot = tmp[1];
			
			//pivot보다 작은 숫자는 pivot 왼쪽으로, pivot보다 큰 숫자는 pivot 오른쪽으로
			while (start <= end) {
				while (numarr[start] < pivot) 
					//numarr[start] < pivot이면, pivot왼쪽에 pivot보다 작은 수가 있다는 뜻
					start++;
				while (numarr[end] > pivot)
					//numarr[end] > pivott이면, pivot오른쪽에 pivot보다 큰 수가 있다는 뜻
					end--;
				
				//'pivot보다 작은 숫자는 pivot 왼쪽으로, pivot보다 큰 숫자는 pivot 오른쪽으로'가
				//만족되지 않는 경우 숫자 교환
				if(start <= end) {
					swap(numarr, start,end);
					start ++;
					end --;
				}
			}
			
			if(start-1 > firststart) { 
				// 왼쪽 정렬할 부분이 남은경우
				// startidx와  endidx 스택에 정렬할 부분의 시작 인덱스 끝 인덱스 추가
				startidx.add(firststart);
				endidx.add(start-1);
			}
			if(start < firstend) {
				// 오른쪽 정렬할 부분이 남은경우
				// startidx와  endidx 스택에 정렬할 부분의 시작 인덱스 끝 인덱스 추가
				startidx.add(start);
				endidx.add(firstend);
			}
			
			
		}
		
		print(numarr, "비재귀적 퀵 정렬");
		
	}
	
	//셸 정렬-1
	private void shellsort(int[] numarr) {
		
		//gap이 홀수 일때 효율적이라고 알려져있다.
		int gap = numarr.length / 2;
		if(gap != 0 && gap % 2 ==0 ) {
			gap++;
		}
		
		while(gap>= 1) { //gap이 1일때가 마지막 회차
			for(int i=0; i<gap; i++) {
				//gap만큼 idx가 차이나는 원소끼리 선택정렬
				//ex. gap이 3이면, 0,3,6,9.... 원소끼리 선택정렬한다
				innershellsort(numarr, gap, i);
			}
			
			gap = gap / 2;
			if(gap != 0 && gap % 2 ==0 ) {
				gap++;
			}
			
		}
		
		print(numarr, "셸 정렬");
		
	}
	
	//셸 정렬(셸 정렬 내부에서 사용하는 선택정렬 구현) -2 
	private void innershellsort(int[] numarr, int gap, int start) {
		for(int i = start; i< numarr.length -1; i=i+gap) {
			int min = i;
			for(int j = i+gap; j<numarr.length; j=j+gap) {
				// 최솟값을 가진 인덱스 찾기
				if (numarr[j] < numarr[min]) {
					min = j;
				}
			}
			// i 번째 값과 최솟값을 교환
			swap(numarr, i, min);
		}
	}
	
	//기수 정렬
	private void radixsort(int[] numarr, int data_length) {
		
		//0~9번의 Queue 생성
		Queue<Integer>[] queues= new Queue[10]; 
		for(int i = 0; i<queues.length; i++) {
			queues[i] = new LinkedList<>();
		}
		
		//데이터의 길이만큼 반복
		while(data_length != 0) {
			for(int i = 0; i<numarr.length; i++) {
				//자릿수에 해당하는 수 구하기
				int digit = (numarr[i]%(int)Math.pow(10,data_length))/(int)Math.pow(10,data_length-1); 
				//자릿수에 해당하는 수를 기준으로 알맞은 번호의 queue에 넣기
				queues[digit].add( numarr[i]); 
			}
			int i, p;
			//0번  Queue 부터 원소 꺼내기
			for(i = 0, p =0; i<numarr.length; i++) {
				while(!queues[i].isEmpty()) {
					numarr[p++] =queues[i].poll();
				}
			}
			data_length--;
		}
		
		print(numarr, "기수 정렬");
		
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
		int[] numarr = { 5, 1, 3, 4, 2, 6 }; // 정렬할 배열
		System.out.println("원래 배열:" + Arrays.toString(numarr));

		System.out.println("");
		
		sort Sort = new sort();

		Sort.bubblesort(numarr);
		System.out.println("");
		
		Sort.imporvedbubblesort(numarr);
		System.out.println("");

		Sort.insertionsort(numarr);
		System.out.println("");
		
		Sort.selectionsort(numarr);
		System.out.println("");
		
		Sort.TopDown_mergesort(numarr);
		System.out.println("");
		
		Sort.BottomUp_mergesort(numarr);
		System.out.println("");
		
		Sort.priorityQueue_heapsort(numarr);
		System.out.println("");
		
		Sort.implementing_heap_heapsort(numarr);
		System.out.println("");
		
		Sort.in_place_heapsort(numarr);
		System.out.println("");
		
		Sort.countingsort(numarr,6,1);
		System.out.println("");
		
		Sort.recursive_quicksort(numarr);
		System.out.println("");
		
		Sort.stack_quicksort(numarr);
		System.out.println("");
		
		Sort.shellsort(numarr);
		System.out.println("");
		
		Sort.radixsort(numarr,1);
		System.out.println("");
	}
}
