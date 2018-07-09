package chap10;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReaderProgram {
	public static void main(String args[]) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream("output.dat"));
			while (true) {
				int number1 = in.readInt();
				int number2 = in.readInt();
				double number3 = in.readDouble();
				
				System.out.println("number1 : " + number1);
				System.out.println("number2 : " + number2);
				System.out.println("number3 : " + number3);
			}
			
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (EOFException eofe) {
			System.out.println("끝");
		} catch (IOException ioe) {
			System.out.println("파일을 읽을 수 없습니다.");
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

}
