package exam09;

public class Circle {

	private double radius;
	public final static double PI = 3.14;
	public double area;
	public double round;
	
	
	public Circle() {}
	
	public Circle(int radius) {
		this.radius = radius;
		area();
		round();
	}
	
	//setter
	public void setRadius(double radius) {
		this.radius = radius;
		area();
		round();
	}
	//getter
	public double getRadius() {
		return radius;
	}
	//getter
	public double getArea() {
		return area;
	}
	//getter
	public double getRound() {
		return round;
	}
	
	
//	public double area() {
//		return radius * radius * PI;
//	}
	
	private void area() {
		this.area = radius * radius * PI;
	}
	private void round() {
		this.round = 2 * radius * PI;
	}
	
	
	
}
