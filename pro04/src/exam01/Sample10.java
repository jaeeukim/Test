package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class Sample10 {

	public static void main(String[] args) {
		/*
		 * 사용자 입력을 임의의 정수 값을 입력 받고 입력받은 모든 정수의 합과 평균을 구하는
		 * 코드를 작성한다. (동적배열 활용)
		 * -1이 들어오면 종료라고 판별한다.
		 * 
		 * 예제 
		 * 		1번째 정수 값 입력 : 3
		 * 		2번째 정수 값 입력 : 4
		 * 		3번째 정수 값 입력 : -1
		 * 
		 *		총 합 : 7	
		 *		평 균 : 3.5 
		 */
		
		Scanner sc = new Scanner(System.in);
//		int[] arr = new int[1000];
//		int tot = 0, cnt = 0;
//		
//		for(int i = 0;;i++) {
//			System.out.printf("%d 번째 정수값 입력 : ", i + 1);
//			int num = sc.nextInt();
//			
//			if(num == -1) {
//				break;
//			}
//			arr[i] = num;
//			tot += num;
//			cnt++;
//		}
//		int temp[] = new int[cnt];
//		System.arraycopy(arr, 0, temp, 0, cnt);
//		arr = temp;
//
//		System.out.println("총 합 : " + tot);
//		System.out.println("평 균 : " + tot/(double)arr.length);
		
		int cnt = 1;
		int[] arr1 = new int[0];
		while(true) {
			System.out.printf("%d 번째 정수 값 : ", cnt);
			
			int num;
			if(sc.hasNextInt()) {
				num = sc.nextInt();	 sc.nextLine(); //개행 관련해서 넘겨버리기 입력하지않으면 g를 입력했을때 continue부분이 두번나옴
			} else {
				if(sc.nextLine().equals("exit")){
					break;
				} else {
					System.out.println("정수 값 또는 exit를 입력하세요.");
					continue;
				}
			}
			
			int[] temp = Arrays.copyOf(arr1, arr1.length + 1);
			arr1 = temp;
			
			arr1[cnt -1] = num;
			cnt += 1;
			
			System.out.println(Arrays.toString(arr1));
		}
		
		int tot = 0;
		double avg;
		
		for(int i = 0; i < arr1.length; i++) {
			tot += arr1[i];
		}
		avg = (double)tot / arr1.length;
		
		System.out.println("총 합 : " + tot);
		System.out.println("평 균 : " + avg);
		
		
		
	}

}
