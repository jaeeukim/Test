package exam07;

//사원
public class Staff extends Employee {
	private boolean teamManager;
	private boolean headManager;
	
	public Staff(String name, int age) {
		super(name, age);
		this.setSalary(2800);
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
			case 6: case 12:
				System.out.printf("지급액 : %,.0f\n", getSalary() * 0.25 * 10000);
		}
	}
	
	
}
