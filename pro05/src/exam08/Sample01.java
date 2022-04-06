package exam08;

import java.util.Arrays;
import java.util.Random;

public class Sample01 {

	public static void main(String[] args) {
		MethodSample m = new MethodSample();
		
		m.method01();
		int r1 = m.method02();
		int[] r2 = m.method03();
		String r3 = m.method04();
		
		int[] arg1 = new int[] {1, 2, 3};
		System.out.println(arg1);
		m.method05(arg1);
		System.out.println(Arrays.toString(arg1));
		
		Random rand = new Random();
		m.method06(rand);
		
		m.method07(1, 2, 3, 4, 5, 6, 7, 8);
		m.method07(1, 2, 3, 4, 5, 6, 7);
		m.method07(1, 2, 3, 4, 5);
		
	}

}
