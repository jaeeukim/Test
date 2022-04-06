package exam07;

import java.util.Random;
//직원
public abstract class Employee {
	//과장이상은 팀장(보너스 연봉10%), 차장 이상은 본부장(보너스 연봉20%)
	// 1달에 1번 급여
	// 보너스는 연봉의 25% -> 사원/대리는 6개월에 한번, 과장/차장 4개월에 한번, 부장 1년에 한번
	// 대리 이상은 법인카드 발급가능, 연봉의 1.5%까지
	// 1번 반복마다 1달의 사용 
	// 법인카드의 사용은 1~9사이중 4의배수에 해당하면 대리, 과장, 차장, 부장 중 한명이사용
	//과장 팀장 -> 기본 4000 + 팀장수행(4000* 0.1) + 4개월에 한번 4000*0.25*3)
	private String name;
	private int age;
	private char gender;
	private int salary = 2500;
	
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void payMonth() {
		System.out.printf("지급액 : %,.0f\n", (double)getSalary() / 12 * 10000);
	}
	
	public abstract void bonus(int month);
//		System.out.printf("지급액 : %,.0f\n", getSalary() * 0.25 * 10000);
	

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


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}
//	Random rand = new Random();
//
//	public void getBonus(String position) {
//		
//	}
//	
//	public void companyCard(String position) {
//			if(!position.equals("사원")) {
//				cardLimit = income * 0.015; 
//			}
//		}
//		
//	
//	public void useCard() {
//		for(int i = 0; i < 12; i++) {
//			if((rand.nextInt(9) + 1) % 4 == 0) {
//				
//			}
//		}
//	}
//	public String[] getPosition() {
//		return position;
//	}
//
//	public void setPosition(String[] position) {
//		this.position = position;
//	}
//
//	public int getBonus() {
//		return bonus;
//	}
//
//	public void setBonus(int bonus) {
//		this.bonus = bonus;
//	}
//
//	public int getCardLimit() {
//		return cardLimit;
//	}
//
//	public void setCardLimit(int cardLimit) {
//		this.cardLimit = cardLimit;
//	}



//	public void basic() {
//		System.out.println("xxx 물산의 직급은 다음과 같습니다.\n");
//		System.out.println("	직위		연봉");
//		System.out.println("===================================");
//		System.out.println("	사원		2800");
//		System.out.println("	대리		3000");
//		System.out.println("	과장		4000");
//		System.out.println("	차장		5500");
//		System.out.println("	부장		8000");
//	}
}
