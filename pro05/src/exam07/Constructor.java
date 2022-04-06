package exam07;

public class Constructor {
	public int num1;
	public int num2;
	public int num3;
	public int num4;

	public Constructor() {
	}
	
	//매개변수가 있는 생성자
	public Constructor(int num1) {
		//전달 받은 매개변수 값을 이용하여 초기화
		this.num1 = num1;
		System.out.println("int");
	}
	
	public Constructor(int num1, int num2) {
		this(num1);
		this.num2 = num2;
	}
	public Constructor(int num1, int num2, int num3) {
		this(num1, num2);
		this.num3 = num3;
	}
	public Constructor(int num1, int num2, int num3, int num4) {
		this(num1, num2, num3);
		this.num4 = num4;
	}
	
	
	
	
	
	
	
	public Constructor(byte b) {
		System.out.println("byte");
	}
	public Constructor(char c) {
		System.out.println("char");
	}
	public Constructor(short s) {
		System.out.println("short");
	}
	public Constructor(long l) {
		System.out.println("long");
	}
	
	
	
	
	
	
	
	
	
}
