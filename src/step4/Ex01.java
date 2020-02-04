package step4;

import java.util.Arrays;

/*
 * # OMR카드[문제]
 * 1. 배열 answer는 시험문제의 정답지이다.
 * 2. 배열 student에는 총 10명의 학생들의 OMR카드 값이 기록되어 있다.
 * 3. answer와 student 값을 비교해 정오표와 성적을 출력한다. 
 * 4. 점수는 한 문제당 20점이다.
 * 예)
 * answer  = {1, 3, 4, 2, 5}
 * student[0] = {1, 1, 4, 4, 3}
 * "0번 학생 {O, X, O, X, X} : 40점" 
 * 
 */

public class Ex01 {
	public static void main(String[] args) {

		int[] answer = {1, 3, 4, 2, 5};
		int[][] student = {
				{1, 1, 4, 4, 3},
				{2, 3, 4, 5, 2},
				{1, 3, 4, 2, 4},
				{1, 2, 4, 2, 5},
				{1, 3, 4, 2, 5}
		};
		
		for (int j=0; j < answer.length; j++) {
			int score = 0;
			System.out.print(j+"번 학생 {");
			for(int i=0; i <student[j].length; i++) {
				if(student[j][i] == answer[i]) {
					score+=20;
					System.out.print(" O ");
				}else System.out.print(" X ");
			}
			System.out.println("} : "+score+"점");
		}
		
		//이곳에 코드를 작성하시오.
		
	}
}
