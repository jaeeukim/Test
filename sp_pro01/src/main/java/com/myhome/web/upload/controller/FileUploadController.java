package com.myhome.web.upload.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/upload")
@MultipartConfig
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	
	@PostMapping(value="/image", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String image(HttpServletRequest request
				, HttpServletResponse response
				, HttpSession session
				, @RequestParam("upload") MultipartFile[] files
				, @RequestParam("type") String type) throws Exception {
		logger.info("image(files={}, type={})", files, type);
		
		JSONObject json = new JSONObject();
		
		PrintWriter out = response.getWriter();
		Part part = request.getPart("image");
		
		String realPath = request.getServletContext().getRealPath("/resources");
		
	
		if(!part.getSubmittedFileName().isEmpty()) {	// 업로드가 된 이미지가 있는지 체크
			part.write(realPath + part.getSubmittedFileName());

			out.println("{");
			out.println("	\"uploaded\": 1,"); // 속성명 : 속성값
			out.println("    \"fileName\": \"" + part.getSubmittedFileName() + "\",");
			out.println("    \"loc\": \"./resources/img/board/" + part.getSubmittedFileName()+ "\""); 
			out.println("}");
		}
		out.flush();
		
		
		for(MultipartFile file:files) {
			System.out.println(realPath);
			System.out.println("isEmpty() : " + file.isEmpty());
			System.out.println("getName() : " + file.getName());
			System.out.println("getOriginFilename() : " + file.getOriginalFilename());
			System.out.println("getSize() : " + file.getSize() / 1000);
			file.transferTo(new File(realPath + "/img/board/" + file.getOriginalFilename()));
		}
		
		
		return json.toJSONString();
	}
}
