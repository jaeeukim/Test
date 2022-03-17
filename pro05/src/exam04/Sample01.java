package exam04;

public class Sample01 {

	public static void main(String[] args) {
		StaticTest st1 = new StaticTest();
		StaticTest st2 = new StaticTest();
		StaticTest st3 = new StaticTest();

		// static 예약어를 사용한 변수는 클래스 명으로 사용하는것을 권장한다.
		
		// 3가지 모두 같은 (Static)영역을 보고있기때문에 값이 같이 바뀐다.
		st1.share = 20;  // StaticTest.share = 20; 과 동일하다.
		System.out.println(st1.share + "|" + st2.share + "|" + st3.share);
		
		st2.share = 30; // StaticTest.share = 20; 과 동일하다.
		System.out.println(st1.share + "|" + st2.share + "|" + st3.share);
		
		st3.share = 40; // StaticTest.share = 20; 과 동일하다.
		System.out.println(st1.share + "|" + st2.share + "|" + st3.share);
		
		
		
		//
		FinalTest ft1 = new FinalTest();
		
		System.out.println(ft1.CONSTANT);
		//ft1.CONSTANT = 20; 불가능
		
		
		FinalStaticTest fst1 = new FinalStaticTest();
		FinalStaticTest fst2 = new FinalStaticTest();
		
		System.out.println(fst1.CONSTSHARE + "|" + fst2.CONSTSHARE);
		// FinalStaticTest.CONSTSHARE 과 동일함
//		FinalStaticTest.CONSTSHARE = 30; 불가능 
	}

}
