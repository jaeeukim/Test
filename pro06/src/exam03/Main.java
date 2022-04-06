package exam03;

public class Main {

	public static void main(String[] args) {
		Student std = new Student("홍길동", 14, 1);
		
		std.nextYear();
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getGrade());
		System.out.println(std);
	}

}
