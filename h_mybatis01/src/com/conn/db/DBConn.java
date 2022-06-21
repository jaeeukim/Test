package com.conn.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.data.vo.EmpVO;

public class DBConn {

	public static SqlSession getSqlSesseion() {
		SqlSession sess = null;
		
		String config = "resources/mybatis-config.xml";  // 경로설정 
														 // 지정하는거니까 어디에 둬도 상관없음
		try {
			InputStream is = Resources.getResourceAsStream(config);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
			sess = ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sess;
	}
	
	public static void main(String[] args) {
		SqlSession session = DBConn.getSqlSesseion();
		// List<Object> result = session.selectList("testMapper.test2");
		//  String result = session.selectOne("testMapper.test"); //namespace.id		
		// for(int i = 100; i <= 110; i++) {
		// 	EmpVO result = session.selectOne("testMapper.employee", i);
		//	System.out.println(result);
		//	System.out.println(result.getEmployee_id() + ", " + result.getFirst_name());			
		// }
		
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("id1", 100);
		data.put("id2", 110);
		List<EmpVO> result = session.selectList("testMapper.employee", data);
		System.out.println(result);
		
		for(EmpVO d: result) {
			System.out.println(d.getEmployee_id() + ", " + d.getFirst_name());						
		}
		// List<EmpVO> result = session.selectList("testMapper.employee");
		// System.out.println(result.size()); //size가 얼마인지 보면 됨
		// System.out.println(result.get(0).getEmployee_id() + ", " + result.get(0).getFirst_name());
	}
}
