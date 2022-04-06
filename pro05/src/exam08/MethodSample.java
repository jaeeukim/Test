package exam08;

import java.util.Random;

public class MethodSample {

	public void method01() {
		System.out.println("반환 타입은 void이고 매개변수가 없는 메서드");
	}
	
	public int method02() {
		System.out.println("반환 타입은 int(기본자료형)이고 매개변수가 없는 메서드");
		return 0;
	}
	
	public int[] method03() {
		int[] res = new int[5];
		System.out.println("반환 타입은 int[](배열)이고 매개변수가 없는 메서드");
		return res;
	}
	
	public String method04() {
		String res = new String();
		System.out.println("반환 타입은 String(클래스/객체)이고 매개변수가 없는 메서드");
		return res;
	}
	
	public void method05(int[] arr) {
		System.out.println("반환 타입은 void이고 매개변수가 배열인 메서드");
		System.out.println(arr);
		for(int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "] -> " + arr[i]);
		}	
	}
	
	
	public void method06(Random r) {
		System.out.println("반환 타입은 void이고 매개변수가 클래스(객체)인 메서드");
		r.nextInt(10);
	}
	
	public void method07(int ... nums) {
		System.out.println("반환 타입은 void이고 매개변수가 가변인자인 메서드");
		System.out.println(nums);
		for(int i = 0; i < nums.length; i++) {
			System.out.println("nums[" + i + "] -> " + nums[i]);
		}
	}
	
}
