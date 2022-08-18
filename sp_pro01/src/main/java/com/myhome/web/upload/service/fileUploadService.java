package com.myhome.web.upload.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.upload.model.FileUploadDAO;
import com.myhome.web.upload.model.FileUploadDTO;

@Service
public class fileUploadService {
	
	@Autowired
	private FileUploadDAO dao;
	
	private boolean _upload(MultipartFile file, FileUploadDTO data) throws Exception {
		File directory = new File(data.getLocation());
		if(!directory.exists()) {
			directory.mkdirs(); // 해당 폴더가 없다면 생성하게 함
		}
		
		if(file.getSize() / (1024 * 1024 * 5) > 1) {
			return false;
		}
		
		data.setFileName(file.getOriginalFilename());
		data.setFileSize(file.getSize());
		
		boolean result = dao.insertData(data);
		if(result) {				// separatorChar는 '/'나 '|' 구별해서 들어가게 함
			file.transferTo(new File(data.getLocation() + File.separatorChar+ file.getOriginalFilename()));
		}
		return result;
	}
	
	public int upload(MultipartFile file, FileUploadDTO data) throws Exception {
		boolean res = this._upload(file, data);
		return res ? 1: 0;
	}
	
	public int upload(MultipartFile[] files, FileUploadDTO data) throws Exception {
		int count = 0;
		for(MultipartFile file: files) {
			boolean res = this._upload(file, data);
			count = res ? count + 1 : count; 					
		}
		return count;
	}
	
	
}
