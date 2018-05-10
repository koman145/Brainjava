package com.malldb;

// 패키지
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class JDBCmalldbExample1 {
    public static void main(String args[]) {
    	
    	// 1단계: DB 연결을 위한 커넥션 인터페이스 
    	Connection conn = null;
        //Statement 인터페이스 sql을 실행
        Statement stmt=null;        
        //ResultSet 인터페이스 sql 결과를 저장
        ResultSet rs=null;
        
        // try ~ catch 문에서 DB연결중에 예외가 발생하는지를 검사.
    	try {
    		
    		String a = null;
    		
//    		String Jumincd 	= null;
//          String PName 	= null;
//          String gender 	= null;
//          int age 		= 0;
    		
    		
        	// 2단계: JDBC드라이버를 로드한다.
            Class.forName("com.mysql.jdbc.Driver");
            // 3단계: 드라이버매니져 클래스는 getConnection메소드로 DB를 연결한다.
            conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/malldb", "root", "12345");
            System.out.println("데이터베이스에 접속했습니다.");            
               
            //malldb 접속정보 -> show processlist;
            //select now();
            
            //Connection객체가 Statement객체를 생성
            stmt=conn.createStatement();
                               
            //DML sql쿼리 실행후 결과를 저장            
            rs=stmt.executeQuery("select now()");
            
            System.out.println("    날짜      시간");
            
            while(rs.next()) { //레코드(데이터)가 있으면 rs를 레코드의 첫줄로 이동한다.
                
                a = rs.getString(1);	
                System.out.println(a);
                }
            
            
            
            
            // 4단계: DB연결을 종료한다.
            conn.close();
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("해당 클래스를 찾을 수 없습니다." + 
                               cnfe.getMessage());
        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}