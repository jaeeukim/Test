package exam01;

public class Sample02 {

	public static void main(String[] args) {
		/*
		 * 배열의 크기가 10인 배열을 선언하고 10 ~ 1까지의 값으로 초기화하기 
		 */
		
		int[] arr1 = new int[10];
		
		for(int i = 0; i < 10; i++) {
			arr1[i] = 10 - i;  //int init = 10; arr1[i] = init--;
			System.out.print(arr1[i] + "\t");
		}
		
		/*
		 * for(int i = 9; i >= 0; i++){
		 * 		arr1[i] = 10 - i; }
		 */
		
		
		System.out.println("");
		
		
		/*
		 * 배열의 크기가 10인 배열을 선언하고 2부터 시작하여 짝수값에 해당하는 값으로 초기화
		 */
		
		int[] arr2 = new int[10];
		
		for(int i = 0; i < 10; i++) {
			arr2[i] = 2 + 2 * i; //init = 2; arr2[i] = init; init += 2;
			System.out.print(arr2[i] + "\t");
		}
		System.out.println("");

	/* 
	 * 배열의 크기가 5인 실수 배열을 선언하고 1부터 0.5씩 증가된 값으로 초기화
	 */
		double[] arr3 = new double[5];
		
		for(int i = 0; i < 5; i++) {
			arr3[i] = 1 + 0.5 * i; //double init2 = 1.0; arr3[i] = init2; init2 += 0.5;
			System.out.print(arr3[i] + "\t");
		}
		System.out.println("");
		
		/*
		 * 배열의 크기가 5인 문자배열을 선언하고 'A' ~ 'E'까지의 문값으로 초기화 
		 */
		
		char[] arr4 = new char[5];
		
		for(int i = 0; i < 5; i++) {
			arr4[i] = (char)('A' + i);
			System.out.print(arr4[i] + "\t");
		}
		
		
		
		
	}

}
