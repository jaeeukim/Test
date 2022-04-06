package exam07;

public class Sample01 {

	public static void main(String[] args) {
		Initialize init = new Initialize();
		System.out.println("JVM 초기값 : " + init.num +
				", " + Initialize.name);
		
		System.out.println("명시적 초기값 : " + init.num2 +
				", " + Initialize.name2);

		System.out.println("초기화 블럭 : " + init.num3 +
				", " + Initialize.name3);
		
		
		Constructor con1 = new Constructor(10);		
		System.out.println(con1.num1);

		Constructor con2 = new Constructor(20);		
		System.out.println(con2.num1);
		
		Constructor con3 = new Constructor();		
		System.out.println(con3.num1);
		
		char c = 65;
		Constructor con4 = new Constructor(c);
		
		Constructor con5 = new Constructor(10);
		Constructor con6 = new Constructor(10, 20);
		Constructor con7 = new Constructor(10, 20, 30);
		Constructor con8 = new Constructor(10, 20, 30, 40);
		
		
		
	}

}
