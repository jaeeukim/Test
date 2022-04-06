package exam10.model.vo;

public class Circle {
	private final double PI = 3.14;
	private int radius = 1;
	
	public Circle() {}
	
	public void incrementRadius() {
		radius += 1;
		getAreaOfCircle();
		getSizeOfCircle();
	}
	
	public void getAreaOfCircle() {
		System.out.println("원의넓이 : " + radius * radius * PI);
	}
	
	public void getSizeOfCircle() {
		System.out.println("원의둘레 : " + 2 * radius * PI);
	}
	
	
	
	
}
