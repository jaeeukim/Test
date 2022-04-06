package exam01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Sample07 {

	Scanner sc = new Scanner(System.in);

	public void ex01() {
		/*
		 * 사용자 입력으로 년/월/일 형식의 날짜를 입력 받아
		 * 날짜 배열에 저장하기 위한 코드를 작성한다.
		 * (입력 형식은 반드시 년/월/일 형식이어야 한다. 
		 * 	형식이 다른경우 다시 압력하도록 한다.)
		 */
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String input = "";
		Date[] dates = new Date[0];
		
		while(true) {
			System.out.print("년/월/일 형식으로 날짜를 입력하시오(e를 입력하면 종료) : ");
			input = sc.nextLine();
			if(input.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
				try {
					dates = Arrays.copyOf(dates, dates.length + 1);
					dates[dates.length - 1] = format.parse(input);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(input.equals("e")) {
				break;				
			}
		}
		System.out.println(Arrays.toString(dates));
	}
	
	public void ex02() {
		/*
		 * 사용자 입력으로 년/월/일 형식의 날짜를 입력 받은 후
		 * 입력 받은 날짜가 현재 날짜로부터 얼만큼의 일자인지
		 * 그 차이를 구하는 코드를 작성한다.
		 * (과거의 날짜는 D-day - 10일, 미래의 날짜는 D-day +10일) 
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String input = "";
		Date[] dates = new Date[1];
		
		while(true) {
			System.out.print("년/월/일 형식으로 날짜를 입력하시오 : ");
			input = sc.nextLine();
			if(input.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
				try {
					dates[0] = format.parse(input);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(dates[0]);
		try {
			c2.setTime(format.parse(format.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(c1.getTime());
		System.out.println(c2.getTime());
		
		int totalDate = 0;
		
		// .after() .before()
		if(c1.after(c2)) {
			// +1
			while(c1.compareTo(c2) != 0) {
				c1.add(Calendar.DATE, -1);
				totalDate++;
			}	
		} else {
			// -1
			while(c1.compareTo(c2) != 0) {
				c1.add(Calendar.DATE, 1);
				totalDate--;
			}	
		}
		
		
//		int year = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
//		int month = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
//		int date = c1.get(Calendar.DATE) - c2.get(Calendar.DATE);
//		
//		int totalDate = 0;
//		if(year != 0) {
//			totalDate += year * 365;
//		}
//		if(month != 0) {
//			totalDate += month * 30;
//		}
//		if(date != 0) {
//			totalDate += date;
//		}
//		
//		System.out.println("D-Day " + totalDate);
	}
	
	
	public void ex03() {
		
		/*
		 * 프로그램이 동작한 후 부터 종료할 떄가지의 시간 기록을 남기기 위한 코드를
		 * 작성한다.
		 * 동작 시킬 프로그램은 1 ~ 100,000 까지의 누적합을 구하는 코드로
		 * 해당 반복이 얼만큼의 시간이 걸리는지 기록을 출력한다.
		 */
		
		
//		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초. SSS");
//		String now = dFormat.format(new Date());
//		System.out.println(now);
		Date start = new Date();
		
		int sum = 0;
		for(int i = 1; i <= 100000; i++) {
			sum += i;
		}
		Date end = new Date();
		
		long timer = end.getTime() - start.getTime();		
		System.out.println("걸린 시간 : " + timer + " 밀리초");
		
//		System.out.println(sum);
//		now = dFormat.format(new Date());
//		System.out.println(now);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Sample07 sample = new Sample07();
		sample.ex03();
		 
		
		
	}

}
