package game.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

public class PenaltyDatabase {

	private File file;
	private String[] penaltys;
	private String userHome;
	
	public PenaltyDatabase() {
		this.userHome = System.getProperty("user.home"); // 알아서 홈 찾아주는 메서드
		this.file = new File(userHome + "/up_down.penalty");	
		this._parser(this._load());
	}
	
	public void add(String data) { 
		this.penaltys = Arrays.copyOf(this.penaltys, this.penaltys.length + 1);
		
		try(FileWriter fw = new FileWriter(this.file, true)){
			this.penaltys[this.penaltys.length - 1] = data;
			fw.write(data + "\n"); 
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getList() {
		return this.penaltys.clone();
	}
	
	public void modify(int index, String penalty) {
		this.penaltys[index] = penalty;
		this._save();
	}
	
	public void remove(int index) {	
		String[] temp = new String[this.penaltys.length - 1];
		System.arraycopy(this.penaltys, 0, temp, 0, index);
		System.arraycopy(this.penaltys, index + 1, temp, index, this.penaltys.length - (index + 1));
		this.penaltys = temp;
		
		this._save();
	}
	
	private void _parser(String data) {
		StringTokenizer st = new StringTokenizer(data, "\n");
		String[] res = new String[st.countTokens()];
		int idx = 0;
		while(st.hasMoreTokens()) {
			res[idx++] = st.nextToken();
		}
		this.penaltys = res.clone();
	}
	
	//보조스트림 사용
	private String _load() {
		try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){
			while(dis.available() > 0) {
				String str = dis.read(null, 0, 0)
			}
			return str;
			
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	// 기존방식
//	private String _load() {
//		try(FileReader fr = new FileReader(this.file)){
//			char[] buffer = new char[1024];
//			char[] data = new char[0];
//			while(true) {
//				int rCnt = fr.read(buffer);
//			
//				if(rCnt == -1) {
//					break;
//				}
//				
//				int stIdx = data.length;
//				data = Arrays.copyOf(data, data.length + rCnt);
//				System.arraycopy(buffer, 0, data, stIdx, rCnt);
//			}
//			return new String(data);
//			
//		} catch (FileNotFoundException e) {
//			return null;
//		} catch (IOException e) {
//			return null;
//		}
//	}
	
	// 기존방식
//	private void _save() {
//		try(FileWriter fw = new FileWriter(file)){
//			for(int i = 0; i < this.penaltys.length; i++) {
//				fw.write(this.penaltys[i] + "\n");				
//			}
//			fw.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
	
	// 보조스트림 방식
	private void _save() {
		try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
			for(int i = 0; i < this.penaltys.length; i++) {
				dos.write(penaltys[i] + "\n");				
			}
			dos.write(byte[] );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
