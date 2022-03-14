package exam01;

public class Sample01 {

	public static void main(String[] args) {
		
		/* 배열
		 * 
		 * 배열 선언 방법
		 * 		자료형[] 배열명;
		 * 		자료형 배열명[];
		 * 
		 * 배열에 값을 할당하기 위한 방법
		 * 		배열명 = new 자료형[배열크기]; //배열의 크기를 먼저 지정. 
		 * 		배열명[위치값] = 초기값;  // 배열의 크기가 지정된 후에 원하는 위치(index)에 초기값 할당 
		 * 
		 * 선언과 동이세 할당 및 초기화 하기
		 * 		자료형[] 배열명 = new 자료형[] {초기값1, 초기값2, ...};
		 * */		
		
		int[] arr1;
		arr1 = new int[5];
		arr1[0] = 1;	arr1[1] = 2;	arr1[2] =3;
		arr1[3] = 4;	arr1[4] = 5;

		int[] arr2 = new int[] {1, 2, 3, 4, 5};
		
		
		System.out.println(arr1[0] + ", " + arr1[1] + ", " + arr1[2]);
		System.out.println(arr2[0] + ", " + arr2[1] + ", " + arr2[2]);
		
		/*
		 *	반복문을 사용하여 초기화하기 
		 */
		
		for(int i = 0; i < 5; i++) {
			arr1[i] = i + 1;
		}
		
		for(int i = 0; i < 5; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.println("");
		
		
		
		
		
		
	}

}
