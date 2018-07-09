package chap10;

import java.io.IOException;
import java.io.PrintWriter;

public class SmartProgram {
	public static void main(String args[]) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("output.txt");
			String str1 = "내 귀는 소라껍질";
			String str2 = "바다 소리를 그리워한다";
			writer.printf("%s %s", str1, str2);
		} catch (IOException ioe) {
			System.out.println("파일로 출력할 수 없습니다.");
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
}
