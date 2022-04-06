package exam06;

import java.util.Objects;

public class Customer {
	private String name;
	private int age;
	private char gender;
	private int price;
	
	// getter, setter
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	public void buy(String productName, int price) {
		System.out.printf("%s 상품을 %,d원에 구입하였습니다.\n", productName, price);
	}
	
	
	public void refund() {
		System.out.println("영수증 없이 환불 조치를 하였습니다.");
	}
	
	public void refund(String receipt) {
		System.out.println("영수증을 확인하였습니다.");
		System.out.printf("%s 상품이 환불 되었습니다.\n", receipt);
	}
	
	public Customer renewal() {
		System.out.println("고객 정보를 갱신합니다.");
		return this;
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
		Customer other = (Customer) obj;
		return age == other.age && gender == other.gender && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
	
}
