package exam06;

public class EmployeeCustomer extends Customer {
	
	@Override
	public void buy(String productName, int price) {
		price =  (int)(price * 0.9);
		super.buy(productName, price);
		System.out.println("직원할인가를 적용합니다");
	}
	
	
}
