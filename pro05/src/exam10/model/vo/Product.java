package exam10.model.vo;

public class Product {
	private String pName = "키보드";
	private int price = 250000;
	private String brand = "X오X드";
	
	public Product() {}
	
	public void information() {
		
		System.out.println("상품명 : " + pName + "\n가격 : " + price
				+ "\n브랜드 : " + brand);
	}
	
}
