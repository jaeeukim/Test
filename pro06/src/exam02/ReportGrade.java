package exam02;

import java.util.Arrays;

public class ReportGrade {
	private String name;
	private String[] subjects;
	private double[] grades;
	
	public ReportGrade(String name) {
		this.name = name;
	}
	public ReportGrade(String name, String[] subjects) {
		this(name);
		this.subjects = subjects;
	}

	// getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSubjects() {
		return subjects;
	}
	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}
	public double[] getGrades() {
		return grades;
	}
	public void setGrades(double[] grades) {
		this.grades = grades;
	}
	
	public void setGrades(String subject, double grade) {
		/*
		 * 과목 배열{"국어", "영어", "수학", "사회", "체육"}
		 * subject = "수학";
		 */
		int idx = -1;
		for(int i = 0; i< subjects.length; i++) {
			if(this.subjects[i].equals(subject)) {
				idx = i;
				break;
			}
		}
		this.grades[idx] = grade;
	}
	
	public double getGrade(String subject) {
		int idx = -1;
		
		for(int i = 0; i< subjects.length; i++) {
			if(this.subjects[i].equals(subject)) {
				idx = i;
				break;
			}
		}
		return this.grades[idx];
	}
	
	// 새로운 과목과 점수 추가...
	
	public void addSubject(String subject) {
		//점수 0점으로 추가
		this.addSubject(subject, 0); 
	}
	
	public void addSubject(String subject, double grade) {
		this.subjects = Arrays.copyOf(this.subjects, this.subjects.length + 1);
		this.grades = Arrays.copyOf(this.grades, this.grades.length + 1);
		
		subjects[subjects.length - 1] = subject;
		grades[grades.length - 1] = grade;		
		
		
	}
	
	
	
	
}
