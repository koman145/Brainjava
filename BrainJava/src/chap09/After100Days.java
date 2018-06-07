/**
 * 	2018. 5. 15.  Dev Kim.J.H
 */
package chap09;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 *
 */
public class After100Days {

	public static void main(String[] args) {
		
		GregorianCalendar calender = new GregorianCalendar();
				
		SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy년 MM월 dd일 EEEE aa hh시mm분ss초");
		
		System.out.println("  현재  날짜  : " + dateFormat.format(calender.getTime()));
				
		calender.add(GregorianCalendar.DATE, +100);
		
		System.out.println("100일 후 날짜 : " + dateFormat.format(calender.getTime()));		
	}

}