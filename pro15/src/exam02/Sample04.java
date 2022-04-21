package exam02;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Person{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, name);
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
		return age == other.age && Objects.equals(name, other.name);
	}
	
	
}


public class Sample04 {

	public static void main(String[] args) {
		Set<Person> pSet = new HashSet<Person>();
		pSet.add(new Person("홍길동", 23));
		pSet.add(new Person("김철수", 25)); 
		pSet.add(new Person("김철수", 22)); 
		
		// 이름이랑 나이가 같아도 객체가 다르기 때문에 둘다 출력됨

		//객체 안에 포함이된 값까지 중복을 허용하지 않고싶다면
		//Person에 Generate hashCode and equals를 해주면 된다.
		
		System.out.println(pSet);
		
		
		
	}

}
