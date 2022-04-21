package pro12;

import java.util.Random;
import java.util.Scanner;

public class GuessNum {

	private Random rand = new Random();
	private int guess;
	private int guessLimit;
	
	public GuessNum() {
		this.guess = rand.nextInt(100) + 1;
		this.guessLimit = 10;
	}
	
	public GuessNum(int rangeMax, int limit) {
		this.guess = rand.nextInt(rangeMax) + 1;
		this.guessLimit = limit;
	}
	
	public GuessNum(int rangeMin, int rangeMax, int limit) {
		this.guess = rand.nextInt(rangeMax - rangeMin) + rangeMin;
		this.guessLimit = limit;
	}
	
	public Result guessing(int number) {
		Result res = new Fail();
		this.guessLimit--;
		if(remainCount()) {
			if(number > this.guess) {
				res = new DOWN();
			} else if(number < this.guess) {
				res = new UP();
			} else if(number == this.guess) {
				res = new Correct();
			}
		} else if(this.guessLimit == 0) {
			if(number == this.guess) {
				res = new Correct();
			}
		}
		return res;
	}
	
	public boolean remainCount() {
		return this.guessLimit > 0 ? true : false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 내 풀이
//	Random rand = new Random();
//	Scanner sc = new Scanner(System.in);
//	
//	private int cnt;
//	private int num = rand.nextInt(100) + 1;
//	
//	public int getCnt() {
//		System.out.println("횟수를 몇번으로 제한 할까요?");
//		System.out.print(">>> ");
//		cnt = sc.nextInt();
//		return cnt;
//	}
//	public void setCnt(int cnt) {
//		this.cnt = cnt;
//	}
//	
//	public void start() {
//		
//		while(true) {
//			System.out.print("숫자를 입력하시오 : ");
//			int pNum = sc.nextInt();
//			if(pNum > num) {
//				// DOWN
//				Down();
//				cnt--;
//			}else if(pNum < num) {
//				//UP
//				Up();
//				cnt--;
//			}else {
//				//correct
//				Correct();
//				cnt = 0;
//				break;
//			}
//			if(cnt == 0) {
//				//Fail
//				Fail();
//				break;
//			}
//		}	
//	}
//	
//	
//	
//	
//	public void Up() {
//		System.out.println("UP!");
//	}
//	public void Down() {
//		System.out.println("DOWN!");
//	}
//	public void Correct() {
//		System.out.println("정답!");
//		
//	}
//	public void Fail() {
//		System.out.println("실패했습니다!");
//		System.out.println("정답 : " + num);
//	}
	
	
}
