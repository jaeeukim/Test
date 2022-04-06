package exam09;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 *  Circle 클래스, Rectangle 클래스 ,Triangle 클래스를 만든다.
		 *  
		 *  1. 만들어진 클래스를 이용하여 각 도형의 넓이를 구하기 위한 메서드를 만든다.
		 *  	(메서드명은 area로 한다.)
		 *  2. 위의 만들어진 클래스를 이용하여 각 도형의 둘레를 구하기위한 메서드를 만든다.
		 *  	(메서드명은 round로 한다.)
		 */
		
		Circle c1 = new Circle(3);
		
		System.out.println("반지름 3인 원의 넓이 : " + c1.getArea());
		System.out.println("반지름 3인 원의 둘레 : " + c1.getRound());
		
		
		Circle c2 = new Circle();
		c2.setRadius(5);	
		System.out.println("반지름 5인 원의 넓이 : " + c2.getArea());
		System.out.println("반지름 5인 원의 둘레 : " + c2.getRound());
		System.out.println("반지름 : " + c2.getRadius());

		c2.setRadius(7);	
		System.out.println("반지름 7인 원의 넓이 : " + c2.getArea());
		System.out.println("반지름 7인 원의 둘레 : " + c2.getRound());
		System.out.println("반지름 : " + c2.getRadius());
		
		
		Rectangle r1 = new Rectangle(3, 5);
		
		System.out.println("밑변 3, 높이 5 사각형의 넓이 : " + r1.getArea());
		System.out.println("밑변 3, 높이 5 사각형의 둘레 : " + r1.getRound());
		
		
	}

}
