package chap20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

class ReceiverThread2 extends Thread {
	Socket socket;

	ReceiverThread2(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String str = reader.readLine();
				if (str == null)
					break;
				System.out.println("수신>" + str);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
