package exam01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Person o) {
		if(this.getName().compareTo(o.getName()) > 0) {
			return 1;
		}else if(this.getName().compareTo(o.getName()) < 0) {
			return -1;
		}else {
			if(this.getAge() > o.getAge()) {
				return 1;
			} else if(this.getAge() < o.getAge()) {
				return -1;
			}
		}
		return 0;		
	}
	
	
	
}

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * List 계열 컬렉션 - ArrayList
		 */
		
		// E << 이 리스트에 들어갈 타입을 넣는다(제네릭 타입) 
		// List<E> aList = new ArrayList<E>(); 
		
		//List<Integer> aList = new Vector<Integer>();로 넣고 진행해도 똑같은 값		
		//List<Integer> aList = new LinkedList<Integer>();로 진행해도 똑같음
		List<Integer> aList = new ArrayList<Integer>();
		aList.add(100);
		aList.add(200);
		aList.add(300);
		System.out.println(aList);
		
		aList.add(2, 400);
		System.out.println(aList);
		
		List<Integer> bList = new ArrayList<Integer>();
		bList.add(500); bList.add(600); bList.add(700);
		
		aList.addAll(bList);
		System.out.println(aList);
		
		// set(index, A); index에 있는 값을 A값으로 바꾼다.
		Integer i1 = aList.set(2, 350); //수정되기 전에 값을 반환
		System.out.println(i1 + " / " + aList);
		i1 = aList.set(3, 450);
		System.out.println(i1 + " / " + aList);

		// contains 은 boolean 값을 반환한다.
		boolean result1 = aList.contains(Integer.valueOf(350));
		System.out.println(result1);
		
		// indexOf 몇번째 index에 있는지 반환 (없을 경우 -1 반환)
		int result2 = aList.indexOf(Integer.valueOf(350));
		System.out.println(result2);
		
		System.out.println(aList.get(0));
		System.out.println(aList.get(1));
		System.out.println(aList.get(2));
		
		System.out.println(aList.size());
		
		for(int i = 0; i < aList.size(); i++) {
			System.out.println(aList.get(i));
		}
		
		result1 = aList.isEmpty();
		System.out.println(result1);
		
		// clear 했기때문에 (empty)비어있다.
		bList.clear();
		result1 = bList.isEmpty();
		System.out.println(result1);
		
		// remove(idx) idx에있는 내용 사라짐 (null이나 0으로 사라지는게 아님)
		// aList.remove(0); //return 값은 제네릭 값에 따라 달라짐
		// System.out.println(aList);
		
		i1 = aList.remove(0); // 완전 삭제가 아니기 때문에 반환 가능하다
		System.out.println(i1 + " / " + aList);
		
		System.out.println("<<<<< Iterator 사용 >>>>>>");
		Iterator<Integer> iter = aList.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		// for each문은 iterator라는 형태가 있다면 사용가능하다
		System.out.println("<<<<<< for each 문 >>>>>>");
		for(Integer i3 : aList) {
			System.out.println(i3);
		}
		
		
		// reverse 내림차순으로 정렬
		System.out.println("리버스 전 : " + aList);
		Collections.reverse(aList);
		System.out.println("리버스 후 : " + aList);
		
		// sort 오름차순으로 정렬
		System.out.println("오름차순 정렬 전 : " + aList);
		Collections.sort(aList);
		System.out.println("오름차순 정렬 후 : " + aList);
		
		// 내림차순으로 정렬하기 위해서는 오름차순 후 리버스 하는것이 편하지만 이런 방식도 있음.
		Collections.sort(aList, new Comparator<Integer>(){
			
			@Override
			public int compare(Integer i1, Integer i2) {
				if(i1 > i2) {
					return -1;
				} else if(i1 == i2) {
					return 0;				
				}
				return 1;
			}
		});
		System.out.println("내림차순 정렬 후 : " + aList);
	
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person("홍길동", 23));
		pList.add(new Person("김철수", 25));
		pList.add(new Person("김철수", 21));
		
		Person p1 = pList.get(0);
		Person p2 = pList.get(1);
		Person p3 = pList.get(2);
		
		// p1과 p2 비교했을때 + 가 나오면 p1이 더 뒤에 있는것 (큰거에서 작은거 뺀거)
		System.out.println(p1.getName().compareTo(p2.getName()));
		// -가나오면 앞에있는애가 더 앞에있는것 (작은거에서 큰거 뺀거)
		System.out.println(p2.getName().compareTo(p1.getName()));
		// 같은거 비교하면 0
		System.out.println(p2.getName().compareTo(p3.getName()));
		
		Collections.sort(pList, new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				if(p1.getName().compareTo(p2.getName()) > 0) {
					return 1;
				}else if(p1.getName().compareTo(p2.getName()) < 0) {
					return -1;
				}else {
					if(p1.getAge() > p2.getAge()) {
						return -1;
					} else if(p1.getAge() < p2.getAge()) {
						return 1;
					}
				}
				return 0;					
			}			
		});
	
		for(Person p : pList) {
			System.out.println(p.getName() + " | " + p.getAge());			
		}
		
		// Person클래스에 Comparator를 implements해서 @Override했기때문에
		// 간단하게 사용 가능
		System.out.println("============================");
		Collections.sort(pList);		
		for(Person p : pList) {
			System.out.println(p.getName() + " | " + p.getAge());			
		}
		
		
		
		
		
		
	}
}
