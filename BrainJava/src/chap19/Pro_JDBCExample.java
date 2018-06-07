package chap19;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Pro_JDBCExample {
	public static void main(String args[]) throws ClassNotFoundException {

		String driver 	= null;
		String url 		= null;
		String username = null;
		String password = null;

		try {
			// 프로퍼티 파일 위치
			String propFile = "db.properties";
			
			// 프로퍼티 객체 생성
			Properties props = new Properties();
			
			// 프로퍼티 파일 스트림에 담기
			FileInputStream fis = new FileInputStream(propFile);
			
			// 프로퍼티 파일 로딩
			props.load(new java.io.BufferedInputStream(fis));
			
			// 항목 읽어와서 String 속성에  대입하기
			driver 		= props.getProperty("jdbc.driver");
			url 		= props.getProperty("jdbc.url");
			username 	= props.getProperty("jdbc.username");
			password 	= props.getProperty("jdbc.password");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 1단계: DB 연결을 위한 커넥션 인터페이스
		Connection conn = null;
		
		try {
			// 2단계: JDBC드라이버를 로드한다.
			if (driver != null) {
			    Class.forName(driver) ;
			}
			// 3단계: 드라이버매니져 클래스는 getConnection메소드로 DB를 연결한다.
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB 접속.");

			// 4단계: DB연결을 종료한다.
			conn.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
