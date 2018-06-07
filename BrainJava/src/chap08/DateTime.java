/**
 * 	2018. 5. 11.  Dev Kim.J.H
 */
package chap08;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 *
 */
public class DateTime {

	public static void main(String[] args) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		int year = gregorianCalendar.get(Calendar.YEAR);
		int month = gregorianCalendar.get(Calendar.MONTH) + 1;
		int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		System.out.printf("오늘은 %d년 %d월 %d일입니다. %n", year, month, day);
	}
}