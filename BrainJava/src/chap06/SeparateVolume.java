package chap06;

public class SeparateVolume implements Lendable {

	String requestNo; // 청구번호
	String bookTitle; // 제목
	String writer; // 저자
	String borrower; // 대출일
	String checkOutDate; // 대출일
	byte state; // 대출상태

	SeparateVolume(String requestNo, String bookTitle, String writer) {
		this.requestNo = requestNo;
		this.bookTitle = bookTitle;
		this.writer = writer;
	}

	public void checkOut(String borrower, String date)  {
		if (state != STATE_NORMAL)
			return;
		this.borrower = borrower;
		this.checkOutDate = date;
		this.state = STATE_BORROWED;
//		this.state = 1;
		System.out.println("*" + bookTitle + "이(가) 대출되었습니다.");
		System.out.println("대출인 : " + borrower);
		System.out.println("대출일 : " + date + "\n");
	}

	public void checkIn() {
		this.borrower = null;
		this.checkOutDate = null;
		this.state = STATE_NORMAL;
//		this.state = 0;
		System.out.println("*" + bookTitle + "이(가) 반납되었습니다.\n");
	}

}