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
	
	public Circle() { //매개변수 없는 생성자
		radius = 1;
		name = "";
	}
	
	
	public Circle(int r, String n) { //매개변수를 가진 생성자
		radius = r; 
		name = n;
	}
	
	public double getArea() { //method
		return 3.14 * radius * radius;
	}
	


	public static void main(String[] args) { //main method
		
		Circle pizza = new Circle(10, "자바피자");
		double area = pizza.getArea();
		System.out.println(pizza.name + "의 면적은" + area);
		
	}

}
