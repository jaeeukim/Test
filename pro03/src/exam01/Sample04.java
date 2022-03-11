package exam01;

import java.util.Scanner;

public class Sample04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int kor, eng, math, sum;
		double avg;
		System.out.print("국어 점수(0 ~ 100) : ");
		kor = sc.nextInt();
		System.out.print("영어 점수(0 ~ 100) : ");
		eng = sc.nextInt(); 
		System.out.print("수학 점수(0 ~ 100) : ");
		math = sc.nextInt();
		
		sum = kor + eng + math;
		avg = sum / 3.0;
		
		if(kor < 40 || eng < 40 || math <40) {
			System.out.println("과락된 과목이 있어 불합격 되었습니다.");
			if(kor < 40) {
				System.out.println("과락된 과목 : 국어");
			}
			if(eng < 40) {
				System.out.println("과락된 과목 : 영어");
			}
			if(math < 40) {
				System.out.println("과락된 과목 : 수학");
			}
		} else {
			 if(avg >= 60) {
					System.out.println("축하합니다. 합격입니다.");
					System.out.printf("총점 : %d 점\n", sum);
					System.out.printf("국어 : %d 점\n", kor);
					System.out.printf("영어 : %d 점\n", eng);
					System.out.printf("수학 : %d 점\n", math);
					System.out.printf("평균 : %.1f 점\n", avg);
				} else {
					System.out.println("평균 점수가 미달하였습니다.");
				}
		}
	}

}
