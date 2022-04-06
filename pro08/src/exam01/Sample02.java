package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class Sample02 {
	
	Scanner sc = new Scanner(System.in);
	
	public void ex01() {
		/*
		 * 사용자 입력으로 정수값 입력을 받을 때 한번의 입력으로 1개 이상의 정수값 받을 수 있도록한다.
		 * (nextLine()사용)
		 * 
		 * 예)
		 *	 	정수값 입력 : 10 20 30 40 50 
		 * 
		 * 		다음의 값을 입력하였습니다.
		 *	 	10, 20, 30, 40, 50
		 */

		
		
		System.out.print("정수 값 입력 : ");
		String[] sArr = sc.nextLine().split(" ");
		
		int[] iArr = new int[sArr.length];
		for(int i = 0; i < sArr.length; i++) {
			iArr[i] = Integer.parseInt(sArr[i]);
		}
		System.out.println(Arrays.toString(iArr));
		
		
		
		System.out.println("다음의 값을 입력하였습니다.");
		System.out.println(String.join(", ", sArr));	
	}
	
	public void ex02() {
		/*
		 * 사용자 입력으로 전화번호를 입력 받을 때 xxx-xxxx-xxxx 형식으로
		 * 입력하지 않은 겨우 다시 입력하도록 한다.
		 */
		
		String input = "";
		while(!input.matches("\\d{3}-\\d{4}-\\d{4}")) {
			System.out.print("xxx-xxxx-xxxx 형식으로 번호를 입력하시오 : ");
			input = sc.nextLine();			
		}
		System.out.println("올바르게 입력하였습니다.");
		
	}
	
	
	
	public void ex03() {
		/*
		 * 사용자 입력으로 전화번호를 입력받고 출력할때 전화번호 마지막4자리 숫자는
		 * 마스킹처리하여 출력한다(마스킹 문자 -> *)
		 */
		
	
		
		System.out.print("번호를 입력하시오 : ");
		String input = sc.nextLine();
		
		//1번 String masking = input.replace(input.substring(input.length() - 4, input, length()), "****");
		//2번 String masking = input.substring(0, input.length()-4)+ "****";
//		
		//3번 
//		String[] sArr = input.split("-");
//		sArr[2] = "****";
//		String masking = String.join("-", sArr);
//		System.out.println(masking);
		
		
		
		// 내가 한 풀이
		if(input.matches("\\d{3}-\\d{4}-\\d{4}")) {
			input = String.format("%s-%s-%s",input.substring(0,3),input.substring(4,8),"****");
			System.out.println(input);
		}else if(input.matches("\\d{11}")) {
			input = String.format("%s-%s-%s", input.substring(0, 3), input.substring(3,7),"****");
			System.out.println(input);
		}
	
	}
	
	public void ex04() {
	/*
	 * 사용자 입력으로 이메일 주소 형식을 입력 받을 때 xxxx@gmail.com 또는
	 * xxxx@naver.com, xxxx@nate.com의 주소 형식만 받을 수 있게 한다.
	 * (xxxx는 4글자 제한아님)
	 */
	
		String[] emailDomain = new String[] {
				"gmail.com", "naver.com", "nate.com"
		};
		
		while(true) {
			System.out.print("이메일 주소 입력 : ");
			String input = sc.nextLine();
		
			//@가 포함된 이메일 주소 형식인가
			String[] email = input.split("@");
			if(email.length == 2) {
				boolean isValiad = false;
				for(int i = 0; i< emailDomain.length; i++) {
					if(email[1].equals(emailDomain[i])) {
						isValiad = true;
						break;
					}
				}
				if(isValiad) {
					System.out.println("이메일 주소를 확인하였습니다.");
					break;
				}else {
					System.out.println("허용된 이메일 주소 도메인이 아닙니다.");
				}
					
			}else {
				System.out.println("이메일 주소 형식이 아닙니다. 다시 입력하세요.");
			}
				
		}
		
	
	}
	
	
	
		public static void main(String[] args) {
			Sample02 sample = new Sample02();
			sample.ex01();
		}
	
	
	
	
}
