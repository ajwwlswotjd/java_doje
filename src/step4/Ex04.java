package step4;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # ATM(3단계)[문제]
 * 1. 가입
 * . 계좌번호와 비밀번호를 입력받아 가입
 * . 계좌번호 중복검사
 * . 5명이상 입력시 배열의 크기를 자동으로 늘려줌
 * 2. 탈퇴
 * . 계좌번호를 입력받아 탈퇴
 * 
 * 주의:리스트 사용금지
 */

public class Ex04 {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int[] accs = { 1001, 1002, 0, 0, 0 }; // 계좌번호 배열
		int[] pws = { 1111, 2222, 0, 0, 0 }; // 비밀번호 배열

		int cnt = 2;

		boolean run = true;
		while (run) {
			System.out.println("계좌 번호 : "+Arrays.toString(accs));
			System.out.println("비밀 번호 : "+Arrays.toString(pws));
			System.out.println("1.가입");
			System.out.println("2.탈퇴");

			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();

			if (sel == 1) {
				System.out.print("가입할 계좌번호를 입력 : ");
				int id = scan.nextInt();
				if (id <= 0) {
					System.out.println("잘못된 계좌번호 입니다.");
					continue;
				}

				boolean dup = false;
				for (int i = 0; i < accs.length; i++) {
					if (id == accs[i]) {
						System.out.println("이미 등록된 계좌 입니다.");
						dup = true;
						break;
					}
				}
				if (dup)
					continue;

				int emptyIndex = -1;

				for (int i = 0; i < accs.length; i++) {
					if (accs[i] == 0) {
						emptyIndex = i;
						break;
					}
				}

				if (emptyIndex == -1) { // 배열을 늘려야함
					int[] arrTemp = accs;
					accs = new int[arrTemp.length + 1];
					for (int i = 0; i < arrTemp.length; i++)
						accs[i] = arrTemp[i];
					accs[accs.length - 1] = id;
				} else { // 빈공간이 있음
					accs[emptyIndex] = id;
				}

				System.out.print("비밀번호 입력 : ");
				int pwd = scan.nextInt();

				emptyIndex = -1;
				for (int i = 0; i < pws.length; i++) {
					if (pws[i] == 0) {
						emptyIndex = i;
						break;
					}
				}
				if (emptyIndex == -1) { // 배열을 늘려야함
					int[] arrTemp = pws;
					pws = new int[arrTemp.length + 1];
					for (int i = 0; i < arrTemp.length; i++)
						pws[i] = arrTemp[i];
					pws[pws.length - 1] = pwd;
				} else { // 빈공간이 있음
					pws[emptyIndex] = pwd;
				}

			}

			else if (sel == 2) {
				System.out.print("탈퇴할 계좌번호를 입력 : ");
				int id = scan.nextInt();
				int userIndex = -1;
				for(int i=0; i < accs.length;i++) if(accs[i] == id) userIndex = i;
				if(userIndex == -1) {
					System.out.println("존재하지 않는 계좌번호 입니다.");
					continue;
				}
				System.out.print("비밀번호 입력 : ");
				int pwd = scan.nextInt();
				if(pws[userIndex]==pwd) {
					
					
					int[] arrTemp = accs;
					accs = new int[accs.length-1];
					if(userIndex==0) for(int i=0; i < accs.length; i++) accs[i] = arrTemp[i+1]; 
					else {
						for(int i=0; i < userIndex;i++) accs[i] = arrTemp[i];
						for(int i=userIndex; i < accs.length;i++) accs[i] = arrTemp[i+1];
					}
					
					int[] arrTemp2 = pws;
					pws = new int[pws.length-1];
					if(userIndex==0) for(int i=0; i < pws.length; i++) pws[i] = arrTemp[i+1]; 
					else {
						for(int i=0; i < userIndex;i++) pws[i] = arrTemp[i];
						for(int i=userIndex; i < pws.length;i++) pws[i] = arrTemp[i+1];
					}
					
					
					
				}else {
					System.out.println("비밀번호가 틀렸습니다.");
					continue;
				}
			}

		}

		scan.close();

	}
}
