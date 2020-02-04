package step5;

import java.util.Arrays;

/*
 * # 정렬하기[문제]
 * 1. 인덱스 0번이 나머지를 검사한다.
 * 2. 제일 큰 값을 찾아 교환한다.
 * 3. 인덱스 1증가한다.
 * 4. [1~3]을 끝까지 반복한다.
 * 예)
 * 10, 50, 30, 40, 80, 7
 * 80, 50, 30, 40, 10, 7
 * 80, 50, 30, 40, 10, 7
 * 80, 50, 40, 30, 10, 7
 */

public class Ex02 {
	public static void main(String[] args) {
		
		int[] score = {10, 50, 30, 40, 80, 7};
		for(int i=0; i < score.length; i++) {
			System.out.println(Arrays.toString(score));
			int maxIndex = i; 
			for(int j=i; j < score.length;j++) if(score[j] > score[maxIndex]) maxIndex = j;
			int temp = score[maxIndex];
			score[maxIndex] = score[i];
			score[i] = temp;
		}
		
		
	}
}
