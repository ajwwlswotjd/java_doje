package step6;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
 * # 영화관 좌석예매[문제]
 * 1. 사용자로부터 좌석번호(index)를 입력받아 예매하는 시스템이다.
 * 2. 예매가 완료되면 해당 좌석 값을 1로 변경한다.
 * 3. 이미 예매가 완료된 좌석은 재구매할 수 없다.
 * 4. 한 좌석당 예매 가격은 12000원이다.
 * 5. 프로그램 종료 후, 해당 영화관의 총 매출액을 출력한다.
 * 
 * 좌석예매는 행(알파벳)과 열(숫자)을 입력받아서 예매를 한다.
 *    0  1  2  3  4 
 * A  1  0  0  0  0
 * B  0  1  0  0  0
 * C  1  1  0  0  0
 * D  0  0  0  0  0
 * E  0  0  0  0  0
 * 
 * 예매할 행과 열을 입력하세요.
 * 
 */

class Movie{
	int[][] seat = new int[5][5]; //25개의 자리
	int money = 0;
	
	String[] stringArr = {"A","B","C","D","E"};
	
	private int stringToInt(String str) {
		if(str.equals("A") || str.equals("a")) return 0;
		if(str.equals("B") || str.equals("b")) return 1;
		if(str.equals("C") || str.equals("c")) return 2;
		if(str.equals("D") || str.equals("d")) return 3;
		if(str.equals("E") || str.equals("e")) return 4;
		else return -1;
	}
	
	public boolean setSeat(String row, int col) {
		//좌석 예매 매서드
		// 성공시 true, 실패시 false를 반환
		int seatI = this.stringToInt(row);
		if(seatI==-1 || col < 0 || col > 4) {
			System.out.println("존재하지 않는 좌석 입니다.");
			return false;
		}
		
		if(this.seat[seatI][col]==1) {
			System.out.println("해당 좌석은 이미 예매되었습니다.");
			return false;
		}
		
		this.seat[seatI][col] = 1;
		this.money+=12000;
		System.out.println("좌석 예매에 성공하였습니다.");
		return true;
	}
	
	public void printResult() {
		// 영화관 현재 총매출액을 출력하는 매서드
		DecimalFormat fmt = new DecimalFormat("###,###");
		System.out.println("매출액 : " + fmt.format(money)+"원");
	}
	
	public void printSeat() {
		// 영화관의 현재 좌석 예매 현황을 출력하는 매서드
		System.out.println("  0 1 2 3 4");
		for(int i = 0; i < this.seat.length; i++) {
			System.out.print( this.stringArr[i]+" ");
			for(int j=0; j < this.seat[i].length; j++) System.out.print(this.seat[i][j]+" ");
			System.out.println("");
		}
	}
}

public class Ex04 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Movie mega = new Movie();
		
		while(true) {
			
			mega.printSeat();
			System.out.println("[MEGA MOVIE]");
			System.out.println("[1]좌석예매");
			System.out.println("[2]종료하기");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.print("행 입력 (A,B,C,D,E) : ");
				String h = scan.next();
				System.out.print("열 입력 (0,1,2,3,4) : ");
				int y = scan.nextInt();
				mega.setSeat(h, y);
			}
			else if(sel == 2) {
				
				mega.printResult();
				
				break;
			}else continue;
		}
		
		scan.close();
		
	}
}
