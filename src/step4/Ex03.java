package step4;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # 배열 컨트롤러(1단계)[문제]
 * 1) 추가 : 맨 끝에 삽입
 * 2) 삭제 : 삭제를 원하는 인덱스를 입력하면 삭제
 * 3) 삽입 : 삽입을 원하는 인덱스와 값을 입력하면 삽입
 * 
 * 주의 : 리스트 사용금지!
 *  
*/

public class Ex03 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int[] arr = {10, 1, 2, 3, 4, 5, 6};
		int cnt = 2;
		
		boolean run = true;
		while(run) {
			System.out.println(Arrays.toString(arr));
			System.out.println("[1]추가");
			System.out.println("[2]삭제");
			System.out.println("[3]삽입");
			System.out.println("[0]종료");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				
				System.out.print("추가할 값 입력 : ");
				int data = scan.nextInt();
				
				int[] arrTemp = arr;
				arr = new int[arrTemp.length+1];
				for(int i=0; i < arrTemp.length;i++) arr[i] = arrTemp[i];
				arr[arr.length-1] = data;
				
				
			}
			else if(sel == 2) {
				
				System.out.print("삭제할 인덱스 입력 : ");
				int data = scan.nextInt();
				if(data > arr.length || data < 0) {
					System.out.println("존재하지 않는 인덱스 입니다.");
					continue;
				}
				int[] arrTemp = arr;
				arr = new int[arr.length-1];
				if(data==0) for(int i=0; i < arr.length; i++) arr[i] = arrTemp[i+1]; 
				else {
					for(int i=0; i < data;i++) arr[i] = arrTemp[i];
					for(int i=data; i < arr.length;i++) arr[i] = arrTemp[i+1];
				}
				 
				
			}
			else if(sel == 3) {
				
				System.out.print("삽입할 위치 입력 : ");
				int idx = scan.nextInt();
				if(idx > arr.length || idx < 0) {
					System.out.println("존재하지 않는 인덱스 입니다.");
					continue;
				}
				System.out.print("삽입할 값 입력 : ");
				int data = scan.nextInt();
				
				int[] arrTemp = arr;
				arr = new int[arr.length+1];
				for(int i=0; i < idx; i++) arr[i] = arrTemp[i];
				arr[idx] = data;
				for(int i= idx+1; i < arr.length;i++) arr[i] = arrTemp[i-1];
				
				
			}
			else if(sel == 0) {
				run = false;
				System.out.println("프로그램 종료");
			}
			
		}
		
		scan.close();
		
	}
}
