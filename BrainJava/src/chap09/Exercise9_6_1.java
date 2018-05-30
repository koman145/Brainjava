/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap09;

import java.util.GregorianCalendar;
import java.util.Random;

/**
 * @author Administrator
 *
 */
public class Exercise9_6_1 {

	public static void main(String[] args) {
		GregorianCalendar gcal = new GregorianCalendar();
		int f=0, b=0;
		Random r = new Random(gcal.getTimeInMillis());
		
		for(int i= 0; i<=9; i++) {
			
			int rV = r.nextInt(2);
			
			if(rV == 0) {
				f++;
			}
			else {
				b++;
			}
		}
		
		System.out.println("앞면 : " + f);
		System.out.println("뒷면 : " + b);
	
	}

}
