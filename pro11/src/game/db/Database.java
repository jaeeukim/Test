package game.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import game.player.UserPlayer;

public class Database {

	private File file;
	
	public Database() {
		this.file = new File("C:/Users/USER/gbo_game.record");
	}
	
	public Database(File file) {
		this.file = file;
	}
	
	//DataInputStream 사용하기
	// 보조스트림 사용하기
	public int[] load() {
		int[] nArr = new int[3];
		StringBuilder sb = new StringBuilder();
		
		if(file.exists()) {
			try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){
				int i = 0;
				while(dis.available() > 0) {
					nArr[i++] = dis.readInt();
				}				
			} catch (FileNotFoundException e) {
				System.out.println("해당 파일이 없습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("파일을 읽는 중 오류 발생");
				e.printStackTrace();
			}
		}
		return nArr;
	}
	
	//DataInput 사용하기
	public void save(int[] record) {
		try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
			
			for(int i = 0; i < record.length; i++) {
				dos.writeInt(record[i]);
			}
			dos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("파일 쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 보조스트림 사용하기
//	public int[] load() {
//		int[] nArr = new int[3];
//		StringBuilder sb = new StringBuilder();
//	
//		if(file.exists()) {
//			try(BufferedReader br = new BufferedReader(new FileReader(file))){
//				String data = "";
//				while(br.ready()) {
//					data = br.readLine();
//				}
//				StringTokenizer st = new StringTokenizer(data, " ");
//				nArr = new int[st.countTokens()];
//				int i = 0;
//				while(st.hasMoreTokens()) {
//					String s = st.nextToken();
//					nArr[i++] = Integer.parseInt(s);
//				}
//				
//			} catch (FileNotFoundException e) {
//				System.out.println("해당 파일이 없습니다.");
//				e.printStackTrace();
//			} catch (IOException e) {
//				System.out.println("파일을 읽는 중 오류 발생");
//				e.printStackTrace();
//			}
//		}
//		return nArr;
//	}
	//보조스트림 사용하기
//	public void save(int[] record) {
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
//			for(int i = 0; i < record.length; i++) {
//				bw.write(Integer.valueOf(record[i]).toString() + " ");
//			}
//			bw.flush();
//		} catch (FileNotFoundException e) {
//			System.out.println("파일 쓰기 작업을 위한 파일을 찾을 수 없습니다.");
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public int[] load() {
//		// 데이터 불러오기
//		int[] nArr = new int[3];
//		
//		if(file.exists()) {
//			StringBuilder sb = new StringBuilder();
//			
//			try(FileReader fr = new FileReader(file)) {
//				// 버퍼 크기만큼 읽는다.
//				char[] buffer = new char[4];
//				char[] readChars = new char[0]; 
//				
//				while(true) {
//					int i = fr.read(buffer);
//					
//					if(i == -1) {
//						break;
//					}
//					int endIndex = readChars.length;
//					readChars = Arrays.copyOf(readChars, readChars.length + i);
//					System.arraycopy(buffer, 0, readChars, endIndex, i);
//				}	
//				sb.append(new String(readChars));
//				
//				StringTokenizer st = new StringTokenizer(sb.toString(), " ");
//				nArr = new int[st.countTokens()];
//				int i = 0;
//				while(st.hasMoreTokens()) {
//					String s = st.nextToken();
//					nArr[i++] = Integer.parseInt(s);
//				}
//				
//				
//			} catch (FileNotFoundException e) {
//				System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
//				e.printStackTrace();
//			} catch(IOException e) {
//				System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
//				e.printStackTrace();
//			}
//		}
//		return nArr;
//	}
	
//	public void save(int[] record) {
//		// 데이터 저장하기
//		try(FileWriter fw = new FileWriter(file)) { 
//			for(int i = 0; i < record.length; i++) {
//				fw.write(Integer.valueOf(record[i]).toString() + " ");
//			}
//			fw.flush();
//		} catch (FileNotFoundException e) {
//			System.out.println("파일 쓰기 작업을 위한 파일을 찾을 수 없습니다.");
//			e.printStackTrace();
//		} catch(IOException e) {
//			System.out.println("쓰기 작업 중 문제가 발생하였습니다.");
//			e.printStackTrace();
//		}
//	}
	

	

	
}
