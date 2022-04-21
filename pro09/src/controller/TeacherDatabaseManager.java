package controller;

import java.util.Date;

import model.vo.Teacher;

public class TeacherDatabaseManager {
	public Teacher[] datas;
	
	// 테스트용 초기 데이터 나중에는 삭제할거임
	{
		datas = new Teacher[2];
		datas[0] = new Teacher("고명환", "a1234");
		datas[0].setLoginDate(new Date());
		datas[1] = new Teacher("김수덕", "a111");
	}
	
	
	public Teacher login(String name, String password) {
		// Teacher 배열에 존재하는 객체들 중 동일한
		// 이름, 패스워드를 사용하는 객체가 있는지 검사 후
		// 동일한 객체가 있으면 해당하는 Teacher 객체를 반환
		// 동일한 객체가 없으면 null 반환
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName()) && password.equals(datas[i].getPassword())) {
				return datas[i];
			}
		}
		return null;
	}
	
	public Teacher getTeacher(String name) {
		return datas[_findIndex(name)];
	}
	
	
	
	public boolean isExisted(String name) {
		return _isExisted(name);
	}
	
	private boolean _isExisted(String name) {
		return _findIndex(name) != -1 ? true : false;
	}
	
	private int _findIndex(String name) {
		int idx = -1;
		
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName())) {
				idx = i;
				return idx;
			}
		}
		return idx;
	}
	
	
}
