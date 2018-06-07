/**
 * 	2018. 5. 16.  Dev Kim.J.H
 */
package chap06;

class CreditLineAccount extends Account {
	int creditLine;

	public CreditLineAccount(String accountNo, String ownerName, int balance, int creditLine) {
		super(accountNo, ownerName, balance);
		this.creditLine = creditLine;
	}

	int withdraw(int amount) throws Exception {
		if ((balance + creditLine) < amount)
			throw new Exception("인출이 불가능합니다.");
		balance -= amount;
		return amount;
	}

	public int CreditLine() {
		return creditLine;
	}
}

public class InheritanceExample3 {

	public static void main(String[] args) {
		CreditLineAccount obj = new CreditLineAccount("000-11-111111", "함선농", 10000, 20000000);
		try {
			int amount = obj.withdraw(20010000);
			System.out.println("인출액 : " + amount);

			int balance = obj.displayBalance();
			System.out.println("잔액 : " + balance);

			int creditLine = obj.CreditLine();
			System.out.println("마이너스 한도 : " + creditLine);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}