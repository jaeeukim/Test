package exam03.pack2;

import exam03.pack1.AccessMain;

public class AccessTest2 {
public static void main(String[] args) {
		
		// 다른패키지, 다른 클래스에서 AccessMain 클래스의
		// 멤버 변수에 접근
		AccessMain m1 = new AccessMain();
		System.out.println(m1.v1); // public 가능
		System.out.println(m1.v2); // protected 불가
		System.out.println(m1.v3); // (default) 불가
		System.out.println(m1.v4); // private 불가 
		 
	}
}
