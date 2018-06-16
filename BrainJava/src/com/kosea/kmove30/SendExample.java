package com.kosea.kmove30;

import java.util.Random;

public class SendExample {

	public static Random key = new Random();
	public static int result = key.nextInt(10000) + 1000;
	public static String code;

	public static void main(String[] args) {

		/*
		 * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
		 */

		/*
		 * String api_key = "NCSDLAGWUTCTIGX7"; String api_secret =
		 * "02PBJXQ4BULD9EETUNQRDSFCFV8A6UZU"; Coolsms coolsms = new Coolsms(api_key,
		 * api_secret);
		 */

		if (result > 10000) {
			result = result - 1000;
		}
		code = Integer.toString(result);
		System.out.println(code);

		/*
		 * HashMap<String, String> set = new HashMap<String, String>(); set.put("from",
		 * "01058099521"); // 발신번호 set.put("to", "01058099521"); // 수신번호 set.put("text",
		 * key.toString()); // 문자내용 set.put("type", "sms"); // 문자 타입
		 * 
		 * JSONObject result1 = coolsms.send(set); // 보내기&전송결과받기 if
		 * (Boolean.valueOf((boolean) result1.get("status")) == true) { // 메시지 보내기 성공 및
		 * 전송결과 출력 System.out.println("성공");
		 * System.out.println(result1.get("group_id")); // 그룹아이디
		 * System.out.println(result1.get("result_code")); // 결과코드
		 * System.out.println(result1.get("result_message")); // 결과 메시지
		 * System.out.println(result1.get("success_count")); // 메시지아이디
		 * System.out.println(result1.get("error_count")); // 여러개 보낼시 오류난 메시지 수 } else {
		 * // 메시지 보내기 실패 System.out.println("실패");
		 * System.out.println(result1.get("code")); // REST API 에러코드
		 * System.out.println(result1.get("message")); // 에러메시지 }
		 */
	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		SendExample.code = code;
	}
	
}