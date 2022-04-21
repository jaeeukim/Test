package exam02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * Set 컬렉션
		 */
		
		Set<Integer> aSet = new HashSet<Integer>();
		
		aSet.add(100); // boolean값으로 지정해서 보면 true
		aSet.add(200); // true
		aSet.add(200); // false가 나옴 (중복된 값은 추가 되지 않음)
		System.out.println(aSet);
		
		Set<Integer> bSet = new HashSet<Integer>();
		bSet.add(300); bSet.add(400);
		
		boolean result1 = aSet.addAll(bSet);
		System.out.println(result1 + " " + aSet);
		
		//List와 같이 사용
		List<Integer> cList = new ArrayList<Integer>();
		cList.add(500); cList.add(600); cList.add(100);
		
		// List에 중복값이 있다면 제외하고 출력함 (result1는 true나옴)
		result1 = aSet.addAll(cList);
		System.out.println(result1 + " " + aSet);
		
		result1 = aSet.contains(Integer.valueOf(100));
		System.out.println(result1);
		result1 = aSet.contains(Integer.valueOf(150));
		System.out.println(result1);
		
		result1 = aSet.isEmpty();
		System.out.println(result1);
		
		bSet.clear();
		result1 = bSet.isEmpty();
		System.out.println(result1);
		
		// 길이
		int len = aSet.size();
		System.out.println(len + " " + aSet);
		
		// 제거
		result1 = aSet.remove(Integer.valueOf(100));
		System.out.println(result1 + " " + aSet);
		
		result1 = aSet.remove(Integer.valueOf(150));
		System.out.println(result1 + " " + aSet);
		
		// 저장된 객체 한번씩 가져오는 반복자 리턴
		Iterator<Integer> iter = aSet.iterator();
		while(iter.hasNext()) {
			Integer i1 = iter.next();
			System.out.print(i1 + "\t");
		}
		System.out.println();
		
		// for문 형식
		for(Integer i1 : aSet) {
			System.out.print(i1 + "\t");
		}
		System.out.println();
		
		// Set 을 List 로 변경
		List<Integer> aList = new ArrayList<Integer>(aSet);
		System.out.println(aList);
		
		ListIterator<Integer> iter1 = aList.listIterator(aList.size() - 1);
		
		// previous 역순으로 가져옴 (역순으로 가져오기 위해서 ↑위에서 size - 1부터 시작하게 설정)
		while(iter1.hasPrevious()) {
			Integer data = iter1.previous();
			System.out.println("Previous : " + data);
		}
		
		
		
		// List 를 Set 으로 변경
		Set<Integer> cSet = new HashSet<Integer>(aList);
		System.out.println(cSet);
		
		// 배열로 변경
		Integer[] iArr = aList.toArray(new Integer[aList.size()]);
		System.out.println(Arrays.toString(iArr));
		iArr = cSet.toArray(new Integer[cSet.size()]);
		
	}
}
