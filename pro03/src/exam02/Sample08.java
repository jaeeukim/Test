package exam02;

public class Sample08 {

	public static void main(String[] args) {
		
		// while문
		int i = 0;
		while(i < 5) {
			System.out.println("i -> " + i);
			i++;
		}
		
		// do ... while문
		i = 0;
		do {
			System.out.println("i -> " + i);
			i++;
		}while(i < 5);
		
		i = 0;
		while(i < 10) {
			i += 1;
			if(i % 3 == 0) {
				System.out.println("i -> " + i);
			} else {
				continue;
			}
			System.out.println("continue가 되면 이 출력은 동작하지 않음.");

		}
		
		
		
		
		
		
		
		
	}

}
