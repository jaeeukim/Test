package exam04;

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

	public void setPriceTotalTotal(int priceTotalTotal) {
		this.priceTotal = priceTotal;
	}

	
	
	
	
	
}
