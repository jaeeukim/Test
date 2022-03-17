package exam03.pack1;

public class AccessTest1 {
	public static void main(String[] args) {
		// 다른 클래스, 같은 패키지에서 AccessMain 클래스의
		// 멤버 변수에 접근
		AccessMain m1 = new AccessMain();
		System.out.println(m1.v1); //public 가능
		System.out.println(m1.v2); // protected 가능
		System.out.println(m1.v3); // (default) 가능
		System.out.println(m1.v4); // private 불가 
		
	}
}
