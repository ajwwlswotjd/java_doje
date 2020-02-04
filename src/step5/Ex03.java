package step5;

import java.text.DecimalFormat;
import java.util.Scanner;

// # 관리비[문제]

public class Ex03 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] apt = {
			{101, 102, 103},	
			{201, 202, 203},	
			{301, 302, 303}	
		};
			
		int[][] pay = {
			{1000, 2100, 1300},	
			{4100, 2000, 1000},	
			{3000, 1600,  800}
		};
			
		// 문제 1) 각층별 관리비 합 출력
		// 정답 1) 4400, 7100, 5400
		
		// 문제 2) 호를 입력하면 관리비 출력
		// 예    2) 입력 : 202	관리비 출력 : 2000
		
		// 문제 3) 관리비가 가장 많이 나온 집, 적게 나온 집 출력
		// 정답 3) 가장 많이 나온 집(201), 가장 적게 나온 집(303)
		
		// 문제 4) 호 2개를 입력하면 관리비 교체
		
		System.out.println("============각 층별 관리비 합============");
		for(int i=0; i < pay.length; i++) {
			 int sum = 0;
			 for(int j=0; j < pay[i].length;j++) sum += pay[i][j];
			 System.out.println((i+1)+"층 : "+comma(sum)+"원");
		}
		
		System.out.println("============관리비 가장 많은집, 가장 적은집============");
		
		int maxI = 0;
		int maxJ = 0;
		int minI = 0;
		int minJ = 0;
		for(int i=0; i < pay.length; i++) {
			for(int j=0; j < pay.length; j++) {
				if(pay[maxI][maxJ] < pay[i][j]) {
					maxI = i;
					maxJ = j;
				}
				
				if(pay[minI][minJ] > pay[i][j]) {
					minI = i;
					minJ = j;
				}
			}
		}
		
		System.out.println("관리비 MAX : "+apt[maxI][maxJ]+"호 : "+comma(pay[maxI][maxJ])+"원");
		System.out.println("관리비 MIN : "+apt[minI][minJ]+"호 : "+comma(pay[minI][minJ])+"원");
		
		
		System.out.println("============문제 2,4번============");

		while(true) {
			System.out.println("[0] 종료");
			System.out.println("[1] 문제2번 풀기 (호 입력하면 관리비 나옴)");
			System.out.println("[2] 문제4번 풀기 (호 두개 입력하면 관리비 바꿔치기)");
			System.out.print("입력 : ");
			int sel = in.nextInt();
			
			if(sel==1) {
				System.out.print("호 입력 : ");
				int hoNum = in.nextInt();
				int hoI = -1;
				int hoJ = -1;
				
				for(int i=0; i < apt.length; i++) {
					for(int j=0; j < apt.length;j++) {
						if(hoNum == apt[i][j]) {
							hoI = i;
							hoJ = j;
						}
					}
				}
				
				if(hoI==-1 || hoJ==-1) {
					System.out.println("존재하지 않는 호 입니다.");
					continue;
				}
				
				System.out.println(apt[hoI][hoJ]+"호 의 관리비 : "+comma(pay[hoI][hoJ])+"원");
				
				
			}
			else if(sel==2) {
				
				 System.out.print("호 두개 입력 : ");
				 int ho1 = in.nextInt();
				 int ho2 = in.nextInt();
				 
				 int ho1I = -1;
				 int ho1J = -1;
				 for(int i=0; i < apt.length;i++) {
					 for(int j=0; j < apt.length; j++) {
						 if(ho1 == apt[i][j]) {
							 ho1I = i;
							 ho1J = j;
						 }
					 }
				 }
				 
				 int ho2I = -1;
				 int ho2J = -1;
				 for(int i=0; i < apt.length;i++) {
					 for(int j=0; j < apt.length; j++) {
						 if(ho2 == apt[i][j]) {
							 ho2I = i;
							 ho2J = j;
						 }
					 }
				 }
				 if(ho1I==-1 || ho2I == -1 || ho1J == -1 || ho2J == -1) {
					 System.out.println("존재하지 않는 호를 검색하셨습니다.");
					 continue;
				 }
				 
				 int payTemp = pay[ho1I][ho1J];
				 pay[ho1I][ho1J] = pay[ho2I][ho2J]; 
				 pay[ho2I][ho2J] = payTemp;
				 System.out.println("관리비 교체가 완료되었습니다.");
			}
			else if(sel==0) break;
			else continue;
		}
		
		
		in.close();
	}
	
	public static String comma(int num) {
		DecimalFormat format = new DecimalFormat("###,###");
		return format.format(num);
	}
}
