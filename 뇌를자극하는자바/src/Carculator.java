import java.util.Scanner;

/**
 * 2018. 5. 1. dev By Kim.J.H
   
   Carculator.java
 */

/**
 * @author Administrator
 *
 */
public class Carculator {
	// 속성필드(데이터)
	int number1 = 0; 
	int number2 = 0;
	
	// 메소드(함수/기능)
	public int plus(int x, int y, int z)
	{
		int total = x + y + z; // total = 100 + 200;
		return total;
	}
	
	public static void main(String[] args) {
		
		int num1, num2, num3;
		
		System.out.println("세 개의 숫자 입력");
		Scanner scan = new Scanner(System.in); //scanf("%d", &num1); -- c언어 스타일
		num1 = scan.nextInt();
		num2 = scan.nextInt();
		num3 = scan.nextInt();
	
		//객체생성
		Carculator cal = new Carculator();
		int result = cal.plus(num1, num2, num3);
		System.out.println("result:" + result);
	}

}
