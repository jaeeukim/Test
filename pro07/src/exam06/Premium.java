package exam06;

import java.util.Objects;

public class Premium extends Customer {
	private double sale;
	private int priceTotal;
	
	@Override
	public void buy(String productName, int price) {
		priceTotal += price;
		double p = _calcDiscount(price);
		System.out.printf("%s 상품을 %.2f 할인율 적용하여 %,.1f 원에 구입하였습니다.\n", productName, sale, p);
		
	}
	
	@Override
	public Customer renewal() { // 다형성 활용 
		//반환하는 타입이 normal과 premium으로 나오게되므로 customer로 리턴타입을 지정한다.
		Customer c = this; //this는 자식이고 Customer는 부모니까 업캐스팅
		if(priceTotal < 5000000) {
			c = new Normal();
			c.setName(getName());
			c.setAge(getAge());
			c.setGender(getGender());
			System.out.println("누적사용액이 기준에 미달하여 일반 고객으로 강등되었습니다.");
		}
		return c;
	}
	
	
	private double _calcDiscount(int price) {
		if(priceTotal >= 10000000) {
			sale = 0.1;
			return price * 0.9;
		}else if(priceTotal >= 3000000) {
			sale = 0.05;
			return price * 0.95;
		}else if(priceTotal >= 1000000) {
			sale = 0.02;
			return price * 0.98;
		}else {
			return price;
		}
	}
	
	
	

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public int getPriceTotalTotal() {
		return priceTotal;
	}

	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}

	
	
	
	
	
}
