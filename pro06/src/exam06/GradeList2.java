package exam06;

import java.util.Arrays;

import exam05.Person;

public class GradeList2 {
	//강사님 버전
	private Grade[] gArr = new Grade[0];
	
	public GradeList2() {}
	
	public GradeList2(int size) {
		this.gArr = new Grade[size];
	}
	
	public GradeList2(String ... subjects) {
		// new GradeList2("과목1", "과목2", ...)
		this.gArr = new Grade[subjects.length];
		for(int i = 0; i < subjects.length; i++) {
			this.gArr[i] = new Grade(subjects[i]);
		}
	}
	
	public GradeList2(Grade[] grade) {
		this.gArr = grade;
	}
	
	public int length() {
		return this.gArr.length;
	}
	

	//추가
	public void add(String name) {
		this.gArr = Arrays.copyOf(this.gArr, length() + 1);
		this.gArr[length() - 1] = new Grade(name);
	}
	public void add(String name, double result) {
		this.gArr = Arrays.copyOf(this.gArr, length() + 1);
		this.gArr[length() - 1] = new Grade(name, result);
	}
	public void add(Grade grade) {
		this.gArr = Arrays.copyOf(this.gArr, length() + 1);
		this.gArr[length() - 1] = grade;
	}
	
	//수정
	// 		1. 수정할 자료를 찾는다.
	//		2. 찾은 자료에 대해 수정을 한다.
	public void update(String search, String change) {
		int idx = findIndex(search);
		this.gArr[idx].setName(change);
	}
	
	public void update(String search, double change) {
		int idx = findIndex(search);
		this.gArr[idx].setResult(change);
	}
	
	public void update(String search, String changeName, double changeResult) {
		int idx = findIndex(search);
		this.gArr[idx].setName(changeName);
		this.gArr[idx].setResult(changeResult);
	}
	
	public void update(String search, Grade change) {
		int idx = findIndex(search);
		this.gArr[idx] = change;
	}
	
	public void update(int index, String change) {
		this.gArr[index].setName(change);
	}
	public void update(int index, double change) {
		this.gArr[index].setResult(change);
	}
	public void update(int index, String changeName, double changeResult) {
		this.gArr[index].setName(changeName);
		this.gArr[index].setResult(changeResult);		
	}
	public void update(int index, Grade change) {
		this.gArr[index] = change;
	}
	
	//삭제
	
	//조회
	public Grade get(int index) {
		return this.gArr[index];
	}
	public String getName(int index) {
		return this.gArr[index].getName();
	}
	public double getResult(int index) {
		return this.gArr[index].getResult();
	}
	
	public double getResult(String name) {
		return 0;
	}
	
	public double getAvg() {
		return getTotal() / length();
	}
	
	public double getTotal() {
		double tot = 0;
		for(int i = 0; i < length(); i++) {
			tot += gArr[i].getResult();	
		}
		return tot;
	}
	
	public String[] getUnder() { //100점 만점 기준 40점 미만은 과락
		String[] score = new String[0];
		for(int i = 0; i < length(); i++) {
			if(gArr[i].getResult() < 40) {
				score = Arrays.copyOf(score,score.length);
				score[score.length - 1] = gArr[i].getName();
			}
		}
		return score;
	}
	
	public String[] getUnder(double result) { //과락의 기준을 외부값으로 받아서 찾아내는 함수
		String[] score = new String[0];
		for(int i = 0; i < length(); i++) {
			if(gArr[i].getResult() < result) {
				score = Arrays.copyOf(score,score.length);
				score[score.length - 1] = gArr[i].getName();
			}
		}
		return score;
	}
	
	public String getTop() {
		//최고 득점을 받은 과목을 찾아내는 함수
		Grade top = gArr[0];
		for(int i = 1; i < length(); i++) {
			if(top.getResult() < gArr[i].getResult()) {
				top = gArr[i];
			}
		}
		return top.getName();
	}
	
	public String[] getTop(int count) {
		//count 만큼 찾아내기	
		Grade[] tArr = _sort(true);
		String[] score = new String[0];
		for(int i = 0; i < count; i++) {
			score = Arrays.copyOf(score, score.length + 1);
			score[score.length - 1] = tArr[i].getName();			
		}
		return score;
	}
	
	
	
	public String getBottom() {
		Grade bottom = gArr[0];
		for(int i = 1; i < length(); i++) {
			if(bottom.getResult() > gArr[i].getResult()) {
				bottom = gArr[i];
			}
		}
		return bottom.getName();
	}
	
	public String[] getBottom(int count) {
		Grade[] tArr = _sort(false);
		
		String[] score = new String[0];
		for(int i = 0; i < count; i++) {
			score = Arrays.copyOf(score, score.length + 1);
			score[score.length - 1] = tArr[i].getName();			
		}
		return score;
	}
	
	
	
	public int findIndex(String name) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			Grade data = get(i);
			if(name.equals(data.getName())){
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	private Grade[] _sort(boolean descending) {
		Grade[] tArr = gArr.clone();
		
		if(descending) {
			for(int i = 0; i < tArr.length - 1; i++) {
				for(int j = i + 1; j < tArr.length; j++) {
					if(tArr[i].getResult() < tArr[j].getResult()) {
						Grade temp = tArr[i];
						tArr[i] = tArr[j];
						tArr[j] = temp;
					}
				}
			}
		} else {
			for(int i = 0; i < tArr.length - 1; i++) {
				for(int j = i + 1; j < tArr.length; j++) {
					if(tArr[i].getResult() > tArr[j].getResult()) {
						Grade temp = tArr[i];
						tArr[i] = tArr[j];
						tArr[j] = temp;
					}
				}
			}
		}
		
		return tArr;
		
	}
	
	
	
	
}
