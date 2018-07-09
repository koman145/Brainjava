package chap10;

import java.io.FileWriter;
import java.io.IOException;

public class WriterExample1 {
   public static void main(String args[]) {
      FileWriter writer = null;
      try {
         writer = new FileWriter("output.txt");
         char arr[] = { '뇌', '를', ' ', '자', '극', '하', '는', ' ', '자', '바' };
         for (int cnt = 0; cnt < arr.length; cnt++) {
            writer.write(arr[cnt]);
         }
      } catch (IOException ioe) {
         System.out.println("파일로 출력할수 없습니다.");
      } finally {
         try {
            writer.close();
         } catch (Exception e) {
         }
      }

   }
}