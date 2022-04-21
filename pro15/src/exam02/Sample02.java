package exam02;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Sample02 {
	public static void main(String[] args) {
		/*
		 * 로또 번호 생성
		 * 		- 1 ~ 45 까지의 랜덤 번호를 생성하여 리스트에 담는다.
		 * 		- 중복된 값이 없어야한다.
		 * 		- 총 6개의 정수값이 Set에 담길 수 있게 한다.
		 */
		Random rand = new Random();
		Set<Integer> lotto = new HashSet<Integer>();
		for(int i = 0; i < 6;) {
			int r = rand.nextInt(45) + 1;
			if(lotto.add(r)) {
				i++;
			}
		}
		System.out.println(lotto);
		
		
		
//		while(true) {
//			int num = rand.nextInt(45) + 1;
//			if(!lotto.contains(Integer.valueOf(num))) { //Integer 객체로 확인
//				lotto.add(num);				
//			}
//			if(lotto.size() == 6) {
//				break;
//			}
//		}
//		
		
		
		
	}
}
