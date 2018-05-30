/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap09;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class After100Days_Teacher {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy년 MM월 dd일 EEEE aa hh시mm분ss초");
		
		cal.setTime(new Date()); //현재시간 설정
		
		cal.add(Calendar.DATE, 5);
		
		System.out.println(dateFormat.format(cal.getTime()));
		
	}

}
