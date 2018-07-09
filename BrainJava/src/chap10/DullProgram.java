package chap10;

import java.io.FileWriter;
import java.io.IOException;

public class DullProgram {
	public static void main(String args[]) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("output.txt");
			String str1 = "내 귀는 소라껍질";
			String str2 = "바다 소리를 그리워한다";
			for (int cnt = 0; cnt < str1.length(); cnt++)
				writer.write(str1.charAt(cnt));
			writer.write('\n');
			for (int cnt = 0; cnt < str2.length(); cnt++)
				writer.write(str2.charAt(cnt));
			writer.write('\n');
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
