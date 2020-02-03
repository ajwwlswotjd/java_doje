package step1;

import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/*
 * # 화폐매수[문제]
 * 금액을 입력받아 해당 금액의 화폐 종류 별 화폐 매수를 출력한다.
 * 예시)
 * 만약 87900원이 입력되었다면 다음과 같이 출력된다. (화폐단위는 한국을 기준으로 하며 없는 단위의 매수는 출력되지 않는다.)
 * 5만원 : 1장
 * 1만원 : 3장
 * 5천원 : 1장
 * 1천원 : 2장
 * 5백원 : 1개
 * 1백원 : 4개
 */

public class Ex03 {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int money = in.nextInt();
		int[] arr = {50000,10000,5000,1000,500,100};
		for (int don : arr) {
			if(money/don > 0) {
				System.out.println(don+"원권 : "+money/don+"장");
				money %= don;
			}
		}
		in.close();
		
//		<?php ?>
//		<?php echo date("Y:m:d") ?>
//		<script>
//			document.queryselector("asdf").addEventListener("click",(e)=>{
//				const log = console.log;
//				"use strict";
//				let asdf = [];
//				asdf.push(new Date());
//				asdf.forEach(x=>{
//		 			log(x.toLocaleString());
//				});
//			});
//		</script>
	}
}
