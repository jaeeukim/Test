package exam01;

public class Sample01 {

	public static void main(String[] args) {
		int i1 = 1000;
		char c1 = 65;
		byte b1 = 10;
		
		i1 = b1 + c1;
		
		System.out.println(i1);
		
		float f1 = 4.0f;
		int i2 = 12;
		i2 = (int)f1;
//		i2 = (int)((float)10 + f1);
		// == 10 + (int)f1;
		
		System.out.println(i2);
	}

}
