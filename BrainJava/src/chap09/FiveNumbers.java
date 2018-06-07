/**
 * 	2018. 5. 15.  Dev Kim.J.H
 */
package chap09;

import java.util.Random;

/**
 * @author Administrator
 *
 */
public class FiveNumbers {

	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 4, 5};
		Random random = new Random();
		int index = random.nextInt(5);
		System.out.println(arr[index]);
		
	}

}