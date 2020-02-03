package step2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/*
 * # ATM[문제]
 * [1]로그인
 * . 계좌번호와 비밀번호를 입력받아 로그인을 처리한다.
 * . 이미 로그인 된 상태에서 다시 이용할 수 없다.
 * [2]로그아웃
 * . 로그아웃 상태에서 이용할 수 없다.
 * [3]입금
 * . 로그인 상태에서 이용할 수 있다.
 * . 입금할 금액을 입력받아 입금을 진행한다.
 * [4]출금
 * . 로그인 상태에서 이용할 수 있다.
 * . 출금할 금액이 계좌잔액을 초과할 경우 출금을 진행할 수 없다.
 * [5]이체
 * . 로그인 상태에서 이용할 수 있다.
 * . 이체할 계좌번호를 입력받아 처리한다.
 * . 이체할 금액이 계좌잔액을 초과할 경우 이체를 진행할 수 없다.
 * [6]잔액조회
 * . 로그인 상태에서 이용할 수 있다.
 * . 로그인 된 회원의 계좌잔액을 출력한다.
 */


public class Ex03 {
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		String acc1 = "gondr";
		String pw1 = "1234";
		int money1 = 10000;
		
		String acc2 = "yydh";
		String pw2 = "1234";
		int money2 = 20000;
		
		
		boolean sign = false;
		String userAcc="";

		/*
		 * 이곳에 필요한 변수를 선언하세요.
		 * (Hint. 로그인상태를 저장하거나 누가 로그인되었는지를 저장하는 변수가 필요합니다.)
		 */
		
		while(true) {
			System.out.println("[MEGA ATM]");
			System.out.println("[1]로그인");
			System.out.println("[2]로그아웃");
			System.out.println("[3]입금");
			System.out.println("[4]출금");
			System.out.println("[5]이체");
			System.out.println("[6]잔액조회");
			System.out.println("[0]종료");
			
			System.out.print("[메세지] 메뉴를 선택하세요 : ");
			int sel = scan.nextInt();
			
			/* 아래의 중괄호에 알맞은 코드를 작성하세요 */
			if(sel == 1) { // 로그인
				if(sign) { // 로그인된 상태
					System.out.println("[메세지] 이미 로그인이 되어있는 상태입니다.");
					continue;
				}
				System.out.print("[메세지] 계좌번호 입력 : ");
				userAcc = scan.next();
				sign = userAcc.equals(acc1) || userAcc.equals(acc2);
				if(!sign) {
					System.out.println("[메세지] 존재하지 않는 계좌번호 입니다.");
					sign = false;
					continue;
				}
				System.out.print("비밀번호 입력 : ");
				sign = scan.next().equals("1234");
				System.out.println("[메세지] 로그인에 " + (sign ? "성공" : "실패")+"하였습니다.");
			}
			else if(sel == 2) { // 로그아웃
				if(!sign) System.out.println("[메세지] 로그인된 상태에서만 사용이 가능합니다.");
				else {
					sign = false;
					System.out.println("[메세지] 로그아웃 하였습니다.");
				}
			}
			else if(sel == 3) { // 입금
				if(!sign) System.out.println("[메세지] 로그인된 상태에서만 사용이 가능합니다.");
				else {
					System.out.print("[메세지] 입금할 금액 입력 : ");
					int money = scan.nextInt();
					if(userAcc.equals("gondr")) money1 += money;
					else money2 += money;
					System.out.println("[메세지] 입금이 완료되었습니다.");
				}
			}
			else if(sel == 4) {
				if(!sign) System.out.println("[메세지] 로그인된 상태에서만 사용이 가능합니다.");
				else {
					System.out.print("[메세지] 출금할 금액 입력 : ");
					int money = scan.nextInt();
					if(userAcc.equals("gondr")) {
						if(money1 < money) {
							System.out.println("[메세지] 출금이 불가능합니다.");
							continue;
						}else money1 -= money;
					}
					if(userAcc.equals("yydh")) {
						if(money2 < money) {
							System.out.println("[메세지] 출금이 불가능합니다.");
							continue;
						}else money2 -= money;
					}
					System.out.println("[메세지] 출금이 완료되었습니다.");
				} 
			}
			else if(sel == 5) {
				if(!sign) System.out.println("[메세지] 로그인된 상태에서만 사용이 가능합니다.");
				else {
					System.out.print("[메세지] 이체할 금액 입력 : ");
					int money = scan.nextInt();
					if(userAcc.equals("gondr")) {
						if(money1 < money) {
							System.out.println("[메세지] 이체가 불가능합니다.");
							continue;
						}
					}
					if(userAcc.equals("yydh")) {
						if(money2 < money) {
							System.out.println("[메세지] 이체가 불가능합니다.");
							continue;
						}
					}
					System.out.print("[메세지] 이체할 계좌번호를 입력 : ");
					String tosAcc = scan.next();
					
					if(tosAcc.equals(userAcc)) {
						System.out.println("[메세지] 자신의 계좌로는 이체가 불가능합니다.");
						continue;
					}
					
					if(tosAcc.equals("gondr")) money1 += money;
					else if(tosAcc.equals("yydh")) money2 += money;
					else {
						System.out.println("[메세지] 자신의 계좌로는 이체가 불가능합니다.");
						continue;
					}
					
					if(userAcc.equals("gondr")) money1 -= money;
					if(userAcc.equals("yydh")) money2 -= money;
					
					System.out.println("[메세지] 이체가 성공적으로 이루어졌습니다.");
				}
			}
			else if(sel == 6) {
				if(!sign) System.out.println("[메세지] 로그인된 상태에서만 사용이 가능합니다.");
				else {
					System.out.println("계좌번호 : "+ userAcc);
					System.out.println("현재 계좌에 있는 금액  : "+(userAcc.equals("gondr") ? money1 : money2)+"원");
				}
			}
			else if(sel == 0) {
				System.out.println("[메세지]프로그램 종료");
				break;
			}
			
		}
		
		scan.close();
	}
}
