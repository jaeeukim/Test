package exam01;

public class Sample01 {

	public static void main(String[] args) {
		
		int num = 12;
		
		if(num > 10) {
			System.out.println("num 변수에 저장된 값은 10 보다 크다.");
		}
		
	
		if(num %2 == 0) {
			System.out.println("num 변수에 저장된 값은 짝수이다.");
		}
		else {
			System.out.println("num 변수에 저장된 값은 홀수이다.");
		}
		
		num = 18;
		
		if(num <= 19) {
			if(num >= 13 && num <= 15) {
				System.out.println("중학생 입니다.");
			} else if(num >= 16 && num <= 18) {
				System.out.println("고등학생 입니다.");
			} else if(num == 19){
				System.out.println("고등학교 졸업생 입니다.");
			}else {
				System.out.println("유아/초등학생 입니다.");
			}
		} else if(num <= 29) {
			System.out.println("20대 입니다.");
		} else if(num <= 39) {
			System.out.println("30대 입니다.");
		} else if(num <= 49) {
			System.out.println("40대 입니다.");
		} else if(num <= 59) {
			System.out.println("50대 입니다.");
		} else {
			System.out.println("국민연금을 수령할 나이대 입니다.");
		}
		
		
		
		
		
		
		
		
		
		
	}

}
