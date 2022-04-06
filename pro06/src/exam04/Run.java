package exam04;

public class Run {

	public static void main(String[] args) {
//		Paper p = new Paper();
//		
//		p.paperMinus();
//		p.paperPlus();
//		p.paperPlus();
//		p.addPaper(380);
		
		Page p = new Page(100);
		
		
		for(int i = 0; i < 10; i++) {
			p.nextPage();
			System.out.println(p.getPageNumber() + "번째 페이지");			
		}
		
//		for(int i = 0; i < 13; i++) {
//			p.prevPage();;
//			System.out.println(p.getPageNumber() + "번째 페이지");			
//		}
		
		
		for(int i = 0; i < 10; i++) {
			p.nextPage(5);
			System.out.println(p.getPageNumber() + "번째 페이지");			
		}
		for(int i = 0; i < 10; i++) {
			p.prevPage(3);
			System.out.println(p.getPageNumber() + "번째 페이지");			
		}
		
		
		
	}

}
