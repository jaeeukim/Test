package exam02.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			/*
			 * 1.클라이언트용 소켓 생성
			 */
			byte[] addr = new byte[] {(byte)192, (byte)168, 35, 122};
			InetAddress serverIP = InetAddress.getByAddress(addr);
			int serverPort = 50000;
			
			Socket sock = new Socket(serverIP, serverPort);
			/*
			 * 2. 통신용 입출력 스트림 생성
			 */
			BufferedReader sockIn = new BufferedReader  //성능향상
					(new InputStreamReader(sock.getInputStream()));  //문자변환 보조, 바이트 기반 스트림
				//성능향상 <- 문자변환보조 <- 바이트기반	
			BufferedWriter sockOut = new BufferedWriter(
					new OutputStreamWriter(sock.getOutputStream()));
			
			while(true) {
				// 서버로부터 수신한 메시지가 있으면 반복 진행.
				while(sockIn.ready()) {
					String line = sockIn.readLine();
					System.out.println(line);
				}
				
				// 서버에 메시지 전송
				System.out.print("Client : ");
				String msg = sc.nextLine();
				sockOut.write(msg);
				sockOut.newLine();
				sockOut.flush();
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
