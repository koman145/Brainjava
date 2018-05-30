package chap06;

public interface Lendable {
	
	//상수필드
	final static byte STATE_BORROWED = 1;	//대출 중			(대출불가능)
				 byte STATE_NORMAL = 0;		//대출되지 않은 상태(대충가능)
	
	abstract void checkOut(String borrower, String date) throws Exception;

	abstract void checkIn();
}
