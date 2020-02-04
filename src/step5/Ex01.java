package step5;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # 최대값 구하기(3단계)[문제]
 * 1. 가장 큰 값을 찾아 입력한다.
 * 2. 정답이면 해당 값을 0으로 변경한다.
 * 3. arr배열의 모든 값이 0으로 변경되면 프로그램은 종료된다.
 * 예)
 * 11, 87, 42, 100, 24
 * 입력 : 100
 * 
 * 11, 87, 42, 0, 24
 * 입력 : 87
 * 
 * 11, 0, 42, 0, 24
 */

public class Ex01 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] arr = { 11, 87, 42, 100, 24 };

		while (true) {
			System.out.println(Arrays.toString(arr));

			int max = (int) Double.NEGATIVE_INFINITY;
			int maxIndex = 0;
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] > max) maxIndex = i;
				max = arr[maxIndex];
			}

			System.out.print("가장 큰 값 입력 : ");
			int num = in.nextInt();
			if (num == arr[maxIndex]) {
				System.out.println("정답");
				arr[maxIndex] = 0;
			}else {
				System.out.println("오답");
				continue;
			}
			int sum = 0;
			for (int i = 0; i < arr.length; i++)
				sum += arr[i];
			if (sum == 0) {
				System.out.println("끝!");
				break;
			}
		}
		in.close();
	}
}
