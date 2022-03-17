package exam03.pack2;

import exam03.pack1.AccessParent;

public class AccessChild  extends AccessParent{
	public void method() {
		
		// 다른클래스, 다른 패키지 하지만 후손.
		System.out.println(p1); // public 가능
		System.out.println(p2); // protected 가능
		System.out.println(p3); // (default) 불가
		System.out.println(p4);	// private 불가 
	}
}
