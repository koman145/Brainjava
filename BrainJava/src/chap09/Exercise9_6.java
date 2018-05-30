/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap09;

/**
 * @author Administrator
 *
 */
public class Exercise9_6 {

	public static void main(String[] args) {
		int f = 0, b = 0;
		for(int i=1; i<=100; i++) {		
			
			double randomValue = Math.random();
			int value = (int)(randomValue * 2) + 1;
			
		if (value == 1)
			f++;
		else
			b++;
		}
		System.out.println("앞면 : " + f);
		System.out.println("뒷면 : " + b);
	}
}


