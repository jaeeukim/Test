package exam01;

public class Sample04 {

	public static void main(String[] args) {
		/*
		 * 변수에 저장된 값을 복사 -> 큰 문제 없이 사용
		 */
		int n1 = 10;
		int n2 = n1; //n1에 저장된값을 n2로 복사
		
		System.out.printf("n1 -> %d, n2 -> %d\n", n1, n2);

		n1 += 1;
		n2 += 2;
		System.out.printf("n1 -> %d, n2 -> %d\n", n1, n2);
		
		
		/*
		 * 배열 복사-> 얕은 복사
		 */
		
		int[] arr1 = new int[] {1, 2, 3, 4, 5};
		int[] arr2 = arr1;
		System.out.printf("arr1[0] -> %d, arr2[0] -> %d\n", arr1[0], arr2[0]);
		
		arr1[0] += 1;
		arr2[0] += 2;
		System.out.printf("arr1[0] -> %d, arr2[0] -> %d\n", arr1[0], arr2[0]);
		
		/*
		 * 배열 복사 -> 깊은 복사
		 */
		int[] arr3 = new int[] {1, 2, 3, 4, 5};
		int[] arr4 = new int[5];
		
		for(int i = 0; i < 5; i++) {
			arr4[i] = arr3[i];
		}
//		arr4[0] = arr3[0];
//		arr4[1] = arr3[1];
//		arr4[2] = arr3[2];
//		arr4[3] = arr3[3];
//		arr4[4] = arr3[4];
		
		System.out.printf("arr3[0] -> %d, arr4[0] -> %d\n", arr3[0], arr4[0]);
		
		arr3[0] += 1;
		arr4[0] += 2;
		System.out.printf("arr3[0] -> %d, arr4[0] -> %d\n", arr3[0], arr4[0]);
		
		
	}

}
