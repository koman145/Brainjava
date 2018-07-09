package chap20;

import java.net.Socket;

public class ClientExample4 {
	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage : java ClientExample4 <user-name>");
			return;
		}
		try {
			Socket socket = new Socket("192.168.0.61", 9002);
			Thread thread1 = new SenderThread3(socket, args[0]);
			Thread thread2 = new ReceiverThread3(socket);
			thread1.start();
			thread2.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
