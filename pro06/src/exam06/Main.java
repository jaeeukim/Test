package exam06;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		GradeList2 gList1 = new GradeList2();
		GradeList2 gList2 = new GradeList2(5);
		
		GradeList2 gList3 = new GradeList2("국어", "영어", "수학", "과학");
		
		for(int i = 0;i < gList3.length(); i++) {
			System.out.println(gList3.getName(i) + ":" + gList3.getResult(i));
		}
		
		System.out.println("==========================");
		
		Grade[] gArr = new Grade[2];
		gArr[0] = new Grade("과학");
		gArr[1] = new Grade("영어");
		
		GradeList2 gList4 = new GradeList2(gArr);
		gArr[0].setName("국어");
		gList4.get(1).setName("수학");
		gList4.add("역사");
		gList4.add("영어", 89.4);
		gList4.add(new Grade("과학", 88.8));
		
		gList4.update("국어", "한국어");
		
		
		for(int i = 0;i < gList4.length(); i++) {
			System.out.println(gList4.getName(i) + ":" + gList4.getResult(i));
		}
		
		GradeList2 gList5 = new GradeList2();
		gList5.add("국어", 78.4);
		gList5.add("수학", 94.1);
		gList5.add("영어", 90.3);
		gList5.add("사회", 49.7);
		gList5.add("과학", 71.3);
		System.out.println(Arrays.toString(gList5.getTop(3)));
		System.out.println(Arrays.toString(gList5.getBottom(3)));
		
		
		
		
		/*내가 푼 GradeList에 관한 메인함수
		Grade[] gArr = new Grade[2];
		GradeList gList = new GradeList(gArr);
		
		
		
		
		//89.4 출력하기
		Grade g1 = new Grade(89.4);
		System.out.println(g1.getResult());
		
		System.out.println("=======================");

		Grade[] gArr = new Grade[2];
		gArr[0] = g1;
		gArr[1] = new Grade(75.3);		

		//객체 배열활용하기
		
		//90.4추가하기
		GradeList gList = new GradeList(gArr);
		gList.add(90.4);
		
		for(int i = 0; i < gList.length(); i++) {
			Grade data = gList.get(i);
			System.out.println(data.getResult());			
		}
		System.out.println("=======================");
		
		//75.3을 90.3으로 바꾸기 & 90.4를 78.4로 바꾸기 
		gList.update(1, 90.3);
		gList.update(90.4, 78.4);

		for(int i = 0; i < gList.length(); i++) {
			Grade data = gList.get(i);
			System.out.println(data.getResult());			
		}
		System.out.println("=======================");
		
		// 78.4 지우기 & 0번인덱스 89.4 지우기
		gList.remove(78.4);
		gList.remove(0);
		
		for(int i = 0; i < gList.length(); i++) {
			Grade data = gList.get(i);
			System.out.println(data.getResult());			
		}
		
		*/
		
	}

}
