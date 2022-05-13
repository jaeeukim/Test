package semi_pro;

// 변수 정보를 담고 있는 객체
public class DesireVO {
	
	private String prod_name;	// 상품이름
	private String prod_type;	// 상품타입
	private int prod_price;		// 상품가격
	private String reason;		// 필요이유
	private String exp_use;		// 예상사용빈도
	private int des_percentage; // 구매욕구
	
	
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	// String형식으로 들어오면 int로 변경될수 있게 해주는 코드
	public void setProd_price(String prod_price) {
		this.prod_price = Integer.parseInt(prod_price);
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getExp_use() {
		return exp_use;
	}
	public void setExp_use(String exp_use) {
		this.exp_use = exp_use;
	}
	public int getDes_percentage() {
		return des_percentage;
	}
	public void setDes_percentage(int des_percentage) {
		this.des_percentage = des_percentage;
	}
	// String형식으로 들어오면 int로 변경될수 있게 해주는 코드
	public void setDes_percentage(String des_percentage) {
		this.des_percentage = Integer.parseInt(des_percentage);
	}
	

	
	
	
	
	
	
	
	
}
