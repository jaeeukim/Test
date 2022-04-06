package exam04;

public class Main {

	public static void main(String[] args) {
//		Premium p = new Premium();
//		System.out.println(p.auto(7000000));
		Normal n1 = new Normal();
		Premium p1 = new Premium();
		
		n1.buy("루이비통", 1000000);
		for(int i = 0; i < 12; i++) {
			p1.buy("루이비통", 5000000);			
		}
		
		
		
		
		
		
	}

}
