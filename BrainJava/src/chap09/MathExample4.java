/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap09;

/**
 * @author Administrator
 *
 */
public class MathExample4 {

	public static void main(String[] args) {
		for(int i=1; i<=7; i++) {
			
			double randomValue = Math.random();
			int value = (int)(randomValue * 45) + 1;
			System.out.println(i + "번째 난수 :" + value);
		}
	}

}
