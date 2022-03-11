package exam02;

public class Sample09 {

	public static void main(String[] args) {
		// 문자열에서 문자만 추출하는 방법
			// .charAt(위치값) :  문자열열에서 위치값에 해당하는 문자 추출
		
		String s = "Hello Java Programming";
		char c = s.charAt(0);
		System.out.println(c);

		// 문자열의 길이를 알아내는 방법
			// 문자열변수명.length() : 문자수를 알려줌
		System.out.println("문자열의 길이 : " + s.length());
		
		
		// 1. 다음 문자열에서 대문자의 수와 소문자의 수를 구하시오.
		// 2. 다음의 문자열에서 단어의 수를 구하시오.
		
		int cnt1 = 0, cnt2 = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				cnt1++;	
			}else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
				cnt2++;
			}	
		}
		System.out.printf("대문자 : %d개\n소문자 : %d개\n", cnt1, cnt2);
		

		int j, cnt3 = 1;
		
		for(j = 0; j < s.length(); j++) {
			if(s.charAt(j) == ' ') {
				cnt3++;
				continue;
			}
		}
		System.out.printf("단어의 수는 %d 개 이다.", cnt3);
		
		/* 강사님 방식
		 * boolean sStart = false; 문자열 시작 -> 영문자 범위면 true
		 * boolean sEnd = false; 문자열 끝 -> 공백이면 true;
		 * int wordCount = 0;
		 * 
		 * for(int i = 0; i < s.length(); i++) {
		 * 		if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
		 * 		sStart = true;	
		 * 		} else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
		 *			 sStart = true;;
		 * 		} else if(s.charAt(i) == ' '){
		 *           sEnd = true;	
		 * 		}
		 * 
		 * 		if(sStart && sEnd){
		 * 			wordCount++;
		 * 			sStart = false; sEnd = false;
		 * 			continue;
		 * 		}
		 * 
		 * 		if(i == s.length() - 1){
		 * 			if(sStart){
		 * 				wordCount++;
		 * 			}
		 * 		}
		 *} 
		 * System.out.prinln("단어 수 : " + wordCount);
		*/		
		
		
		
		
	}

}
