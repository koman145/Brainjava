/**
 * 	2018. 5. 16.  Dev Kim.J.H

 */
package chap06;

class BonusPointAccount extends Account {
	int bonusPoint;

	public BonusPointAccount(String accountNo, String ownerName, int balance, int bonusPoint) {
		super(accountNo, ownerName, balance);
		this.bonusPoint = bonusPoint;
	}

	void deposit(int amount) {
		super.deposit(amount);
		bonusPoint += (int) (amount * 0.001);
	}
}

public class InheritanceExample4 {

	public static void main(String[] args) {
		BonusPointAccount obj = new BonusPointAccount("333-33-333333", "김미영", 0, 0);
		obj.deposit(1000000);
		System.out.println("잔액 : " + obj.balance);
		System.out.println("누적 포인트 : " + obj.bonusPoint);
	}

}
