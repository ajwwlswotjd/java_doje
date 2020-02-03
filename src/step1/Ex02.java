package step1;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # 값 교체[문제]
 * 1. 2개의 값 x, y를 입력받는다. 입력받은 값을 출력한다.
 * 2. 두 개의 값 x, y를 교체한다. 교체 한 뒤 출력한다.
 * 예시)
 * 20, 10 을 입력했다면 최초 20, 10을 출력 
 * 그 이후에 값을 교체하여 10, 20을 출력 
 * 출력형식은 {x:20, y:10} 의 형태로 출력되도록 하라
 * 출력만 바꾸는 것은 인정되지 않으며, 실제 값이 변경되도록 하라.
 */

public class Ex02 {
	public static void main(String[] args) {

		int x, y;
		Scanner in = new Scanner(System.in);
		x = in.nextInt();
		y = in.nextInt();
		System.out.println("{x:"+x+", y:"+y+"}");
		int temp = x;
		x = y;
		y = temp;
		System.out.println("{x:"+x+", y:"+y+"}");
		in.close();
	}
}
