package exam03;

import java.util.Random;

public class Student {
	private String name;
	private int age;
	private int grade;
	private int classRoom;
	private int classNum;
	
	public Student(String name) {
		this(name, 14, 1);
	}
	
	public Student(String name, int age) {
		this(name, age, 1);
	}
	
	
	
	public Student(String name, int age, int grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	public int getGrade() {
		return this.grade;
	}
	
	
	
	public void nextYear() {
		this.age++;
		
		if(grade == 3) {
			this._graduate();
		}else {
			this.grade++;
			this.classRoom
			= this._assignClassRoom();
		}
	}
	
	private int _assignClassRoom() {		
		Random rand = new Random();
		return rand.nextInt(9) + 1;			
	}
	
	
	private void _graduate() {
		this.grade = -1;
		this.classRoom = -1;
		this.classNum = -1;
	}
	
	
	public String toString() {
//		return this.name + "(" + this.age + "): " + this.grade + " 학년" + this.classRoom + " 반";
		return String.format("%s(%d): %d 학년 %d 반", this.name, this.age, this.grade, this.classRoom);
		
		
	}
	
	
	
	
	
	
}
