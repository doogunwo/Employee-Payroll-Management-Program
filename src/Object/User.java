package Object;

import DB.DataBase;
import UI.Management_Main;

public class User {
	public String id;//사번
	public String pw;//pw
	public String buser;//부서
	public String name;//이름
	public String phone;//연락처
	public String rank;//직급
	public String email;//이메일
	
	public String a1;
	public String a2;
	public String a3;
	public String a4;
	
	public String m1;
	public String m2;
	public String m3;
	public String m4;
	
	public String am;
	public String tt;
	public String ta;
	;
	DataBase db = new DataBase();
	Management_Main MM2 = new Management_Main(id);
	
	public User(String id){
		this.id = id;
		System.out.print(id);
	}
	
	public void a_part() {
		
	}
	public void m_part() {
		
	}
	public void total_part() {
		
	}
	public void getThis() {
		
	}
	
	
}
