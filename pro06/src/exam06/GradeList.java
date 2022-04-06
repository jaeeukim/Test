package exam06;

import java.util.Arrays;

public class GradeList {
	//Grade객체를 배열로 만들어 다루기 위한 객체
	// 추가, 수정, 삭제, 조회 가능
	
	private Grade[] gArr = new Grade[0];
	
	public GradeList(Grade[] data) {
		this.gArr = data;
	}
	
	public Grade get(int index) {
		return this.gArr[index];
	}
	public int length() {
		return this.gArr.length;
	}
	
	public void add(double result) {
		this.gArr = Arrays.copyOf(this.gArr, length() +1);
		this.gArr[length() -1] = new Grade(result);
	}
	
	public void update(double result, double change) {
		int idx = findIndex(result);
		this.gArr[idx].setResult(change);
	}
	
	public void update(int index, double change) {
		this.gArr[index].setResult(change);
	}
	
	
	public void remove(double result) {
		int idx = findIndex(result);
		int index = 0;
		Grade[] temp = new Grade[length() -1];
		for(int i = 0; i < length(); i++) {
			if(i != idx) {
				temp[index++] = this.gArr[i];
			}
		}
		this.gArr = temp;
	}
	
	public void remove(int index) {
		int idx = 0;
		Grade[] temp = new Grade[length()- 1];
		for(int i = 0; i < length(); i++) {
			if(index != i) {
				temp[idx++] = this.gArr[i];				
			}
		}
		this.gArr = temp;		
	}

	
	public int findIndex(double result) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			Grade data = get(i);
			if(result == data.getResult()) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	
	
	
}
