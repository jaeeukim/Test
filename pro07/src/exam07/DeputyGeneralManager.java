package exam07;

//차장
public class DeputyGeneralManager extends DepartmentManager implements HeadManager{

	private boolean headManager;
	
	public DeputyGeneralManager(String name, int age) {
		super(name, age);
		this.setSalary(5500);
	}

	@Override
	public void bonus(int month) {
		switch(month) {
			case 4: case 8: case 12:
				System.out.printf("보너스 : %,.0f 원\n", getSalary() * 0.25 * 10000);
		}
	}

	@Override
	public void headPayBonus() {
		System.out.println("본부장직 수행 보너스 : " + getSalary() / 0.2 /12 * 10000 + "원");	
	}
	
	public boolean isHeadManager() {
		return headManager;
	}

	public void setHeadManager(boolean headManager) {
		this.headManager = headManager;
	}

}
