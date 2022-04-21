package exam01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Sample05 {

	public static void main(String[] args) {
		/*
		 * FileWriter 클래스
		 * 		- 문자 단위로 파일에 데이터를 쓰기위해 사용
		 * 		- 보통 텍스트 파일에 데이터를 쓰기위해 사용
		 */
		File f = new File("C:/Users/USER/eclipse/jee-2021-12/eclipse/read_test.txt");
		
		try(FileWriter fw = new FileWriter(f, true)) { 
			fw.write(65);
			
			char[] bArr = new char[] {66, 67, 68, 69};
			fw.write(bArr);
			
			fw.write("\nFileOutputStream\n");
			
			fw.write("한글로도 쓰기\n");
			
			fw.flush();
		} catch (FileNotFoundException e) {
			System.out.println("파일 쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("쓰기 작업 중 문제가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
