package exam03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * Map 계열 컬렉션
		 */
		//앞은 키에 대한 제네릭타입, 뒤는 값에 대한 제네릭타입
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("a", 100); aMap.put("b", 100); aMap.put("c", 300);
		aMap.put("d", 100); aMap.put("b", 400); aMap.put("e", 500);
		aMap.put("f", 600); aMap.put("g", 700); aMap.put("h", 800);
		
		// put 은 value값을 return 한다
		// put하기전 key에 저장된 원래값을 return
		Integer result1 = aMap.put("h", 900); 
		System.out.println(result1 + " " + aMap);
		
		result1 = aMap.put("i", 900);
		System.out.println(result1 + " " + aMap);
		
		boolean result2;
		result2 = aMap.containsValue(Integer.valueOf(900));
		System.out.println(result2);
		
		result2 = aMap.containsKey("a");
		System.out.println(result2);
		
		
		Set<Entry<String, Integer>> entrys = aMap.entrySet();
		
		// iterator를 이용하는방법
		Iterator<Entry<String, Integer>> iter = entrys.iterator();
		while(iter.hasNext()) {
			Entry<String, Integer> entry = iter.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		// for문 사용
		System.out.println("=========================");
		for(Entry<String, Integer> entry: aMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());			
		}
		
		// key만 출력
		for(String s: aMap.keySet()) {
			System.out.print(s + "\t");			
		}
		System.out.println();
		
		// value만 출력
		for(Integer i: aMap.values()) {
			System.out.print(i + "\t");			
		}
		System.out.println();
		
		// get : key가 "a"에 해당하는 값을 리턴
		result1 = aMap.get("a");
		System.out.println(result1);
		
		// getOrDefault : "k"값이 없다면 default값을 출력하는것
		result1 = aMap.getOrDefault("k", Integer.valueOf(0));
		System.out.println(result1);
		
		// 제거
		result1 = aMap.remove("a");
		System.out.println(result1 + " " + aMap);
		
		// 키와 값이 매치되는값을 제거 
		aMap.remove("b", Integer.valueOf(300));
		System.out.println(aMap);
		aMap.remove("b", Integer.valueOf(400));
		System.out.println(aMap);
		
		
	}

}
