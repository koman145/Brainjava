package com.kosea.kmove30.tetrisproject;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

public class SendExample {

	public static Random key; 
	public static int result;
	public static String code;

	public static void main(String[] args) {

		//랜덤한 코드 생성, CheckCodeNo 클래스에서 code값을 써야하므로 code는 main메소드 밖에서 public 으로 정의하지만
		//실제로 코드 자체는 계속해서 재생성(ex. 재전송 실행 등)해야하므로 실제 랜덤한 코드를 생성하는 메소드는 main 메소드 안에 있어야함
		key = new Random(System.currentTimeMillis());
		result = (int) Math.abs(key.nextInt(899999) + 100000);
		code = String.valueOf(result);

		/*
		 * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
		 */
		String api_key = "NCSDLAGWUTCTIGX7";
		String api_secret = "02PBJXQ4BULD9EETUNQRDSFCFV8A6UZU";
		Coolsms coolsms = new Coolsms(api_key, api_secret);

		String phoneNumber;
		phoneNumber = CheckPhoneNo.getpNo();

		System.out.println(code);

		System.out.println(phoneNumber);

		HashMap<String, String> set = new HashMap<String, String>();
		set.put("from", "01058099521"); // 발신번호
		set.put("to", phoneNumber); // 수신번호
		set.put("text", "[Tetris 가입 본인확인]\r 인증번호 " + "[" + code + "] \r" + "를 화면에 입력해 주세요."); // 문자내용
		set.put("type", "sms"); // 문자 타입

		JSONObject result1 = coolsms.send(set); // 보내기&전송결과받기
		if (Boolean.valueOf((boolean) result1.get("status")) == true) { // 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");
			System.out.println(result1.get("group_id")); // 그룹아이디
			System.out.println(result1.get("result_code")); // 결과코드
			System.out.println(result1.get("result_message")); // 결과 메시지
			System.out.println(result1.get("success_count")); // 메시지아이디
			System.out.println(result1.get("error_count")); // 여러개 보낼시 오류난 메시지 수
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			System.out.println(result1.get("code")); // REST API 에러코드
			System.out.println(result1.get("message")); // 에러메시지
		}

	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		SendExample.code = code;
	}

}