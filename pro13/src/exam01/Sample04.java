package exam01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Sample04 {

	public static void main(String[] args) {
		/*
		 * FileReader 클래스
		 * 		- 문자 기반 스트림으로 문자 단위로 파일을 읽는다.
		 * 		- 보통 텍스트 파일을 읽기 위해 사용
		 */
		File f = new File("C:/Users/USER/eclipse/jee-2021-12/eclipse/read_test.txt");

		StringBuilder sb = new StringBuilder();
		FileReader fr = null;
		try {
			fr = new FileReader(f);

			// 버퍼 크기만큼 읽는다.
			char[] buffer = new char[4];
			char[] readChars = new char[0]; 
			
			while(true) {
				int i = fr.read(buffer);
				
				if(i == -1) {
					break;
				}
				int endIndex = readChars.length;
				readChars = Arrays.copyOf(readChars, readChars.length + i);
				System.arraycopy(buffer, 0, readChars, endIndex, i);
			}	
			sb.append(new String(readChars));
			System.out.println(sb.toString());
			
			
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

}
