package exam02;

public class Sample07 {

	public static void main(String[] args) {
	// 중첩 반복문
		
		for(int i = 1; i <= 3; i++) {
			System.out.println("i 가 1번 반복할때 마다");
			for(int j = 1; j <= 3; j++) {
				System.out.println("j의 반복은 3번씩 총 9번");
			}
		}
		
		// 구구단
		for(int i = 1; i < 10; i++) {
			for(int j = 1; j < 10; j++) {
				System.out.printf("%d X %d = %d\t",i ,j , i * j);
			}
			System.out.println("");
		}
		
		
		
	}

}
