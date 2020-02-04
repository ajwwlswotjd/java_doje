package step4;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/*
 * # 영화관 좌석예매[문제]
 * 1. 사용자로부터 좌석번호(index)를 입력받아 예매하는 시스템이다.
 * 2. 예매가 완료되면 해당 좌석 값을 1로 변경한다.
 * 3. 이미 예매가 완료된 좌석은 재구매할 수 없다.
 * 4. 한 좌석당 예매 가격은 12000원이다.
 * 5. 프로그램 종료 후, 해당 영화관의 총 매출액을 출력한다.
 * 예)
 * seat = 0 0 0 0 0 0 0
 * 
 * 좌석선택 : 1
 * seat = 0 1 0 0 0 0 0
 * 
 * 좌석선택 : 3
 * seat = 0 1 0 1 0 0 0
 * 
 * 좌석선택 : 3
 * seat = 0 1 0 1 0 0 0
 * 이미 예매가 완료된 자리입니다.
 * ----------------------
 * 매출액 : 24000원
 */

public class Ex02 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int[] seat = {0,0,0,0,0,0,0};
		int bill = 0;
		boolean run = true;
		while(run) {
			System.out.println("=메가 영화관=");
			System.out.println("1.좌석예매");
			System.out.println("2.종료");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.println(Arrays.toString(seat));
				System.out.print("예매할 좌석 입력 : ");
				int idx = scan.nextInt();
				if(idx >= seat.length) {
					System.out.println("없는 좌석입니다.");
					continue;
				}
				if(seat[idx]==0) {
					seat[idx] = 1;
					bill+=12000;
					System.out.println(idx+"번째 좌석이 예매되었습니다.");
				}else if(seat[idx]==1) System.out.println("이미 예매가 된 자리 입니다.");
			}
			else if(sel == 2) {
				System.out.println("--------------------------------------------");
				System.out.println("매출액 : "+new DecimalFormat("###,###").format(bill)+"원");
				run = false;
			}
		}
		
		scan.close();
	}
}
