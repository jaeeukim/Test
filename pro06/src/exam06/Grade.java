package exam06;

public class Grade {
	//과목에 대한 성적 점수 정보를 가지는 객체
	private double result;
	private String name;
	
	public Grade(String name) {
		this.name = name;
		this.result = 0.0;
	}
	
	public Grade(String name, double result) {
		this.name = name;
		this.result = result;
	}
	

	public Grade(double result) {
		this.result = result;
	}
	
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
