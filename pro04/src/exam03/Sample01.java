package exam03;

import java.util.Arrays;
import java.util.Scanner;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * 사용자가 입력한 영문자열에서 입력된 문자의 수를 구하는 코드
		 * 1. 영문자를 제외한 다른 문자는 무시한다.
		 * 2. 대/소문자를 구분하지않고 문자수를 구한다.
		 */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("영문자 입력 : ");
		String str = sc.nextLine();
	
		char[] arr = new char[26];
		// 알파벳 수를 세기 위한 배열
		int[] counting = new int[26];
		
		for(int i = 0; i < str.length(); i++) {
			int idx = 0;
			char c = str.charAt(i);
			if(c >= 65 && c <= 90) {
				idx = c - 65;
				counting[idx] += 1;
			}else if(c >= 97 && c <= 122) {
				idx = c - 97;
				counting[idx] += 1;
			}
			
		}
		
		int count = 0;
		System.out.println("입력한 영문자 분포");
		
		
//		System.out.println(Arrays.toString(counting));
		
		for(int i = 0; i < arr.length; i++) {
			if(counting[i] == 0) {
				continue;
			}
			System.out.printf("%s(%d), ", arr[i], counting[i]);
			count++;
			if(count == 5) {
				System.out.println("");
				count = 0;
			}
		}
		
		
		
		
		
	}

}
