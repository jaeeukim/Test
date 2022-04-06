package exam06;

public class Employee extends Customer{
	
	
	
	@Override
	public void buy(String name, int price) {
		System.out.println("직원할인가를 적용합니다");
		price =  (int)(price * 0.9);
		super.buy(name, price);
	}
	
	
	
	
	
	
}
