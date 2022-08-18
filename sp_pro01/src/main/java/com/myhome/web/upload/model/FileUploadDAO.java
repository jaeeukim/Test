package com.myhome.web.upload.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {

	@Autowired
	private SqlSession session;
	
	public boolean insertData(FileUploadDTO data) {
		int res = session.insert("fileUploadMapper.insertData", data);
		return res == 1 ? true : false;
	}

}
