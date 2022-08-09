package com.myhome.web.board.model;

import java.util.*;

import com.myhome.web.emp.model.EmpDTO;



public class BoardDTO {

	private int id;
	private String title;
	private String content;
	private int empId;
	private Date createDate;
	private int viewCnt;
	private int like;
    private EmpDTO empObj;

	public BoardDTO() { }
	public BoardDTO(int id) {
		this.id = id;
	}

    public EmpDTO getEmpObj() {
		return empObj;
	}


	public void setEmpObj(EmpDTO empObj) {
		this.empObj = empObj;
	}


	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}


	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}


	public int getLike() {
		return like;
	}


	public void setLike(int like) {
		this.like = like;
	}

}