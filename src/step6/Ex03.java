package step6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * # OMR카드[문제]
 * 1. 배열 answer는 시험문제의 정답지이다.
 * 2. 학생 답안 데이터는 data.txt 파일을 읽어서 student 리스트에 넣어준다. 
 * 3. answer와 student 값을 비교해 정오표를 출력한다.
 * 4. 한 문제당 20점이다.
 * 예)
 * answer  = {1, 3, 4, 2, 5}
 * student = {1, 1, 4, 4, 3}
 * 정오표      = {O, X, O, X, X}
 * 성적         = 40점
 */

class Student2 {
	private String name = "";
	private int[] marking = new int[5];
	private String[] oxArr = new String[5];
	public int score = 0;
	public int rank = 0;
	public int[] getMarking() {
		return marking;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMarking(int[] arr) {
		this.marking = arr;
	}
	public String getName() {
		return this.name;
	}
	public String[] getOxArr() {
		return this.oxArr;
	}
	
	public void setOxArr(String[] arr) {
		this.oxArr = arr;
	}
	@Override
		public String toString() {
			return this.name+" : "+Arrays.toString(this.oxArr)+" "+this.score+"점 "+this.rank+"등";
			// 이유진 : [O, O, O, O, O] 100점   2등
		}
}

class OmrCard{
	private int[] answer = {1, 2, 3, 4, 5};		// 시험답안
	private List<Student2> markData = new ArrayList<>();
	private int[] answerCnt = new int[5];
	public void readData(String file) {
		//data.txt 파일을 읽어들여서 markData를 만들어줌.
		try {
			FileInputStream fis = new FileInputStream(new File(file));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			while(true) {
				String line = br.readLine();
				if(line==null) break;
				Student2 stdt = new Student2();
				String[] stringArr = line.split(":");
				stdt.setName(stringArr[0]);
				String[] arr = stringArr[1].split(",");
				int[] intArr = new int[5];
				for(int i=0; i < intArr.length; i++) intArr[i] = Integer.parseInt(arr[i]);
				stdt.setMarking(intArr);
				markData.add(stdt);
			}
			
			fis.close();
			isr.close();
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("스코어링 도중 오류발생");
		}
	}
	
	public void scoring() {
		//읽어들인 데이터를 채점하여 각 학생별로 정오표와 점수를 기록 
		// 이를 위해 필요한 멤버변수를 Student에 만들어 줄 수 있음.
		// 문제당 20점으로 하여 채점함.
		
		for(int i=0; i < markData.size();i++) {
			String[] oxArr = new String[5];
			for(int j=0; j < oxArr.length; j++) {
				if(markData.get(i).getMarking()[j] == answer[j]) {
					oxArr[j] = "O";
					markData.get(i).score += 20;
					this.answerCnt[j]++;
				}else oxArr[j] = "X";
			}
			markData.get(i).setOxArr(oxArr);
		}
		
		for(int i=0; i < markData.size(); i++) {
			int rank = 1;
			for(int j=0; j < markData.size(); j++) if(markData.get(i).score < markData.get(j).score) rank++;
			this.markData.get(i).rank = rank;
		}
		
		Comparator<Student2> cmp = new Comparator<Student2>() {
			@Override
			public int compare(Student2 o1, Student2 o2) {
				return o2.score - o1.score;
			}
		};
		
		Collections.sort(this.markData,cmp);
		for(int i = 0; i < this.markData.size(); i++) System.out.println(this.markData.get(i).toString());
		
	}
	public void printStatus(String file) {
		//다음과 같은 형식으로 25명의 데이터를 주어진 파일명으로 출력한다.
		//같은 점수는 같은 등수로 하고 다음등수는 해당인원을 합친 다음부터 시작한다. 즉 1등이 3명이면 다음 점수는 4등이다.
		//[소프트웨어 도제반 성적표]
		//정재성 : [O, O, O, O, O] 200점   1등
		//강은진 : [O, O, O, O, O] 100점   2등
		//이유진 : [O, O, O, O, O] 100점   2등
		//강영미 : [O, O, O, X, O]  80점   4등
		// ............................
		//최선한 : [X, X, X, X, X]   0점   25등 
		//================================
		//총점 : 0000점, 평균 : XX.XX점 
		//최고점 : XX점, 최저점 : XX점
		//문항별 정답율
		// [1번 : 45.00%, 2번 :84.00%, 3번 : 100.00%, 4번 :45.00%, 5번:80.00%]
		
		//문항별 정답율은 해당 문항을 맞춘 사람의 수를 전체 응답자수의 백분율로 구한 수이다. 
		try {
			FileOutputStream fos = new FileOutputStream(new File(file));
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("[소프트웨어 도제반 성적표] ");
			bw.flush();
			bw.newLine();
			int sum = 0;
			int maxIndex = 0; 
			int minIndex = 0;
			for(int i=0; i < this.markData.size(); i++) {
				bw.write(this.markData.get(i ).toString());
				bw.flush();
				bw.newLine();
				if(this.markData.get(i).score > this.markData.get(maxIndex).score) maxIndex = i;
				if(this.markData.get(i).score < this.markData.get(minIndex).score) minIndex = i;
				sum += this.markData.get(i).score;
			}
			
			bw.write("=========================================");
			bw.flush();
			bw.newLine();
			
			DecimalFormat df = new DecimalFormat("###,###");
			
			bw.write("총점 : "+df.format(sum)+"점  평균 : "+(double)sum/this.markData.size()+"점");
			bw.flush();
			bw.newLine();
			
			bw.write("최고점 : "+this.markData.get(maxIndex).score+"점  최저점 : "+this.markData.get(minIndex).score+"점");
			bw.flush();
			bw.newLine();
			
			bw.write("=========================================");
			bw.flush();
			bw.newLine();
			bw.write("[문항별 정답율]");
			bw.flush();
			bw.newLine();
			
			for(int i = 0 ; i < this.answerCnt.length; i++) {
				bw.write("문항 "+(i+1)+"번 => 정답자 : "+this.answerCnt[i]+"명   정답율 : "+((double)this.answerCnt[i]/this.markData.size())*100+"%");
				bw.flush();
				bw.newLine();
			}
			
			
//			fos.close();
//			osw.close();
//			bw.close();
			
			
		} catch (Exception e) {
			System.out.println("파일 출력 중 오류발생");
			e.printStackTrace();
		}
		
	}
}

public class Ex03 {
	public static void main(String[] args) {
		
		OmrCard omr = new OmrCard();
		omr.readData("data/data.txt");
		omr.scoring();
		omr.printStatus("data/result.txt");
	}
}