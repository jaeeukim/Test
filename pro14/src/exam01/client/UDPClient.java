package exam01.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {

			
//			InetAddress iNet = InetAddress.getByName("localhost"); //127.0.0.1
			
			/*
			 *  1. IP주소(네트워크 통신을 위한 IP 주소 정보 설정)
			 */
				/* A번 : cmd에서 ipconfig 쳐서 나온 IP주소를 배열로 만듦
				byte[] ipAddr = new byte[] {(byte)192, (byte)168, 35, 122}; 
				InetAddress iNet = InetAddress.getByAddress(ipAddr);
				 */
			
				// B번
				InetAddress iNet = InetAddress.getLocalHost();
			
				
			InetAddress serverINet = iNet;
			// System.out.println(iNet.getHostAddress());
			
			/*
			 * 2. UDP 통신용 소켓 생성
			 */
				try {
					DatagramSocket dSock = new DatagramSocket(5000, iNet);
					
					while(true) {
						/*
						 * 3. 사용자가 입력한 메시지를 데이터그램 패킷으로 만들어서 전송.
						 * 
						 * 3번과 4번 순서가 바뀌면 client와 server가 바뀌게 된다고 생각하면 된다.
						 * (먼저 보내는게 Client)
						 */
						System.out.print("Client : ");
						
						String msg = sc.nextLine();
						DatagramPacket dPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverINet, 5100);
						dSock.send(dPack); // 보내는 과정
						
						/*
						 * 4. 서버로부터 수신확인 응답을 받기 위한 부분
						 */
						byte[] buff = new byte[1024];
						DatagramPacket resPack = new DatagramPacket(buff, buff.length);
						dSock.receive(resPack);
						
						InetAddress clientIP = dPack.getAddress(); // 송신측 IP
						int clientPort = dPack.getPort(); // 송신측 Port
						
						String rev = new String(resPack.getData(), 0, resPack.getData().length);
						System.out.println("Server Message -> " + rev);
						
						
					}
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
