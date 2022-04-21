package exam01;

public class Sample01 {
	public static void main(String[] args) {
		MyException m = new MyException();
//		String s = null;
//		s.length();
		
		System.out.println("에러 발생 전.");
		try {
			m.exceptionThrows();			
		} catch(Exception e) {
//			System.out.println("Sample01.main 10번줄 Exception 발생!");
			e.printStackTrace();
		}
//		m.exceptionNonThrows();
		System.out.println("에러 발생 후 처리 완료!");
		
		
		
		
		
		
		
	}
}
