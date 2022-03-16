package exam03;

import java.util.Arrays;
import java.util.Random;

public class Sample02 {

	public static void main(String[] args) {
		// 1. 배열의 크기가 10인 정수 배열 2개 성성 후 각 배열에 10 ~ 99 사이의 난수값으로 초기화 / 출력
		
		Random rand = new Random();
		
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = rand.nextInt(89) + 10;
			arr2[i] = rand.nextInt(89) + 10;
		}
		
		System.out.println("1. 결과 확인");
		System.out.println("\t" + Arrays.toString(arr1));
		System.out.println("\t" + Arrays.toString(arr2));
		
		
		// 2. 1번 문제에서 생성한 첫번째 배열과 두번째 배열의 합을 구하여 새로운 배열을 만들고 출력
		int[] arr3 = new int[arr1.length];
		
		for(int i = 0; i < arr3.length; i++) {
			arr3[i] = arr1[i] + arr2[i];
		}
		
		System.out.println("2. 결과 확인");
		System.out.println("\t" + Arrays.toString(arr3));
		
		
		// 3. 2번 문제까지 진행하여 만들어진 3개의 배열을 이용하여 짝수값만 저장하는 배열과 홀수값만
		// 		저장하는 배열을 만들고 짝수배열과 홀수배열을 출력한다.
	
//		int cnt1 = 0, cnt2 = 0;
//		
//		for(int i = 0; i < arr1.length; i++) {
//			if(arr1[i] %2 == 0) {
//				cnt1++;
//			}else {
//				cnt2++;
//			}
//			if(arr2[i] %2 == 0) {
//				cnt1++;
//			}else {
//				cnt2++;
//			}
//			if(arr3[i] %2 == 0) {
//				cnt1++;
//			}else {
//				cnt2++;
//			}
//		}
//
//		System.out.println("짝수 : " + cnt1);
//		System.out.println("홀수 : " + cnt2);
//		
//		int[] arr4 = new int[cnt1];
//		int[] arr5 = new int[cnt2];
//		int idx1 = 0, idx2 = 0;
//		
//		for(int i = 0; i < arr1.length; i++) {
//			if(arr1[i] %2 == 0) {
//				arr4[idx1] = arr1[i];
//				idx1++;
//			}else {
//				arr5[idx2] = arr1[i];
//				idx2++;
//			}
//			
//			if(arr2[i] %2 == 0) {
//				arr4[idx1] = arr2[i];
//				idx1++;
//			}else {
//				arr5[idx2] = arr2[i];
//				idx2++;
//			}
//			
//			if(arr3[i] %2 == 0) {
//				arr4[idx1] = arr3[i];
//				idx1++;
//			}else {
//				arr5[idx2] = arr3[i];
//				idx2++;
//			}
//		}
//
//		System.out.println("3. 결과 확인");
//		System.out.println("\t" + Arrays.toString(arr4));
//		System.out.println("\t" + Arrays.toString(arr5));
//		
		/*
		 * 강사님의 동적배열 활용 방식
		 */
		
		int[] arr4 = new int[0];
		int[] arr5 = new int[0];
		int idx1 = 0, idx2 = 0;
		
		for(int i = 0; i < arr1.length; i++) {
			int[] temp;
			if(arr1[i] % 2 == 0) {
				temp =new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr1[i];
				idx1++;
			}else {
				temp =new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
				
				arr5[idx2] = arr1[i];
				idx2++;
			}
			if(arr2[i] % 2 == 0) {
				temp =new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr2[i];
				idx1++;
			}else {
				temp =new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
				
				arr5[idx2] = arr2[i];
				idx2++;
			}
			if(arr3[i] % 2 == 0) {
				temp =new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr3[i];
				idx1++;
			}else {
				temp =new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
				
				arr5[idx2] = arr3[i];
				idx2++;
			}
		}
		System.out.println("3. 결과 확인");
		System.out.println("\t" + Arrays.toString(arr4));
		System.out.println("\t" + Arrays.toString(arr5));
		
		
		
		/*
		 * 내가 한 방식
		 */
//		int[] temp1 = new int[30];
//		int[] temp2 = new int[30];
//		int j = 0;
//		
//		for(int i = 0; i < arr1.length; i++) {	
//			if(arr1[i] % 2 == 0) {
//					temp1[j] = arr1[i];
//					j++;
//				}else {
//					continue;
//				}
//		}
//		for(int i = 0; i < arr2.length; i++) {	
//			if(arr2[i] % 2 == 0) {
//					temp1[j] = arr2[i];
//					j++;
//				}else {
//					continue;
//				}
//		}
//		for(int i = 0; i < arr3.length; i++) {	
//			if(arr3[i] % 2 == 0) {
//					temp1[j] = arr3[i];
//					j++;
//				}else {
//					continue;
//				}
//		}
//		
//		int even[] = Arrays.copyOf(temp1, j);
//		
//		
//		
//		j = 0;
//		for(int i = 0; i < arr1.length; i++) {	
//			if(arr1[i] % 2 != 0) {
//					temp2[j] = arr1[i];
//					j++;
//				}else {
//					continue;
//				}
//		}
//		for(int i = 0; i < arr2.length; i++) {	
//			if(arr2[i] % 2 != 0) {
//					temp2[j] = arr2[i];
//					j++;
//				}else {
//					continue;
//				}
//		}
//		for(int i = 0; i < arr3.length; i++) {	
//			if(arr3[i] % 2 != 0) {
//					temp2[j] = arr3[i];
//					j++;
//				}else {
//					continue;
//				}
//		}
//		int[] odd = Arrays.copyOf(temp2, j);
		
//		System.out.println(Arrays.toString(even));
//		System.out.println(Arrays.toString(odd));
		
		
		
		// 4. 짝수/홀수 배열에 중복된 값이 있는 경우 하나의 값만 남겨 새로운 배열을 만들고 출력한다.
		
		
		int[] arr6 = new int[1];
		int[] arr7 = new int[1];
		int idx3 = 0, idx4 = 0;
		
		//짝수
		arr6[0] = arr4[0]; 	//첫 번째 값은 중복 없으니 바로 저장 -> temp에 하나 저장 -> 중복검사 -> 중복없으면 배열크기 늘려서 저장
		for(int i = 1; i < arr4.length; i++) {
			int temp = arr4[i];
			boolean dup = false;
			for(int j = 0; j < arr6.length; j++) {
				if(arr6[j] == temp) {
					dup = true;
					break;
				}
			}
			if(!dup) {
				arr6 = Arrays.copyOf(arr6, arr6.length + 1);
				arr6[arr6.length - 1] = temp;
			}
		}
		
		//홀수 
		arr7[0] = arr5[0];
		for(int i = 1; i < arr5.length; i++) {
			int temp = arr5[i];
			boolean dup = false;
			for(int j = 0; j < arr7.length; j++) {
				if(arr7[j] == temp) {
					dup = true;
					break;
				}
			}
			if(!dup) {
				arr7 = Arrays.copyOf(arr7, arr7.length + 1);
				arr7[arr7.length - 1] = temp;
			}
		}
		
		System.out.println("4. 결과 확인");
		System.out.println("\t" + Arrays.toString(arr6));
		System.out.println("\t" + Arrays.toString(arr7));
		
		
//		// 짝수배열 중복 제거 
		
//		int[] arr6 = new int[0];
//		int[] arr7 = new int[0];
		
//		for(int i = 0; i < arr4.length; i++) {
//			for(int j = 0; j < arr4.length; j++) {
//				if(i != j) {
//					if(arr4[j] != -1) {
//						if(arr4[i] == arr4[j]) {
//							arr4[j] = -1;
//						}
//					}
//					
//				}
//			}
//			int[] temp;
//			if(arr4[i] != -1) {
//				temp = new int[arr6.length + 1];
//				System.arraycopy(arr6, 0, temp, 0, arr6.length);
//				arr6 = temp;
//				
//				arr6[idx3] = arr4[i];
//				idx3++;
//			}
//		}
//		
//		// 홀수 배열 중복 제거 
//		for(int i = 0; i < arr5.length; i++) {
//			for(int j = 0; j < arr5.length; j++) {
//				if(i != j) {
//					if(arr5[j] != -1) {
//						if(arr5[i] == arr5[j]) {
//							arr5[j] = -1;
//						}
//					}
//					
//				}
//			}
//			int[] temp;
//			
//			if(arr5[i] != -1) {
//				temp = new int[arr7.length + 1];
//				System.arraycopy(arr7, 0, temp, 0, arr7.length);
//				arr7 = temp;
//				
//				arr7[idx4] = arr5[i];
//				idx4++;
//			}
//		}
//		
		
		
		/*
		 * 내가 한 방식
		 */
//		for(int i = 0; i < even.length; i++) {
//			for(j = 0; j < i; j++) {
//				if(even[i] == even[j]) {
//					even[j] = 0;
//				}
//			}
//		}
//		for(int i = 0; i < even.length; i++) {
//			for(j = 0; j < i; j++) {
//				if(odd[i] == odd[j]) {
//					odd[j] = 0;
//				}
//			}
//		}
//		
//		
//		System.out.println(Arrays.toString(even));
//		System.out.println(Arrays.toString(odd));
		
		// 5. 짝수 / 홀수 배열에 있는 값을 작은값부터 큰값 순으로 정렬된 배열을 만들고 출력한다.
		
		int[] arr8 = arr6.clone();
		int[] arr9 = arr7.clone();
		
		for(int i = 0; i < arr8.length - 1; i++) {
			for(int j = i + 1; j < arr8.length; j++) {
				if(arr8[i] > arr8[j]) {
					int temp = arr8[i];
					arr8[i] = arr8[j];
					arr8[j] = temp;
				}
			}
		}
		for(int i = 0; i < arr9.length - 1; i++) {
			for(int j = i + 1; j < arr9.length; j++) {
				if(arr9[i] > arr9[j]) {
					int temp = arr9[i];
					arr9[i] = arr9[j];
					arr9[j] = temp;
				}
			}
		}
		
		
		System.out.println("5. 결과 확인");
		System.out.println("\t" + Arrays.toString(arr8));
		System.out.println("\t" + Arrays.toString(arr9));
	
		
		// 6. 짝수/홀수 배열로 나누어져 있는 것을 합쳐서 하나의 배열로 만들고 출력한다.
		
		int[] arr10 = new int[arr8.length + arr9.length];
		System.arraycopy(arr8, 0, arr10, 0, arr8.length);
		System.arraycopy(arr9, 0, arr10, arr8.length, arr9.length);
		
		
		
		System.out.println("6. 결과 확인");
		System.out.println("\t" + Arrays.toString(arr10));
		
		
		
		
		
		
		
	}

}
