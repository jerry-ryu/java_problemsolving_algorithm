package SortAlgorithm;

import java.util.Arrays;

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

	//병합정렬-1
	private void mergesort(int[] numarr) {
		int[] tmparr = new int[numarr.length]; //임시배열
		
		innermergesort(numarr,0,numarr.length-1, tmparr);
		
		print(tmparr, "병합 정렬");
	}
	
	
	//병합 정렬-2
	private void innermergesort(int[] numarr, int start, int end, int[] tmparr) {
		
		
		
		if(start >= end) {
			return;
		}
		
		int mid = (start+end)/2;
		innermergesort(numarr,start,mid,tmparr);
		innermergesort(numarr,mid+1,end,tmparr);
		int left = start;
		int right = mid+1;
		int idx = left;
		
		while(left<=mid || end>=right ) {
			if( right>end || (left<= mid && numarr[left]<numarr[right]) ){
				tmparr[idx] = numarr[left];
				idx++;
				left++;
			}else {
				tmparr[idx] = numarr[right];
				idx++;
				right++;
			}
		}	
		
		for (int i = start; i <= end; i++) {
			numarr[i] = tmparr[i];
		}
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

		Sort.mergesort(numarr);

	}
}
