package exam01;

public class Child extends Parent {
	
	public Child() {
		super(1);
	}
	
	public void printNumber() {
		setNumber(2);
		System.out.println(getNumber());	
	}
	
	// 오버라이딩이 가능한 메서드인지 확인시키는 용도로 쓰인다.
	@Override
	public void setNumber(int number) {
		System.out.println("자식의 setNumber 메서드 동작 시작");
		super.setNumber(number);
		System.out.println("자식의 setNumber 메서드 동작 끝");
	}
	
	
}
