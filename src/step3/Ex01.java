package step3;

import java.util.Scanner;

/*
 * # 구구단 게임(3단계)[문제]
 * 1. 1부터 9까지의 랜덤 숫자를 2개 뽑아내서,
 * 2. 곱셈 문제를 5회 출제한다.  
 * 3. 한 문제당 20점으로 게임 종료 후 게임성적을 출력한다.
 * 
 * 문제의 제시는 "5 X 4 = ?" 형식으로 제시
 * 
 * 문제가 제시되고 숫자를 입력하면  입력값에 따라 "맞음" 또는 "틀림" 을 출력해주고 최종 5번문제를 풀고나면
 * 최종 점수는 몇점인지를 표시한다.
 */

public class Ex01 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int score = 0;
		
		for(int i=0; i < 5; i++) {
			
			int num1 = (int)(Math.random()*9)+1;
			int num2 = (int)(Math.random()*9)+1;
			System.out.println(num1 + " X " + num2+" = ?");
			int answer = in.nextInt();
			System.out.println(answer==num1*num2 ? "맞음" : "틀림");
			score += answer==num1*num2 ? 20 : 0;
		}
		System.out.println("최종 점수 : "+score+"점");
		
		in.close();
	}
}
