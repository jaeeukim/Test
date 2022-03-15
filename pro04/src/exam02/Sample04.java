package exam02;

import java.util.Arrays;
import java.util.Random;

public class Sample04 {

	public static void main(String[] args) {
		/*
		 * 1 ~ 16 까지의 정수를 4X4 배열에 순차적으로 저장될 수 있게 하시오.
		 * (저장된 정수를 표 형식으로 출력)
		 */
		
		int[][] arr1 = new int[4][4];
		
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1.length; j++) {
				arr1[i][j] = 4 * i + j + 1;
			}
		}
		
		for(int i = 0; i < arr1.length; i++) {
			System.out.println(Arrays.toString(arr1[i]));
		}
		
		
		System.out.println("===========================");
		/*
		 * 1 ~ 49 사이의 임의의 정수를 5x6 배열에 순차적으로 저장하고 표 형식으로 출력하시오.
		 */
		
		Random rand = new Random();
		int[][] arr2 = new int[5][6];
		
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[i].length; j++) {
				int num = rand.nextInt(49) + 1;
				arr2[i][j] = num;
			}
		}
		for(int i = 0; i < arr2.length; i++) {
			System.out.println(Arrays.toString(arr2[i]));
		}
		
		/*
		 * 1 ~ 49 사이의 임의의 정수를 5xN 배열에 순차적으로 저장하고 표 형식으로 출력
		 * (N의 경우 3 ~ 6 사이의 임의의 크기로 만들어 저장되도록 한다. <- 가변 길이 2차 배열)
		 */
		
		
		
	}

}
