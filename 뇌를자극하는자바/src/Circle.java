/**
 * 2018. 5. 2. Dev By Kim.J.H
   
   Circle.java
 */

/**
 * @author Administrator
 *
 */
public class Circle {
	public int radius;
	public String name;
	
	public Circle() { //�Ű����� ���� ������
		radius = 1;
		name = "";
	}
	
	
	public Circle(int r, String n) { //�Ű������� ���� ������
		radius = r; 
		name = n;
	}
	
	public double getArea() { //method
		return 3.14 * radius * radius;
	}
	


	public static void main(String[] args) { //main method
		
		Circle pizza = new Circle(10, "�ڹ�����");
		double area = pizza.getArea();
		System.out.println(pizza.name + "�� ������" + area);
		
	}

}
