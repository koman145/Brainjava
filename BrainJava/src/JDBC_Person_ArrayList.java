import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


class Person {
	
	String Jumincd	= null;
	String PName 	= null;
	String gender 	= null;
	int age 		= 0;
	
	public Person()	//기본 생성자 () 안에 아무것도 안들어감
	{
		this.Jumincd 	= "주민번호 입력 누락";
		this.PName 		= "이름 입력 누락";
		this.gender 	= "성별 입력 누락";
		this.age 		= 0;
	}
	
	public String getJumincd() {
		return Jumincd;
	}
	public void setJumincd(String jumincd) { // "800511-1574310" 대입
		Jumincd = jumincd;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}

public class JDBC_Person_ArrayList {
    public static void main(String args[]) {
    	    	
    	ArrayList<Person> listPerson = new ArrayList<Person>(3);
    	
    	
   	    	  
    	  //=============================== JDBC 연결문 시작 ================================
          //1단계 :DB 연결을 위한 커넥션 인터페이스
           Connection conn = null;
           
           //Statement 인터페이스 sql을 실행
           Statement stmt=null;
           
           //ResultSet 인터페이스  sql 결과를 저장
           ResultSet rs=null;
           
           //try ~catch 문에서 DB연결중에 예외가 발생하는지를 검사
           try {
        	   
        	   /*
        	   String Jumincd 	= null;
               String PName 	= null;
               String gender 	= null;
               int age 			= 0;
               */
               
              //2단계: JDBC드라이버를 로드한다
               Class.forName("com.mysql.jdbc.Driver");
               //3단계: 드라이버매니져 클레스는 getConnection메소드로 DB를 연결한다
               conn = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/mysql", "root", "12345");
               System.out.println("데이터베이스에 접속했습니다.");
               
               //Connection객체가 Statement객체를 생성
               stmt=conn.createStatement();
               //DML sql쿼리 실행후 결과를 저장
               rs=stmt.executeQuery("SELECT Jumincd, PName, gender, age FROM Person");
               
               System.out.println("   주민번호     이름  성별  나이");
                    
               while(rs.next()) {
            	   //Person person;
            	   Person person = new Person();
            	   
            	   person.setJumincd(rs.getString(1)); //rs.getString(1)은 DB에서 테이블의 속성을 입력받기 위한것
            	   person.setPName(rs.getString(2));
            	   person.setGender(rs.getString(3));
            	   person.setAge(rs.getInt(4));
            	   
            	   listPerson.add(person);
            	  
               }
               
               
               
               //4단계: DB연결을 종료한다
               conn.close();
               System.out.println("--------------------------------------");
               
               for(int i = 0; i < listPerson.size(); i++) {
            	   System.out.print(listPerson.get(i).getJumincd() + "  ");
            	   System.out.print(listPerson.get(i).getPName() + "  ");
            	   System.out.print(listPerson.get(i).getGender() + "    ");
            	   System.out.print(listPerson.get(i).getAge() + " ");
            	   System.out.println(" ");
            	   
               }

              
           }
           catch (ClassNotFoundException cnfe) {
               System.out.println("해당 클래스를 찾을 수 없습니다." + 
                                  cnfe.getMessage());
           }
           catch (SQLException se) {
               System.out.println(se.getMessage());
           }
           
         //=============================== JDBC 연결문 종료 ================================
    	   
    	}

}