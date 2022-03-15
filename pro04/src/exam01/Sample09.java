package exam01;

import java.util.Arrays;

public class Sample09 {

	public static void main(String[] args) {
		/*
		 * 동적배열
		 * 		- 기존의 배열은 크기가 한 번 정해지면 크기를 늘리거나 줄일 수 없다.
		 * 		- 배열의 크기를 늘리거나 줄일 수 있도록 기존 배열을 조작.
		 * 		- 최초 배열의 크기보다 크거나 작은 배열을 새로 만들고 새로 생성된 배열에 값을 복사한다.
		 */
		
		int[] arr1 = new int[3];
		arr1[0] = 10; arr1[1] = 20; arr1[2] = 30;
		
		int[] temp = new int[arr1.length + 1];
		for(int i = 0; i< arr1.length; i++) { //깊은 복사
			temp[i] = arr1[i];
		}
		
		//또 다른 깊은 복사 
		//int[] temp = Arrays.copyOf(arr1, arr1.length + 1);
		
		//또 다른 깊은 복사 
		//arraycopy를 사용하면 원하는 위치부터 시작하게 설정할수 있다.
		//int[] temp = new int[arr1.length + 1];
		//System.arraycopy(arr1, 0, temp, 0, arr1.length);
		
		arr1 = temp; //얕은복사
		arr1[3] = 40;
		
		System.out.println(Arrays.toString(arr1));
		
		
		
	}

}
