package exam05;

import java.util.Arrays;

public class PersonList {
	private Person[] pArr = new Person[0];
	
	public PersonList(Person[] data) {
		this.pArr = data;
	}
	
	public Person get(int index) {
		return this.pArr[index];
	}
	
	public int length() {
		return this.pArr.length;
	}
	
	public void add(String name) {
		this.pArr = Arrays.copyOf(this.pArr, length() + 1);
		this.pArr[length() - 1] = new Person(name);
	}
	
	public void update(String find, String change) {
		int idx = findIndex(find);
		this.pArr[idx].setName(change);
	}
	
	public void update(int index, String name) {
		this.pArr[index].setName(name);
	}
	
	public void remove(String name) {
		int idx = findIndex(name);
		int index = 0;
		Person[] temp = new Person[length() - 1];
		for(int i = 0; i < length(); i++) {
			if(i != idx) {
				temp[index++] = this.pArr[i];
			}
		}
		this.pArr = temp;
	}
	
	public void remove(int index) {
		int idx = 0;
		Person[] temp = new Person[length() -1];
		for(int i = 0; i < length(); i++) {
			if(index != i) {
				temp[idx++] = this.pArr[i];
			}
		}
		this.pArr = temp;
	}
	
	
	// 중복된 부분 메서드로 만들어 묶기
	
	public int findIndex(String name) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			Person data = get(i);
			if(name.equals(data.getName())){
				idx = i;
				break;
			}
		}
		return idx;
	}
	
}
