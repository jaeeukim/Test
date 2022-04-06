package exam07;

public class Sample02 {

	public static void main(String[] args) {
		Book book1 = new Book();
		Book book2 = new Book("Java Programming");
		Book book3 = new Book("Programming", 123456789);
		Book book4 = new Book("Java", 123456799, "미상");
		Book book5 = new Book("Java", 123456779, "홍길동", 5);
		
		Book.method2();
	}

}
