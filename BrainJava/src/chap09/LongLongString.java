/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap09;

/**
 * @author Administrator
 *
 */
public class LongLongString {
	public static void main(String[] args) {
		
	StringBuilder stringbuilder = new StringBuilder();
	
	for(String str : args) {
		stringbuilder.append(str);
		stringbuilder.append(" ");
	}
	
	System.out.println(stringbuilder);
	}

}
