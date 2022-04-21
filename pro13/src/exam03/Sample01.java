package exam03;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * 보조 스트림
		 * 		- 입출력에 사용하는 기반 스트림을 보조하는 역할을 수행.
		 * 		- 기반 스트림의 기능 높이거나 추가 기능을 부여하기 위해 사용.
		 * 		- 보조 스트림은 반드시 기반 스트림을 사용해야 사용 가능(단독 사용 불가)
		 * 		- 문자 변환 보조스트림(InputStreamReader, OuputStreamWriter)
		 * 		  바이트 기반 스트림 + 문자 변환 보조 스트림 사용
		 * 		- 성능 향상 보조 스트림(BufferedInputStream, BufferedOutputStream, BufferedReader, BufferedWriter)
		 *		  바이트 기반 스트림 + 성능 향상 보조 스트림 (BufferdInputStream, BufferedOupuutStream)
		 *		  문자 기반 스트림 + 성능 향상 보조 스트림  (BufferedReader, BufferedWriter)
		 *		- 기본 데이터 타입 보조 스트림(DataInputStream, DataOutputStream)
		 *		  바이트 기반 스트림 + 기본 데이터 타입 보조 스트림
		 *		- 객체 보조 스트림(ObjectInputStream, ObjectOutputStream)
		 *		  바이트 기반 스트림 + 객체 보조 스트림
		 */
		
	Sample01 smp = new Sample01();
	
	//  smp.ex01();
	//	smp.ex02();
	//	smp.ex03();
	//	smp.ex04();
		smp.ex05();
	}
	
	public void ex01() {
		// 문자 변환 보조 스트림
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/문자보조스트림.테스트");
		
		
//		try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f)){ // 한줄에 작성하면 close안해도 ㄱㅊ 
		try(FileOutputStream fis = new FileOutputStream(f)){
			//보조스트림이 기반스트림을 가져다 사용
			OutputStreamWriter osw = new OutputStreamWriter(fis); 
			//	fis.write("문자열".getBytes()); //문자열을 쓸때 getBytes를 사용해야하지만
			//	osw.write(userHome); //보조스트림 사용시에는 그냥 사용가능
			osw.write("바이트 기반 스트림 + 문자 보조 스트림");
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(InputStreamReader isr = new InputStreamReader(new FileInputStream(f))){
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			while(isr.ready()) { //size가 -1인지 볼 필요가 없어짐
				int size = isr.read(buff);
				sb.append(buff, 0, size); // 0번부터 size만큼
			}
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void ex02() {
		// 성능 향상 보조 스트림
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))){
			bos.write("바이트 기반 스트림 + 성능 향상 보조 스트림".getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))){
			StringBuilder sb = new StringBuilder();
			while(true) {
				int size = bis.available(); //읽을수 있는만큼 읽어라
				if(size == 0) { // 더이상 읽을 수 없다
					break;
				}
				byte[] bytes = new byte[size];
				int eof = bis.read(bytes, 0, size);
				sb.append(new String(bytes));
			}
			System.out.println(sb.toString());
			
			/* StringBuiler부터 sb.toString만큼 이걸로 대체 가능
			byte[] bytes = bis.readAllBytes();
			String data = new String(bytes); //전체 읽는거기때문에 반복문을 쓸 필요가 없다!
			System.out.println(data);
			*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		//OutputStreamWriter는 writer의 하위이지만 stream을 생성자로 받기때문에
		// BufferedInputStream을 사용한다.
		try(OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(f)))){	
			osw.write("바이트 기반 스트림 + 성능 향상 보조 스트림 + 문자 변환 보조 스트림");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new FileInputStream(f)))){
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			while(isr.ready()) { //size가 -1인지 볼 필요가 없어짐
				int size = isr.read(buff);
				sb.append(buff, 0, size); // 0번부터 size만큼
			}
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void ex03() {
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		//쓰기
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
			bw.write("문자 기반 스트림 + 성능 향상 보조 스트림");
			bw.newLine(); //개행
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//읽기
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			while(br.ready()) {
				System.out.println(br.readLine()); // 한 줄씩 읽겠다. 반환타입은 String
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void ex04() {
		//기본 데이터 타입 보조스트림
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/기본데이터타입보조스트림.테스트");
	
		try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))){
			dos.writeBoolean(false);
			dos.writeInt(100);
			dos.writeDouble(12.34);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// 실행하면 false, 100, 12.34로 출력되지만 해당 파일을 열었을때는
		// 우리가 읽을 수 있는 형태의 파일이 아니다.
		try(DataInputStream dis = new DataInputStream(new FileInputStream(f))){
			boolean b1 =dis.readBoolean();
			int i1 = dis.readInt();
			double d1 = dis.readDouble();
		
			System.out.println(b1);
			System.out.println(i1);
			System.out.println(d1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void ex05() {
		// 객체 보조 스트림
		// 주의 : 객체를 바이트 데이터로 변환 했을 때의 객체 구조가 바이트 데이터를 객체로 변환 할 때의
		//		 객체 구조와 동일해야 한다. (객체 구조가 바뀌면 변환에 실패한다.)
		// 		 (예를 들어 ObjSample에 객체 갯수가 바뀌거나 이름이 바뀌는 것)
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/객체보조스트림.테스트");
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
			ObjSample os = new ObjSample(123, 12.34, false, "객체 파일로 저장");
			oos.writeObject(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
			Object obj = ois.readObject();
			ObjSample os = (ObjSample)obj;
			System.out.println(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
