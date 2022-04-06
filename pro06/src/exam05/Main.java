package exam05;

public class Main {

	public static void main(String[] args) {
		Person p1 = new Person("홍길동");
		System.out.println(p1.getName());
		
		
		Person p2 = new Person("박지수");
		System.out.println(p2.getName());
		
		Person p3 = new Person("김정우");
		System.out.println(p3.getName());
		
		// 배열을 이용한 객체 사용법 -> 객체배열
		Person[] pArr = new Person[4];
		pArr[0] = p1;
		pArr[1] = p2;
		pArr[2] = p3;
		pArr[3] = new Person("최가은");
		
		for(int i = 0; i < pArr.length; i++) {
			System.out.println("pArr[" + i + "] ->" + pArr[i].getName());
		}
			
		
		PersonList pList = new PersonList(pArr);
				
		pList.add("이장수");
		
		pList.update("홍길동", "홍길영");
		pList.update(1, "박재은");
		
		pList.remove("김정우");
		pList.remove(2);
		
		for(int i = 0; i < pList.length(); i++) {
			Person data = pList.get(i);
			System.out.println(data.getName());			
		}
	
		
		
		
		
	}

}
