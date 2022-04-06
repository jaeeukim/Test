package exam01;

public class Grade {
	private String name;
	private double score;
	private char rank;
	private final static char[] RANK = new char[]{'F', 'F', 'F', 'F', 'E', 'E', 'D', 'C', 'B', 'A', 'A'	};
	// 기본생성자
	public Grade() {}
	
	public Grade(String name) {
		this.name = name;
	}
	
	public Grade(String name, double score) {
		this(name);
		this.setScore(score);
	}

	// getter, setter 만들기
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		if(score <= 100 && score >= 0) {
			this.score = score;			
		} else {
			this.score = 0;
		}
		this._setRank();
	}

	public char getRank() {
		return rank;
	}
	
	/*private void _setRank() {
		if(this.score < 40) {
		this.rank = 'F';
		}else if(this.score < 60) {
			this.rank = 'E';
		}else if(this.score < 70) {
			this.rank = 'D';
		}else if(this.score < 80) {
			this.rank = 'C';
		}else if(this.score < 90) {
			this.rank = 'B';
		}else if(this.score <= 100) {
			this.rank = 'A';
		}
	}
	*/
	
	/*
	 * private void _setRank(){
	 * 	switch((int)(this.score / 10)){
	 * 		case 10:	 case9:
	 * 			this.rank = 'A'; break;
	 * 		case 9: 
	 * 			this.rank = 'B'; break;
	 * 		...
	 * 
	 * 		case 3:		case2:		case1: 		case0:
	 * 			this.rank = 'F'; break;
	 */
	private void _setRank() {
		this.rank = RANK[(int)(this.score / 10)]; 	
	}
	
	

	
	
}
