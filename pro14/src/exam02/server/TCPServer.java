package exam02.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class TCPServer {
	public static void main(String[] args) {
		try {
			/*
			 * 1. 서버용 소켓 생성
			 */
			ServerSocket sSock = new ServerSocket();
			
			/*
			 * 2. 생성한 소켓에 서버IP 주소, 서버 Port 번호 바인딩(연결)
			 */
			byte[] addr = new byte[] {(byte)192, (byte)168, 35, 122}; //내 IP주소
			InetAddress serverIP = InetAddress.getByAddress(addr);
			int serverPort = 50000;
			
		
			InetSocketAddress serverIpPort = new InetSocketAddress(serverIP, serverPort);
			sSock.bind(serverIpPort);
			
			/*
			 * 3. 클라이언트 연결 요청을 대기 후 요청이 오면 accept해서 클라이언트 소켓 생성
			 */
			Socket cSock = sSock.accept(); //accept가 되어야 clientSocket이 만들어짐
			
			InetAddress clientIP = cSock.getInetAddress(); //client에 대한 주소 정보
			int clientPort = cSock.getPort(); //client에 대한 포트 정보 (랜덤)
			int connectionPort = cSock.getLocalPort(); //(Local)자기 자신에 대한 포트 정보 (랜덤)
			
			System.out.printf("%s:%d <-----> %s:%d\n",serverIP.getHostAddress(), connectionPort,
					clientIP.getHostAddress(), clientPort);
			/*
			 * 4. 통신용 입출력 스트림 생성
			 */
			BufferedReader sockIn = new BufferedReader  //성능향상
					(new InputStreamReader(cSock.getInputStream()));  //문자변환 보조, 바이트 기반 스트림
			BufferedWriter sockOut = new BufferedWriter(
					new OutputStreamWriter(cSock.getOutputStream()));
			/*
			 * 5. 지속적인 통신을 위한 반복문
			 */
			boolean disconnect = false;
			while(true) {
				// 클라이언트로부터 수신한 메시지가 있으면 반복 진행.
				while(sockIn.ready()) {
					String line = sockIn.readLine();
					if(line.contains("exit")) { //원래 클라이언트에서 서버 끄게 하면 안됨 test용으로 해봄
						disconnect = true;
						break;
					}
					System.out.println(line);
				}
				if(disconnect) {
					break;
				}
			}
			/*
			 * 6. 통신 종료 후에는 모든 자원 반납.
			 */
			sockIn.close();
			sockOut.close();
			cSock.close();
			sSock.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
