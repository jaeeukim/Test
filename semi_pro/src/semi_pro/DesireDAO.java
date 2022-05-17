package semi_pro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 데이터베이스에 엑세스하기 위한 객체로 활용
public class DesireDAO {
	private DeConn dc;
	private DesireVO data;

	
	public DesireDAO() {
		try {
			dc = new DeConn("localhost", "1521", "XEPDB1", "for_semi", "for_semi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//pstat
	// 물건 추가
	public boolean add_prod(DesireVO data) {
	String query = "INSERT INTO DESIRE_T VALUES(?, ?, ?, ?, ?, ?)";
	try {
		PreparedStatement pstat = dc.getPstat(query);
		pstat.setString(1,  data.getProd_name());
		pstat.setString(2,  data.getProd_type());
		pstat.setInt(3,     data.getProd_price());
		pstat.setString(4,  data.getReason());
		pstat.setString(5,  data.getExp_use());
		pstat.setInt(6,     data.getDes_percentage());
		
		int rs = dc.sendInsertQuery();
		if(rs == 1) {
			dc.commit();
			return true;
		}
		dc.rollback();
		return false;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}	
	
	// 중복된 이름이 있는지 반환
	public DesireVO get(String prod_name) {
		String query = "SELECT * FROM desire_t WHERE prod_name = ?";
		
		try {
			PreparedStatement pstat = dc.getPstat(query);
			pstat.setString(1, prod_name);
			
			ResultSet rs = dc.sendSelectQuery();

			if(rs.next()) {
				DesireVO data = new DesireVO();
				
				data.setProd_name(rs.getString("prod_name"));
				data.setProd_type(rs.getString("prod_type"));
				data.setProd_price(rs.getInt("prod_price"));
				data.setReason(rs.getString("reason"));
				data.setExp_use(rs.getString("exp_use"));
				data.setDes_percentage(rs.getInt("des_percentage"));
				return data;
			} 
			rs.close();
		}   catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 해당 물건 정보 수정
	public boolean update_prod(DesireVO data) {
		String query = "UPDATE desire_t SET prod_type = ?, prod_price = ?"
				+ ", reason = ?, exp_use = ?, des_percentage = ?"
				+ "WHERE prod_name = ?"; 

		try {
			PreparedStatement pstat;
			pstat = dc.getPstat(query);
		
			pstat.setString(1, data.getProd_type());
			pstat.setInt(2, data.getProd_price());
			pstat.setString(3, data.getReason());
			pstat.setString(4, data.getExp_use());
			pstat.setInt(5, data.getDes_percentage());
			pstat.setString(6, data.getProd_name());
		
			int rs = dc.sendUpdateQuery();
			if(rs == 1) {
				dc.commit();
				return true;
			}
			dc.rollback();;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	// 물건 정보 삭제
	public boolean delete_prod(DesireVO data) {
		String query = "DELETE FROM desire_t WHERE prod_name = ?";
		
		try {
			PreparedStatement pstat = dc.getPstat(query);
			pstat.setString(1, data.getProd_name());
			
			int rs = dc.sendDeleteQuery();
			if(rs == 1) {
				dc.commit();
				return true;
			}
			dc.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}



	
	// 해당하는 물건이 있는지 찾기
	public boolean find(String prod_name) {
		String query = "SELECT * FROM desire_t WHERE prod_name = ?";
		
		try {
			PreparedStatement pstat = dc.getPstat(query);
			pstat.setString(1, prod_name);
			
			ResultSet rs = dc.sendSelectQuery();

			if(rs.next()) {
				return true;
			} 
			rs.close();
		}   catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
