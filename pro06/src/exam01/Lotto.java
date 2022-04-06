package exam01;

import java.util.Arrays;
import java.util.Random;

public class Lotto {
	// 일단 중복체크 안하기
	private int rangeMin = 1;
	private int rangeMax = 45;
	private int count = 6;
	private int[] numbers;
	private Random rand = new Random();
	
	// 기본생성자
	public Lotto() {
		this.numbers = new int[count];
	}
	
	// 생성 범위와 수를 초기에 설정하여 객체 생성
	public Lotto(int min, int max, int count) {
		this.rangeMin = min;
		this.rangeMax = max;
		this.count = count;
		this.numbers = new int[count]; //this()안됨. 첫줄로가야하나 count보다 위로갈수없음
	}
	
	// 모든 멤버 변수에 대한 get/set, 단, numbers에 대한 setter제외

	public int getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}

	public int getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getNumbers() {
		return numbers;
	}
	
	// 이 메서드를 실행시켜 로또 번호가 생성되게 만든다.
	
	public void generate(int ... nums) {
		int cnt = nums.length;
		
		for(int i = 0; i < cnt; i++) {
			numbers[i] = nums[i];
		}
		
		for(int i = cnt; i < count;) {
			int num = this.rand.nextInt(rangeMax) + rangeMin;
			if(!_isDuplicate(num)) {
				numbers[i] = num;
				i++;
			}
		}
	}
	
	private boolean _isDuplicate(int number) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == number) {
				return true;
			}
		}
		return false;
	}
	
	
	
//	public void generate() {
//		for(int i = 0; i < count; i++) {
//			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
//		}
//	}
//	public void generate(int n1) {
//		numbers[0] = n1;
//		for(int i = 1; i < count; i++) {
//			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
//		}
//	}
//	public void generate(int n1, int n2) {
//		numbers[0] = n1; numbers[1] = n2;
//		for(int i = 2; i < count; i++) {
//			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
//		}
//	}
//	public void generate(int n1, int n2, int n3) {
//		numbers[0] = n1; numbers[1] = n2; 
//		numbers[2] = n3;
//		for(int i = 3; i < count; i++) {
//			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
//		}
//	}
//	public void generate(int n1, int n2, int n3, int n4) {
//		numbers[0] = n1; numbers[1] = n2; 
//		numbers[2] = n3; numbers[3] = n4;
//		for(int i = 4; i < count; i++) {
//			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
//		}
//	}
//	public void generate(int n1, int n2, int n3, int n4, int n5) {
//		numbers[0] = n1; numbers[1] = n2; 
//		numbers[2] = n3; numbers[3] = n4;
//		numbers[4] = n5;
//		for(int i = 5; i < count; i++) {
//			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
//		}
//	}
	
}
