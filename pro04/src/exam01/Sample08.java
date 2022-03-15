package exam01;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sample08 {

	public static void main(String[] args) {
		/*
		 * 배열의 크기가 5인 정수 배열을 생성하고 해당 배열에 1 ~ 19 까지의 정수 값을
		 * 임의로 생성하여 초기화하는 작업을 수행하도록 한다.
		 * 단, 배열에 초기화된 값은 중복되어서는 안된다.
		 */
		
		Random rand = new Random();
		int[] arr1 = new int[5];
		boolean duplicate;
		
		for(int i = 0; i < arr1.length;) {	
			duplicate = false;
			int num = rand.nextInt(19) + 1;
			for(int j = 0; j < i; j++) {
				if(arr1[j] == num) {
					duplicate = true;
					break;
				}
			}
			if(!duplicate) {
				arr1[i] = num;	
				i++;	
			}
		}
		
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + "\t");
		}
		
		System.out.println("\n====================\n");
		//	->	System.out.println(Arrays.toString(arr1));
	
		/*
		 * 사용자 입력을 임의의 정수 값을 입력 받고 입력받은 모든 정수의 합과 평균을 구하는
		 * 코드를 작성한다.
		 * 사용자로부터 임의의 정수 값을 입력받기전에 총 몇개의 정수값을 입력받을지 확인 후 작업한다.
		 * 
		 * 예제 
		 * 		총 입력 횟수 : 2
		 * 		1번째 정수 값 입력 : 3
		 * 		2번째 정수 값 입력 : 4
		 * 
		 *		총 합 : 7	
		 *		평 균 : 3.5 
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("총 입력 횟수 : ");
		int count = sc.nextInt();
		int[] arr2 = new int[count]; 
		int sum = 0;
		
		for(int i = 0; i < arr2.length; i++) {
			System.out.printf("%d번째 정수 값 입력 : ", i + 1);
			arr2[i] = sc.nextInt();
			sum += arr2[i];
		}
		
		System.out.printf("\n총 합 : %d", sum);
		System.out.printf("\n평 균 : %.1f", (double)(sum / count));
		
		
		
	}

}
