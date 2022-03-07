package exam4;

public class Smaple01 {

	public static void main(String[] args) {
		boolean b1 = true;
		boolean b2 = !b1;
		System.out.println("결과 : " + b2);
		
		int i1 = 1;
		int i2 = ++i1;
		int i3 = --i1;
		System.out.println("결과1 : " + i2);
		System.out.println("결과2 : " + i3);
		
		int i4 = 1;
		int i5 = ++i4;
		
		int i6 = 1;
		int i7 = i6++;
		
		System.out.println("전위 : " + i5);
		System.out.println("후위 : " + i7);
		
		System.out.println("i4 = " + i4 + " i6 = " + i6);
	
		
		
		
		
	}

}
