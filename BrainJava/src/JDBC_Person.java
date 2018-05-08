import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class JDBC_Person {
    public static void main(String args[]) {
    	
    	
    	Person[] persons = new Person[3];	// 배열객체 (Person 테이블에 cardinality가 3개)
    	
    	
    	
    	for(int i=0; i<persons.length; i++) {
    		persons[i] = new Person();	// 생성자 persons.length 길이만큼 호출하여 
    									// 배열래퍼런스에 대입
    	}
    	
    	
    	
    	/*
    	for(int i=0; i<persons.length; i++) {
    		System.out.println("주민번호" + persons[i].getJumincd() );
    		System.out.println("이름" + persons[i].getPName() );
    		System.out.println("성별" + persons[i].getGender() );
    		System.out.println("나이" + persons[i].getAge() );
    	}
    	*/	
    	
    	
    	
    	
//    	Person person;			//래퍼런스 변수 선언
//    	person = new Person();	//객체생성 - new 연산자(생성자) 이용 ( new 뒤에는 클래스가 온다)
    	// 한줄로 하면  Person person = new Person();
    	
    	
    	/*
    	// person 객체에 멤버필드를 셋팅
    	person.setJumincd("800511-1574310");
    	person.setPName("홍길동");
    	person.setGender("남자");
    	person.setAge(20);

    	int age = person.getAge();
    	System.out.println(age);
    	
    	
    	System.out.println(person.getAge());
    	*/
    	
    	
    	/*
    	System.out.println(person.Jumincd);
    	System.out.println(person.PName);
    	System.out.println(person.gender);
    	System.out.println(person.age);
    	*/
    	
    	
    	  
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
               
               int i = 0;
               
               while(rs.next()) {
            	   
            	   persons[i].setJumincd(rs.getString(1));
            	   persons[i].setPName(rs.getString(2));
            	   persons[i].setGender(rs.getString(3));
            	   persons[i].setAge(rs.getInt(4));   	   
            	   
            	   i++;
            	   
               /*
               Jumincd	 = rs.getString(1);	//rs.getString("Jumincd");
               PName	 = rs.getString(2);	//rs.getString("PName");
               gender	 = rs.getString(3);	//rs.getString("gender");
               age		 = rs.getInt(4);	//rs.getInt("age");
               */
            	   
               //System.out.println(Jumincd + "--" + PName + "--" + gender + "-----" + age);
               }
               
               
               
               //4단계: DB연결을 종료한다
               conn.close();
               
               System.out.println("--------------------------------------");
               
               //System.out.println(Jumincd + "--" + PName + "--" + gender + "-----" + age);
               for(i=0; i<persons.length; i++) {
            	   
           		System.out.println("주민번호" 	+ persons[i].getJumincd() );
           		System.out.println("이름" 		+ persons[i].getPName() );
           		System.out.println("성별" 		+ persons[i].getGender() );
           		System.out.println("나이" 		+ persons[i].getAge() );
           		System.out.println("--------------------------------------");
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