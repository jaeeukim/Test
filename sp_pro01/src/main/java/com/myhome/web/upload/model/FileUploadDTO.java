package com.myhome.web.upload.model;

public class FileUploadDTO {
	private int id;
	private int bId;
	private String fileName;
	private String location;
	private String url;
	private long fileSize;
	
	public FileUploadDTO() {}
	
	public FileUploadDTO(int id, String location, String url) {
		this.id = id;
		this.location = location;
		this.url = url;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	//public FileUpload() {}
	
	
}
