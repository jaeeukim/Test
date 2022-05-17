package semi_pro;

import java.util.Scanner;

public class DesireView {
	Scanner sc = new Scanner(System.in);
	private Menu menu = new Menu();
	private DesireVO dv = new DesireVO();
	private DesireDAO dao = new DesireDAO();
	private DesireController dc = new DesireController();
	
	public void show() {
		while(true) {
			menu.getMenu();
			
			System.out.print(" >>>  ");
			String input = sc.nextLine();

			switch(input) {
				case "1":
					// 목록보기
					this.look_list();
					break;
				case "2":
					// 수정프로그램실행
					this.modify_menu();
					break;
				case "3":
					// 프로그램 종료
					System.exit(0);
				default :
					System.out.println("잘못된 번호 입력.");	
			}		
		}		
	}
	
	// 목록보기 실행
	private void look_list() {
		System.out.println("어떤 아이템의 정보를 확인하시겠습니까?");
		System.out.print("물건 명 입력 : ");
		String str = sc.nextLine();
		DesireVO data = dao.get(str);
		if(data != null) {
			System.out.println("물건 이름    : " + data.getProd_name());
			System.out.println("물건 타입    : " + data.getProd_type());
			System.out.println("물건 가격    : " + data.getProd_price());
			System.out.println("필요 이유    : " + data.getReason());
			System.out.println("예상사용빈도  : " + data.getExp_use());
			System.out.println("구매욕구     : " + data.getDes_percentage() + " %");
			
			System.out.println("===================================");
			System.out.println("\n\n\n");			
		}else {
			System.out.println("해당하는 물건이 없습니다.");
		}
	}
	
	
	// 수정프로그램 실행
	private void modify_menu() {
		DesireVO data = new DesireVO();
		
		while(true) {
			menu.getMenu_modify();
			System.out.print(" >>>  ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1":
					// 추가메뉴
					this.add_list();
					break;
				case "2":
					// 수정메뉴
					this.modify_list();
					break;
				case "3":
					// 삭제메뉴
					this.delete_list();
					break;
				case "4":
					// 프로그램제거
					System.exit(0);
				default :
					System.out.println("잘못된 번호 입력.");	
			}		
		}		
		
	}



	// 추가하기 위한 프로그램
	private void add_list() {
		DesireVO data = new DesireVO(); 
		System.out.print("갖고싶은 물건 : ");
		data.setProd_name(sc.nextLine());
		System.out.print("물건의 종류 : ");
		data.setProd_type(sc.nextLine());
		System.out.print("물건의 가격 : ");
		data.setProd_price(sc.nextLine());
		System.out.print("필요한 이유 : ");
		data.setReason(sc.nextLine());
		System.out.print("예상사용빈도(상/중/하) : ");	
		data.setExp_use(sc.nextLine());
		System.out.print("구매욕구(0 ~ 100 숫자입력) : ");	
		data.setDes_percentage(sc.nextLine());
		
		boolean result = dc.check(data);
		
		if(result) {
			System.out.println("목록에 추가되었습니다.");
		} else {
			System.out.println("목록 추가에 실패했습니다.");
		}
		
		
	}

	// 수정 프로그램
	private void modify_list() {
		boolean result = true;
		
		System.out.println("어떤 물건의 정보를 수정하시겠습니까?");
		System.out.print("물건의 이름 : ");
		String find_name = sc.nextLine();
		DesireVO data = dao.get(find_name);
		
		
		if(data != null) {
			System.out.printf("\n'%s'의 수정 프로그램이 실행됩니다. \n!!!화살표(>>>)옆에 변경내용을 작성해주세요!!!\n", data.getProd_name());
			System.out.println("입력하지 않고 Enter키를 누르면 이전 값을 유지합니다.\n\n");
			
			
			System.out.println("변경될 타입");
			System.out.print("  >>> ");
			String str = sc.nextLine();
			if(str.isEmpty()) {
				data.getProd_type();
			}else {
				data.setProd_type(str);			
			}
			
			
			System.out.println("변경될 가격");
			System.out.print("  >>> ");
			str = sc.nextLine();
			if(str.isEmpty()) {
				data.getProd_price();
			} else {
				data.setProd_price(str);			
			}
			
			System.out.println("변경될 이유");
			System.out.print("  >>> ");
			str = sc.nextLine();
			if(str.isEmpty()) {
				data.getReason();
			}else {
				data.setReason(str);			
			}
			
			System.out.println("변경될 예상사용빈도(상/중/하)");
			System.out.print("  >>> ");
			str = sc.nextLine();
			if(str.isEmpty()) {
				data.getExp_use();
			}else {
				if(!(str.equals("상") || str.equals("중") || str.equals("하"))) {
					System.out.println("상/중/하 만 입력 가능합니다.");
					result = false;
				} else {
					data.setExp_use(str);							
				}
			}				
			
//			if(!(str.equals("상") || str.equals("중") || str.equals("하"))) {
//				System.out.println("상/중/하 만 입력 가능합니다.");
//				result = false;
//			} else {
//				if(str.isEmpty()) {
//					data.getExp_use();
//				}else {
//					data.setExp_use(str);			
//				}				
//			}
			
			
			System.out.println("변경될 구매욕구(%)(0 ~ 100 사이의 값)");
			System.out.print("  >>> ");
			str = sc.nextLine();
			if(str.isEmpty()) {
				data.getDes_percentage();
			}else {
				if(Integer.parseInt(str) < 0 || Integer.parseInt(str) > 100) {
					System.out.println("0과 100 사이의 값을 입력하세요.");
					result = false;
				}else {
					data.setDes_percentage(str);					
				}
			} 				
			
//			Integer.parseInt(str) < 0 || Integer.parseInt(str) > 100) {
//			System.out.println("0과 100 사이의 값을 입력하세요.");
//			result = false;
//			} else {
//				if(str.isEmpty()) {
//					data.getDes_percentage();
//				}else {
//					data.setDes_percentage(str);
//				} 				
//			}
			
			
			if(dc.update_prod(data) && result) {
				System.out.println("수정완료!");
			} else {
				System.out.println("수정에 실패했습니다.");
			}
			
			
		} else {
			System.out.println("\n해당하는 물건의 이름이 없습니다.\n추가 메뉴를 이용하세요.\n\n");
		}
		
	}
	
	// 삭제 프로그램
	private void delete_list() {
		System.out.println("어떤 물건의 정보를 지우시겠습니까?");
		System.out.print("물건의 이름 : ");
		String find_name = sc.nextLine();
		DesireVO data = dao.get(find_name);
	
		if(data != null) {
			if(dc.delete_prod(data)) {
				System.out.println("삭제 처리가 완료되었습니다.");
			} else {
				System.out.println("삭제를 수행할 수 없습니다.");
			}			
		}else {
			System.out.println("해당하는 이름을 가진 물건이 존재하지 않습니다.");
		}
	}
	
	
	
}
