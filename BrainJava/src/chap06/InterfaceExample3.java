/**
 * 	2018. 5. 18.  Dev Kim.J.H

 */
package chap06;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class InterfaceExample3 {

	public static void main(String[] args) {
		SeparateVolume obj = new SeparateVolume("863?774개", "개미", "베르베르");
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String strDate = sf.format(date);
		
		System.out.println(strDate);
		
		try {
		obj.checkOut("홍길동", strDate);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		printState(obj);

	}

	static void printState(SeparateVolume obj) {
		if (obj.state == Lendable.STATE_NORMAL) {
			System.out.println("==========================");
			System.out.println("대출상태 : 대출가능");
			System.out.println("==========================");
		} else {
			System.out.println("==========================");
			System.out.println("대출상태 : 대출불가능");
			System.out.println("대출인 : " + obj.borrower);
			System.out.println("대출일 : " + obj.checkOutDate);
			System.out.println("==========================");
		}
	}

}
