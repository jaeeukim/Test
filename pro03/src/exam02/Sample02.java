package exam02;

public class Sample02 {

	public static void main(String[] args) {
		
		// 1~99 초기값 1
		for(int i = 1; i < 100; i++) {
			System.out.println(i);
		}
		System.out.println("============");
		
		// 1~99 초기값 0
		for(int i = 0; i < 100; i++) {
			System.out.println(i + 1);
		}
		System.out.println("============");
		
		// 1~99 짝수만
		for(int i = 1; i < 100; i++) {
			if(i % 2 == 0) {
				System.out.println(i);
			}
		}
		
		
	}

}
