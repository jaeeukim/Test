package exam05;

import java.util.Random;

public class Sample01 {

	public static void main(String[] args) {
		
		Circle c1 = new Circle();
		Circle c2 = new Circle();
	
		c1.radius = 1.5;
		c2.radius = 3.5;
	
		c1.diameter = 2 * c1.radius;
		c2.diameter = 2 * c2.radius;
		
		c1.area = Circle.PI * c1.radius * c1.radius;
		c2.area = Circle.PI * c2.radius * c2.radius;
		
		System.out.printf("c1의 radius : %.1f\t diameter : %.1f\t area : %.1f\n",c1.radius, c1.diameter, c1.area);
		System.out.printf("c2의 radius : %.1f\t diameter : %.1f\t area : %.1f\n",c2.radius, c2.diameter, c2.area);
		
		
		
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle();
		
		r1.width = c1.diameter;
		r2.width = c2.diameter;
		
		Random rand = new Random();
		
		r1.height = rand.nextInt(7) + 3;
		r2.height = rand.nextInt(7) + 3;
		
		r1.area = r1.width * r1.height;
		r2.area = r2.width * r2.height;
		
		System.out.printf("r1의 width : %.1f\t height : %.1f\t area : %.1f\n",r1.width, r1.height, r1.area);
		System.out.printf("r2의 width : %.1f\t height : %.1f\t area : %.1f\n",r2.width, r2.height, r2.area);
		
		
		
	}
}
