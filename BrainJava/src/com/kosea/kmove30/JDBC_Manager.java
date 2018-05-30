package com.kosea.kmove30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Manager {

	////////// 속성(필드) //////////
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public JDBC_Manager(Connection conn, Statement stmt, ResultSet rs) {
		super();
		this.conn = conn;
		this.stmt = stmt;
		this.rs = rs;
	}

	public JDBC_Manager() {
		System.out.println("JDBC_Manager() 기본 생성자 호출");

	}

	////////// 기능(메소드) //////////
	// DB연결
	public Connection DBConnection(String driver, String url, String user, String pass) throws Exception { // 예외가 일어나는
																											// 것을 잡아준다.
		Class.forName(driver); // "com.mysql.jdbc.Driver"
		conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}

	// query - "SELECT PName, age, gender FROM Person"
	public ResultSet SelectTable(String query) throws Exception {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		return rs;
	}

	// 추가
	// INSERT INTO Person(PName, gender, age) values('을지문덕' , 'M' , 32);
	public void insertTable(String[] arr) throws Exception {
		String name = arr[0];
		String age 	= arr[1];
		String gender = arr[2];
		
		String query = "INSERT INTO person(PName, age, gender) values('" + name + "','" + age + "','" + gender + "')";
		// String sql = "INSERT INTO Person (Jumincd, PName, gender, age)
		// values('8704112011523' , '을지문덕' , 'M' , 32)";
		stmt = conn.createStatement();
		int insertCount = stmt.executeUpdate(query);

		if (insertCount > 0)
			System.out.println(insertCount + "건이 추가 되었습니다.");
	}

	// 삭제
	// deleteName = "delete from person where PName = "홍길동";
	public void deleteTable(String deleteName) throws Exception {
		String query = "delete from person where PName = '" + deleteName + "'";
		// String sql = "delete from Person where PName = '" + args[0] + "'";
		stmt = conn.createStatement();
		int deleteCount = stmt.executeUpdate(query);

		if (deleteCount > 0)
			System.out.println(deleteCount + "건이 삭제 되었습니다.");

		/*
		 * // DB SQL 작업 stmt = conn.createStatement(); String sql =
		 * "delete from Person where PName = '" + args[0] + "'"; //delete from Person
		 * where PName = '홍길동'; int changerecord = stmt.executeUpdate(sql);
		 */

	}

	// DB 연결해제
	public void DBClose() throws Exception {
		conn.close();
	}

}
