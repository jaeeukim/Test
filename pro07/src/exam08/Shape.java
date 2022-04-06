package exam08;

public abstract class Shape {
	private double width;
	private double height;
	
	// 추상 메서드
	public abstract double getArea();
	
	// 추상 메서드
	public abstract double getRound();
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
}
