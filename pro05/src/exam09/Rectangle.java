package exam09;

public class Rectangle {

	public int floor;
	public int height;
	public int area;
	public int round;
	
	public Rectangle() {}
	
	public Rectangle(int floor, int height) {
		this.floor = floor;
		this.height = height;
	}
	
	//setter
	public void setFloor(int floor) {
		this.floor = floor;
		area();
		round();
	}
	public void setHeight(int height) {
		this.height = height;
		area();
		round();
	}
	
	
	//getter
	public int getArea() {
		return area;
	}
	
	public int getRound() {
		return round;
	}
	
	
	
	private void area() {
		this.area = floor * height;
	}
	
	private void round() {
		this.round = 2 * (floor + height);
	}
	
	
	
	
//	public int area(int floor, int height) {
//		int area = height * floor;
//		return area;
//	}
//	
//	public int round(int floor, int height) {
//		int round = 2 * (height + floor);
//		return round;
//	}
	
	
}
