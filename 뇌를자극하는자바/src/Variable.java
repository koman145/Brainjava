/**
 * 2018. 5. 1. dev By Kim.J.H
   
   Variable.java
 */

/**
 * @author Administrator
 *
 */
public class Variable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int number1 = 30, number2 = 120;
		int total = plus(number1,number2);
		System.out.println("total:" + total);
	}
	
	static int plus(int a, int b)
	{
		return a+b;
	}

}
