package exam01;

import java.util.Scanner;

public class Sample05 {

	public static void main(String[] args) {
		/*
		 * 카이사르 암호 만들기.
		 * 		- 사용자 입력으로 영단어 입력 받는다.
		 * 		- 입력받은 영단어는 문자 배열로 생선한다.
		 * 		- 생성된 문자 배열에 대해 암호화된 배열을 저장하기 위한 배열을 새로 만든다.
		 * 		- 문자 쉬프트는 3으로 하고 만약 'z' 문자에 대한 쉬프트가 필요한 경우 'a'로 이동한다.
		 */
		
		Scanner sc =new Scanner(System.in);
		
		System.out.print("영단어 입력 : ");
		String word = sc.nextLine();
		
		char[] arr1 = new char[word.length()];
		for(int i = 0; i < word.length(); i++) {
			arr1[i] = word.charAt(i);
		}
		
		char[] arr2 = new char[word.length()];
		for(int i = 0; i < word.length(); i++) {
			if(arr1[i] + 3 > 'z'){
				arr2[i] = (char)(arr1[i] + 3 -26);
			} else {
				arr2[i] = (char)(arr1[i] + 3);	
			}
		}
		
		System.out.print("카이스르 암호화 : ");
		for(int i = 0; i< word.length(); i++) {
			System.out.print(arr2[i]);
		}
		/*
		 * String res1 = "";
		 * String res2 = "";
		 * for(int i = 0; i < word.length(); i++){
		 * 		res1 += arr1[i];
		 * 		res2 += arr2[i];
		 * }
		 * System.out.println("암호화 전 : " + res1);
		 * System.out.println("암호화 후 : " + res2);
		 * 
		 */
	}

}
