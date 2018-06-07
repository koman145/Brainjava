/**
 * 	2018. 5. 15.  Dev Kim.J.H
 */
package chap09;

import java.util.Random;

/**
 * @author Administrator
 *
 */
public class RandomExample1 {

	public static void main(String[] args) {
		Random random = new Random();
		System.out.println(random.nextInt(100));
		System.out.println(random.nextInt(100));
		System.out.println(random.nextInt(100));
	}

}