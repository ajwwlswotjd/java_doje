package step4;

import java.util.Arrays;
import java.util.Random;

/*
 * # 셔플[문제]
 * 배열의 있는 값을 다음과 같은 방법으로 무작위로 셔플하는 프로그램을 작성하시오.
 * 1. 100번을 반복한다.
 * 2. 0~9사이의 랜덤한 숫자를 저장한다.
 * 3. arr의 0번째 값과 arr의 위 랜덤 숫자 위치의 값을 교체한다.
 * 4. 이를 통해 arr 배열의 값을 섞을 수 있다.
 */

public class Ex05 {
	public static void main(String[] args) throws InterruptedException {

		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Random rnd = new Random();
		for(int i=0; i < 100; i++) {
			int rndNum = rnd.nextInt(10);
			int temp = arr[0];
			arr[0] = arr[rndNum];
			arr[rndNum] = temp;
			Thread.sleep(100);
			System.out.println(i+"번째 : "+Arrays.toString(arr));
		}
	}
}
