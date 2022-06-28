package conn.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConn {

	public static SqlSession getSqlSession() {
		SqlSession sess = null;
		
		String config = "resources/mybatis-config.xml";  // 경로설정 
														 // 지정하는거니까 어디에 둬도 상관없음
		try {
			InputStream is = Resources.getResourceAsStream(config);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
			sess = ssf.openSession();  // true - 자동커밋 활성화 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sess;
	}
}
