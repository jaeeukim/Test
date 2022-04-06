package exam04;


public class Paper {
	private int page;
	private int nowPage = 1;
	private int max = 380;
	public Paper() {}
	
	//한장씩 넘기기
	public void paperPlus() {
		if(nowPage + 1 >= max) {
			System.out.println("더 이상 페이지를 넘길 수 없습니다.\n");
		} else {
		this.nowPage++;
		System.out.println("페이지를 뒤로 한장 넘깁니다.");
		System.out.println("현재 페이지 : " + nowPage);
		}
	}
	public void paperMinus() {
		if(nowPage == 1) {
			System.out.println("더 이상 페이지를 넘길 수 없습니다.\n");			
		}else {
			this.nowPage--;
			System.out.println("페이지를 앞으로 한장 넘깁니다.");
			System.out.println("현재 페이지 : " + nowPage);			
		}
	}
	
	//원하는 장수만큼 넘기기
	public void addPaper(int page) {
		if(nowPage + page <= 380 && nowPage + page >= 1) {
			if(page > 0) {
				System.out.printf("페이지를 뒤로 %d장 넘깁니다.\n", page);
				nowPage += page;
			} else if(page < 0){
				System.out.printf("페이지를 앞으로 %d장 넘깁니다.\n", Math.abs(page));
				nowPage -= Math.abs(page);
			}
			System.out.println("현재 페이지 : " + nowPage);
			System.out.println("");
		}else {
			System.out.println("더 이상 페이지를 넘길 수 없습니다.\n");						
		}
			
	}
	
}
