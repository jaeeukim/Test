package exam02;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		ReportGrade report = new ReportGrade("홍길동");
		
		double[] grades = new double[] {78.5, 79.3, 87.2, 93.8, 93.4};
		String[] subjects = new String[] {"국어", "영어", "수학", "사회", "체육"};
		report.setGrades(grades);
		report.setSubjects(subjects);
	
		
		report.setGrades("수학", 92.5);
		System.out.println(Arrays.toString(report.getGrades()));
		
		System.out.println(report.getGrade("사회"));
		
		report.addSubject("과학");
		System.out.println(report.getGrade("과학"));
	}
}
