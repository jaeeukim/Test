package exam01.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UDPServer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			/*
			 * 1. 네트워크 통신을 위한 IP주소 정보 설정
			 */
			byte[] ipAddr = new byte[] {(byte)192, (byte)168, 35, 122}; 
			InetAddress iNet = InetAddress.getByAddress(ipAddr);
			/*
			 * 2. UDP 통신용 소켓 생성
			 */
			DatagramSocket dSock = new DatagramSocket(5100, iNet);
			
			while(true) {
				/*
				 * 3. 클라이언트가 보낸 데이터 그램 패킷 메시지를 수신대기함.	
				 */
				byte[] buff = new byte[1024];
				DatagramPacket dPack = new DatagramPacket(buff, buff.length);
				System.out.print("수신 대기...");
				dSock.receive(dPack);
				
				
				SimpleDateFormat sFormat = new SimpleDateFormat("[yyyy.MM.dd a hh:mm:ss.sss]");
				String time = sFormat.format(new Date());
				
				InetAddress clientIP = dPack.getAddress();
				int clientPort = dPack.getPort();
				System.out.printf("%s - %s:%d 에서 접속했습니다.\n", time, clientIP.toString(), clientPort);
				
				// 언제 접속했는지 파일로 기록 남기기
				try(BufferedWriter bw = new BufferedWriter
						(new FileWriter
						(new File(System.getProperty("user.home") + "/connection.log"), true))){
					
					bw.write(time + "\n");
					bw.flush();
				}catch(IOException e) {
					e.printStackTrace();
				}
				/*
				 * 4. 수신한 메시지를 문자열로 변환
				 */
				String rev = new String(dPack.getData(), 0, dPack.getData().length);
				System.out.println("Client 메시지 수신하였습니다.");
				System.out.println("Clent Message -> " + rev);
				
				/*
				 * 5. 응답을 위한 데이터 그램 생성 후 전송
				 */
				String msg = "수신함.";
				clientIP = dPack.getAddress();
				clientPort = dPack.getPort();
				DatagramPacket resPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, clientIP, clientPort);
				dSock.send(resPack);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
