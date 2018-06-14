package com.kosea.kmove30;

import javax.swing.JTable;

// 테트리스 서버  접속을 위한 로그인 창 실행
public class MainProcess {
	LoginView loginView;
	static JDBC_Manager jdbcManager;
	static JTable table;
	DbLogIn dblogin;
	protected CheckPhoneNo checkPhoneNo;

	public static void main(String[] args) {
		// 메인클래스 실행
		
		MainProcess main = new MainProcess();
		main.loginView = new LoginView(); // 로그인창 보이기
		main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
		
	}

	// 로그인 완료 후 데이터베이스(DbLogIn) 로그인 실행
	public void showFrameTest() {
		loginView.dispose(); // 로그인창닫기
		// DbLogIn 클래스 실행
		DbLogIn.main("테트리스 by JH");
	}
}
