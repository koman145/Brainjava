/**
 * 	2018. 5. 14.  Dev Kim.J.H

 */
package chap09;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 *
 */
public class DateFormatExample1 {

	public static void main(String[] args) {
		GregorianCalendar calender = new GregorianCalendar();
		
		SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy년 MM월 dd일 EEEE D일째 aa hh(HH)시mm분ss초");
		
		String str = dateFormat.format(calender.getTime());
		
		System.out.println(str);
	
	}

}
