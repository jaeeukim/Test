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
		
		// 문자열을 문자 배열로 변환
		char[] arr1 = new char[word.length()]; //length() 메서드
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = word.charAt(i);
		}
		
		// .toCharArray 메소드를 활용하여 문자열을 문자 배열로 변환
		arr1 = word.toCharArray();
		
		char[] arr2 = new char[arr1.length]; //length 배열 길이의 속성
		for(int i = 0; i < arr2.length; i++) {
			if(arr1[i] + 3 > 'z'){
				arr2[i] = (char)(arr1[i] + 3 -26);
			} else {
				arr2[i] = (char)(arr1[i] + 3);	
			}
		}
		
		System.out.print("카이스르 암호화 : ");
		
		//내가 한 풀이
		for(int i = 0; i< arr2.length; i++) {
			System.out.print(arr2[i]);
		}
		
		
		
		//강사님 풀이
		 String res1 = "";
		 String res2 = ""; 
		 
		 //문자 배열을 문자열로 만들어서 출력하기 위해 반복문 사용
		 /* for(int i = 0; i < word.length(); i++){ 
		 * 		res1 += arr1[i];
		 * 		res2 += arr2[i];
		 * }

		 */
		 
		 
		 //강사님 권장 방식
 		 //new 연산으로 문자열 생성할때 문자 배열을 넣는다.
		 res1 = new String(arr1);
		 res2 = new String(arr2);
		 
		 //String.valueOf(문자배열) 메서드를 사용한다.
		 res1 = String.valueOf(arr1);
		 res2 = String.valueOf(arr2);
		 
		 System.out.println("\n암호화 전 : " + res1);
		 System.out.println("암호화 후 : " + res2);
		  
		
		
	}

}
