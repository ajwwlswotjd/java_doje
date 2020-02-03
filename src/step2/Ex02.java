package step2;

import java.util.Scanner;

/*
 * # 놀이기구 이용제한[문제]
 * 1. 키를 입력받는다.
 * 2. 키가 110 이상이면, 놀이기구 이용이 가능하다.
 * 3. 키가 110 미만이면, 놀이기구를 이용할 수 없다.
 * 4. 단, 부모님과 함께 온 경우에는 놀이기구를 이용할 수 있다.
 * 5. 부모님과의 동행여부를 1(yes) 또는 0(no)을 입력받아 확인한다.
 */

public class Ex02 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("키 입력 : ");
		int key = in.nextInt();
		if(key >= 110) System.out.println("놀이기구 이용 ㅆㄱㄴ");
		else {
			System.out.print("부모님 동행 여부(1,0) : ");
			int asdf = in.nextInt();
			System.out.println(asdf==1 ? "놀이기구 이용 ㅆㄱㄴ" : asdf==0 ? "안됌" : "입력이 잘못됌");
		}
		in.close();
	}
}
