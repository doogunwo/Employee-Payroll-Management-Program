package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	public Connection conn; // java.sql.Connection
	public Statement stmt = null;
	//database-1.cw1xm5bd9u21.ap-northeast-2.rds.amazonaws.com
	//admin
	//060100eh
	
	String str1="database-1.cw1xm5bd9u21.ap-northeast-2.rds.amazonaws.com";//url
	String str2="admin";//도메인
	String str3="060100eh";//암호
	
	// str1 =  "jdbc:mysql://"+str1+":3306/sys
	String url = "jdbc:mysql://database-1.cw1xm5bd9u21.ap-northeast-2.rds.amazonaws.com:3306/sys";
	public DataBase(){
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, str2,str3);
			
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
		}
		catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
	}
	public ResultSet executeQurey(String sql) {
		//SQL문 실행하기 위한 메소드 - Parameter : String객체로 만든 SQL문
		//실행결과는 ResultSet으로 반환
		
		ResultSet src = null;
		try {
			src = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("SQL 실행 에러");
			return null;
		}
		
		return src;
	}
	public Connection getConnection() {
		//PreparedStatement이용해 SQL 작성할 경우 Connection 객체가 필요해 만든 메소드
		
		if(conn!=null) {
			return conn;
		}else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		DataBase db1 = new DataBase();
		
	}
}


