package exam01;

public class Sample03 {

	public static void main(String[] args) {
		String[] arr1 = {"1", "2", "3", "4"};
		
		// 문자열 배열 사용 방법 1
		for(int i = 0; i < 4; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.println("");
		System.out.println(arr1[0].length());
		
		// 문자열 배열 사용 방법 2
		// 문자열 배열을 초기화하지 않으면 기본값 null로 채워져 있다.
		String[] arr2 = new String[5];
		for(int i = 0; i < 5; i++) {
			System.out.print(arr2[i] + "\t");
		}
		System.out.println("");
		// null 데이터가 있는 경우 문자열 메서드 사용에 오류가 발생한다. -> java.lang.NullPointerException
		// System.out.println(arr2[0].length());
		
		// 정수 배열을 초기화하지 않으면 기본값 0으로 채워져 있다.
		int arr3[] = new int[5];
		for(int i = 0; i < 5; i++) {
			System.out.print(arr3[i] + "\t");
		}
		System.out.println("");
		
		
		// 실수형 배열을 초기화하지 않으면 기본값 0.0으로 채워져 있다.
		double arr4[] = new double[5];
		for(int i = 0; i < 5; i++) {
			System.out.print(arr4[i] + "\t");
		}
		System.out.println("");
		
		
		
	}

}
