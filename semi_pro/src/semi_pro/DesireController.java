package semi_pro;

import java.sql.PreparedStatement;
import java.util.Scanner;

// 중간 제어용 객체
public class DesireController {
	Scanner sc = new Scanner(System.in);
	private DesireDAO dao = new DesireDAO();
	private DeConn dc;
	
	// 예상사용빈도가 상/중/하 로 이뤄졌는지
	// 구매욕구가 0 ~ 100 사이의 값인지 확인할 용도
	public boolean check(DesireVO data) {
		if(!(data.getExp_use().equals("상") || data.getExp_use().equals("중")
			|| data.getExp_use().equals("하"))) {
			System.out.println("!!상/중/하 로 입력하세요!!\n\n");
			return false;
		} 
		
		if(data.getDes_percentage() < 0 || data.getDes_percentage() > 100) {
			System.out.println("!!0과 100 사이의 값을 입력하세요!!\n\n");
			return false;
		}
		
		DesireVO name = dao.get(data.getProd_name());
		
		if(name != null) {
			return false;
		}
		
		boolean result = dao.add_prod(data);
		return result;
		
	}


	
	
	
	
	
	public boolean update_prod(DesireVO data) {
		return dao.update_prod(data);
	}
	
	public boolean delete_prod(DesireVO data) {
		return dao.delete_prod(data);
	}


	
	
	
	
	
}
