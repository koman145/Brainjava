/**
 * 	2018. 5. 17.  Dev Kim.J.H
 */
package chap06;

public class InheritanceExample8 {

	public static void main(String[] args) {
		EMailSender obj1 = new EMailSender("생일을 축하합니다.", "고객센터", "admin@dukeeshop.co.kr", "10% 할인쿠폰이 발행되었습니다.");
		SMSSender obj2 = new SMSSender("생일을 축하합니다.", "고객센터", "02-000-0000", "10% 할인쿠폰이 발행되었습니다.");
		send(obj1, "hatman@yeyeye.com");
		send(obj1, "stickman@hahaha.com");
		send(obj2, "010-0000-0000");
	}

	static void send(MessageSender obj, String recipient) {
		obj.sendMessage(recipient);
	}

}