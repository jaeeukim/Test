import java.util.Scanner;

import org.w3c.dom.UserDataHandler;

import game.card.Bawi;
import game.card.Bo;
import game.card.Gawi;
import game.card.Hand;
import game.db.Database;
import game.player.ComPlayer;
import game.player.UserPlayer;
import game.record.Record;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserPlayer uPlay;
		ComPlayer cPlay = new ComPlayer();
		Database db = new Database();
		
		int[] record = db.load();
		
		System.out.println("가위 바위 보 게임 입니다.");
		System.out.println("플레이어 이름을 입력하세요");
		System.out.print(">>> ");
		String name = sc.nextLine();
		uPlay = new UserPlayer(name);
		
		uPlay.setRecordArray(record);
		
		System.out.println("계속 진행하려면 Enter 키를 입력하세요.");
		sc.nextLine();
		
		while(true) {
			System.out.println("가위 바위 보 중 하나를 입력하세요.");
			System.out.println("아무 값도 입력하지 않고 Enter 키를 누르거나 입력 값이 틀린 경우");
			System.out.println("임의의 값으로 진행됩니다.");
			System.out.println("종료를 원하는 경우 \"종료\"라고 입력하세요.");
			System.out.print(">>> ");
			String pInput = sc.nextLine();
			
			if(pInput.equals("종료")) {
				System.out.println("게임을 종료 합니다.");
				System.out.println("현재 까지 진행 사항을 저장합니다.");
				db.save(uPlay.getRecordArray());
				break;
			}
			
			
			uPlay.setCardHand(pInput);
			cPlay.randomCardHand();
			
			String res = uPlay.versus(cPlay.getHand());
			cPlay.versus(uPlay.getHand());
			
			System.out.printf("%s 님의 승부 결과 %s 했습니다.\n", uPlay.getName(),res);
			System.out.println("승, 패, 무 결과");
			System.out.println(uPlay.getRecord());
		}
	}

}
