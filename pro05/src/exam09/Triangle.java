package exam09;

public class Triangle {

	public int floor;
	public int height;
	public int a1, a2;
	
	
	public double area(int floor, int height) {
		double area = floor * height % 2.0;
		return area;
	}
	
	public double round(int floor, int a1, int a2) {
		double round = floor + a1 + a2;
		return round;
	}
	
	
}
