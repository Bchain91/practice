package kcci.iot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientThreadRunnable implements Runnable {

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		// 클라이언트 메인 내용
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Socket socket = null;

		try {
			socket = new Socket();

			System.out.println("[연결 요청]");
			socket.connect(new InetSocketAddress("localhost", 5000));
			System.out.println("[연결 성공]");

			byte[] bytes = null;
			String message = null;

			OutputStream os = socket.getOutputStream();
			//message = "Hello Server";
			System.out.print("Server에게 보낼 메세지를 입력하세요 : ");
			message = sc.nextLine();
			bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("[데이터 보내기 성공]");

			InputStream is = socket.getInputStream();
			bytes = new byte[100];
			int readByteCount = is.read(bytes);
			message = new String(bytes, 0, readByteCount, "UTF-8");
			System.out.println("[데이터 받기 성공]: " + message);

			os.close();
			is.close();
		} catch (Exception e) {
			System.out.println("서버가 중지되었습니다");
		}

		if (!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e1) {
			}
		}
	}

}
