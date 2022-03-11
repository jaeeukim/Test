package exam02;

public class Sample03 {

	public static void main(String[] args) {
		for(int i = 99; i > 0; i--) {
			System.out.println(i);
		}
		
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		for(char c = '가'; c <= '힣'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		for(char c = 'ㄱ'; c <= 'ㅎ'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		for(char c = 'ㅏ'; c <= 'ㅣ'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		
		
	}

}
