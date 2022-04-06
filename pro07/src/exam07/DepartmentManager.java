package exam07;

//과장
public class DepartmentManager extends AssistantManager implements TeamManager{

	private boolean teamManager;  //팀장직을 가지고있는지 true, false로 구분

	public DepartmentManager(String name, int age) {
		super(name, age);
		this.setSalary(4000);
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
			case 4: case 8: case 12:
				System.out.printf("보너스 : %,.0f 원\n", getSalary() * 0.25 * 10000);
		}
	}

	@Override
	public void teamPayBonus() {
		// 팀장 직책을 수행하는 경우 연봉의 10% 보너스로 받을 수 있습니다.
		if(isTeamManager()) {
			System.out.println("팀장직 수행 보너스 : " + getSalary() / 0.1 / 12 * 10000 + " 원");
		}
	}
	
	public boolean isTeamManager() {
		return teamManager;
	}

	public void setTeamManager(boolean teamManager) {
		this.teamManager = teamManager;
	}

	
	
	
}
