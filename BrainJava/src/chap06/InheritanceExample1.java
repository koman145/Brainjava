/**
 * 	2018. 5. 16.  Dev Kim.J.H

 */
package chap06;

class Account {
	String accountNo;
	String ownerName;
	int balance;

	public Account() {
		System.out.println("Account() 기본생성자.");
	}

	public Account(String accountNo, String ownerName, int balance) {
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
		System.out.println("Account(String,String,int) : 나불렀쪙?");
	}

	void deposit(int amount) {
		balance += amount;
	}

	int withdraw(int amount) throws Exception {
		if (balance < amount)
			throw new Exception("잔액이 부족합니다.");
		balance -= amount;
		return amount;
	}

	public int displayBalance() {
		return balance;
	}
}

class CheckingAccount extends Account {
	String cardNo;

	// public CheckingAccount() {
	// System.out.println("CheckingAccount() 기본생성자 호출");
	// }

	public CheckingAccount(String accountNo, String ownerName, int balance, String cardNo) {
		// 부모클래스의 Account(String, String, int) 생성자 호출
		super(accountNo, ownerName, balance);
		// this.accountNo = accountNo;
		// this.ownerName = ownerName;
		// this.balance = balance;
		this.cardNo = cardNo;
		System.out.println("CheckingAccount(String,String,int,String) 생성자");
	}

	int pay(String cardNo, int amount) throws Exception {
		if (!cardNo.equals(this.cardNo)) // || (balance < amount))
			throw new Exception("계좌번호가 틀립니다.");
		if (balance < amount)
			throw new Exception("잔액이 부족합니다.");
		return withdraw(amount);
	}
}

public class InheritanceExample1 {
	public static void main(String[] args) {

		CheckingAccount obj = new CheckingAccount("111-22-33333333", "홍길동", 100000, "5555-6666-7777-8888"); // 기본생성자

		// CheckingAccount obj = new CheckingAccount(); //기본생성자

		/*
		 * obj.accountNo = "111-22-33333333"; obj.ownerName = "홍길동"; obj.cardNo =
		 * "5555-6666-7777-8888";
		 */

		// obj.deposit(100000); //입금

		try {
			int paidAmount = obj.pay("5555-6666-7777-8888", 147000);
			System.out.println("지불액 : " + paidAmount);
			System.out.println("잔액 : " + obj.balance);
		} catch (Exception e) {
			String msg = e.getMessage();
			System.out.println("예외메세지 : " + msg);
		}

	}

}