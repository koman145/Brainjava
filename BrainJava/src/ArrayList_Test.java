import java.util.ArrayList;
//import java.util.*;
/**
 * 	2018. 5. 9.  Dev Kim.J.H
	BrainJava
	
 */
/**
 * @author Administrator
 *
 */

public class ArrayList_Test {

	public static void main(String[] args) {
		
		ArrayList<String> fruits = new ArrayList<String>(3);
		fruits.add("포도");
		fruits.add("딸기");
		fruits.add("복숭아");
		fruits.add("아보가드로");
		
		
		
		System.out.println("과일 출력");
		
		/*
		int size = fruits.size();
		for (int i=0; i < size; i++) {
			System.out.println(fruits.get(i));
		}
		*/
		
		
		for (int i=0; i < fruits.size(); i++) {
			System.out.println(fruits.get(i));
		}
		
		fruits.remove(2);
		
		for (int i=0; i < fruits.size(); i++) {
			System.out.println(fruits.get(i));
		}
		
			
		
		/*
		System.out.println(fruits.get(0));
		System.out.println(fruits.get(1));
		System.out.println(fruits.get(2));
		System.out.println(fruits.get(3));
		*/
	}
}
