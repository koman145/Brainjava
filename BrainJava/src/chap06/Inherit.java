/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap06;

/**
 * @author Administrator
 *
 */
//슈퍼클래스, 부모클래스
class Person{
	int money;
	
	public Person() {
		// 기본생성자
		System.out.println("부모클래스에 기본생성자 Person() 호출");
		this.money = 300000000;
	}
	
	public Person(int money) {
		this.money = money;
		System.out.println("부모클래스에 생성자 Person(int money) 호출");
	}
	
	public void displayMoney() {
		System.out.println("재산 : " + money);
		
	}
	
	public void displayHello() {

	}
}

//서브클래스, 자식클래스
class Child extends Person{
	
	public Child() {
		System.out.println("자식클래스에 기본생성자 Child() 호출");
		super.displayMoney(); //생성자라는 틀 안에서 부모클래스를 나타내는 문구 super
	}
	public void displayHello() {
		System.out.println("안녕하세요.");
	}
}

class USAChild extends Person {
	public void displayHello() {
		System.out.println("Hello.");
	}
}

public class Inherit {

	public static void main(String[] args) {
		
		//Person person = new Person(1000000000);
		//System.out.println("재산 : " + person.money + " 원");
		
		Person person = new Child();
		person.displayHello();
		
		person = new USAChild();
		person.displayHello();
		
		
		
//		Child child = new Child();
//		child.displayMoney();
//		System.out.println(child.money);
	}

}
