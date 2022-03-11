package exam02;

public class Sample01 {

	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			System.out.printf("%d 번째 반복!!\n", i);
		}
		System.out.println("=====================");
		
		// 초기식 생략
		int i = 0;
		for(; i< 5; i++) {
			System.out.printf("%d 번째 반복!!\n", i);
		}
		System.out.println("=====================");
		
		// 조건식 생략
		for(i = 0;; i++) {
			System.out.printf("%d 번째 반복!!\n", i);
			if(i >= 4) {
				break;
			}
		}
		System.out.println("=====================");
		
		// 증감식 생략
		for(i = 0; i < 5;) {
			System.out.printf("%d 번째 반복!!\n", i);
			i++;
		}
		System.out.println("=====================");
		
		// 전부 생략
		i = 0;
		for(;;) {
			System.out.printf("%d 번째 반복!!\n", i);
			if(i >=4) {
				break;
			}
			i++;
		}
		
	}

}
