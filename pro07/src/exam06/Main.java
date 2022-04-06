package exam06;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/*
		 * 다형성 / 업캐스팅 / 다운캐스팅 활용
		 * 		- 일반 고객 5명을 생성한다.(이름, 나이, 성별 초기화하지 않아도됨)
		 * 		- 일반 고객 5명을 배열에 넣어 랜덤한 고객, 랜덤한 가격으로 물품을 구입하게 만든다.
		 * 		- 물품 구입액이 1,000,000 원을 초과하면 프리미엄 고객으로 전환시켜서 물품 구입에
		 * 		  한일율을 적용할 수 있도록 만든다.
		 * 		- 고객이 물품을 구입하는 작업을 반복문을 통해 100번 반복하게 만든다.
		 * 
		 * 일반 고객 -> 프리미엄 고객 전환
		 * 		Normal n1 = new Normal("홍길동");
		 * 		Premium p1 = new Premium();
		 * 		p1.setName(n1.getName); 
		 */
//		Random rand = new Random();
//		Customer[] cArr = new Customer[5];
//
//		for(int i = 0; i < cArr.length; i++) {
//			cArr[i] = new Normal();
//		}
//		
//		int unit = 10000;
//		int coupon = 0;
//		for(int i = 0; i < 100; i++) {
//			int idx = rand.nextInt(cArr.length);
//			int price = (rand.nextInt(190000) + unit) / unit * unit;
//			
//			if(cArr[idx] instanceof Normal) {
//				Normal c = (Normal) cArr[idx];
//				c.buy("XXXXX", price);				
//			
//				if(price > 1000000) {
//					Premium p = new Premium();
//					p.setName(c.getName());
//					p.setAge(c.getAge());
//					p.setGender(c.getGender());
//					p.setPriceTotal(price);
//					cArr[idx] = p;
//					System.out.println("축하합니다 구입액이 1,000,000 원을 초과하여 프리미엄으로 상승하였습니다.");
//					System.out.println("앞으로 물품 구입 누적액에 따른 할인율이 적용됩니다.");	
//				}
//			}else {
//				Premium p = (Premium) cArr[idx];
//				p.buy("XXXXX", price);
//			}
//			
//			if(i % 30 == 0) {
//				Premium p = (Premium) cArr[idx];
//				if(p.getPriceTotalTotal() < 5000000) {
//					p = (Normal) p;
//				}
//			}
//			
//		}
//		
		Customer[] cArr1 = new Customer[3];
		cArr1[0] = new Normal();
		cArr1[1] = new Premium();
		cArr1[2] = new EmployeeCustomer();
		
		for(int i = 0 ; i < cArr1.length; i++) {
//			cArr1[i].buy("xxxxx", 3000000);
//			cArr1[i].refund();
			System.out.println(cArr1[i].getClass());
			cArr1[i] = cArr1[i].renewal();
			System.out.println(cArr1[i].getClass());
		}
		
		
//		Normal n1 = new Normal("홍길동");
//		Premium p1 = new Premium();
//		p1.setName(n1.getName); 
	}

}
