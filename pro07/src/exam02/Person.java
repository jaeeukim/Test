package exam02;

import java.util.Objects;

// 사람
public class Person {
	private String name;
	private char gender;
	private int age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(age, gender, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && gender == other.gender && Objects.equals(name, other.name);
	}

	
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		Person p = (Person)obj;
//		if(this.getName().equals(p.getName())) {
//			if(this.getAge() == p.getAge()) {
//				if(this.getGender() == p.getGender()){
//					return true;												
//				}
//			}
//		}
//		return false;
//	}
//	
	
	
	
	
}
