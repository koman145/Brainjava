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
	// �Ӽ��ʵ�(������)
	int number1 = 0; 
	int number2 = 0;
	
	// �޼ҵ�(�Լ�/���)
	public int plus(int x, int y, int z)
	{
		int total = x + y + z; // total = 100 + 200;
		return total;
	}
	
	public static void main(String[] args) {
		
		int num1, num2, num3;
		
		System.out.println("�� ���� ���� �Է�");
		Scanner scan = new Scanner(System.in); //scanf("%d", &num1); -- c��� ��Ÿ��
		num1 = scan.nextInt();
		num2 = scan.nextInt();
		num3 = scan.nextInt();
	
		//��ü����
		Carculator cal = new Carculator();
		int result = cal.plus(num1, num2, num3);
		System.out.println("result:" + result);
	}

}
