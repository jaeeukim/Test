package exam07;

public class Book {

	public int bNum; //코드번호
	public String bName; //책이름
	public String writer; //작가
	public int rentDays; //대여기간
	public int rentPrice; //대여금액
	public String publisher; //출판사
	public String category; //분류
	public String libraryName; //도서관이름
	
	
	public Book() {}
	public Book(String bName) {
		this.bName = bName;		
	}
	
	public Book(String bName, int bNum) {
		this(bName);
		this.bNum = bNum;
	}
	public Book(String bName, int bNum, String writer){
		this(bName, bNum);
		this.writer = writer;
	}
	
	public Book(String bName, int bNum, String writer, int rentDays) {
		this(bName, bNum, writer);
		this.rentDays = rentDays;
	}
	
	public static void method2() {
		System.out.println("메서드 실행!");
	}
	
	
	
	
	
	
}
