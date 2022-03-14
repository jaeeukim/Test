package exam01;

import java.util.Arrays;

public class Sample07 {

	public static void main(String[] args) {
		
		/*
		 * 반복문을 사용한 깊은 복사 작업을 자바에서 제공하는 기능으로 바꾸어서 작업
		 */
		
		int[] arr1 = new int[] {1, 2, 3, 4, 5};
		
		// 반복문을 사용한 깊은 복사
		int[] arr2 = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
		}
		
		// 자바의 기본 기능을 사용한 깊은 복사
		int[] arr3 = new int[arr1.length];
		System.arraycopy(arr1, 0, arr3, 0, arr1.length);
		
		arr1[0] = 10;
		System.out.printf("arr1[0] -> %d / arr3[0] -> %d\n", arr1[0], arr3[0]);
		
		// Arrays 객체를 사용한 깊은 복사
		int[] arr4 = Arrays.copyOf(arr1, arr1.length);
		arr1[0] = 20;
		System.out.printf("arr1[0] -> %d / arr4[0] -> %d\n", arr1[0], arr4[0]);
		
		// .clone()을 사용한 깊은 복사
		
		int[] arr5 = arr1.clone();
		arr1[0] = 30;
		System.out.printf("arr1[0] -> %d / arr5[0] -> %d", arr1[0], arr5[0]);
		
		
		
	}

}
