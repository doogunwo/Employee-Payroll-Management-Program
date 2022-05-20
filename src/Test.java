

//https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html
//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=battledocho&logNo=220013094299
//https://www.geeksforgeeks.org/java-swing-jtable/
//https://stackoverflow.com/questions/16295942/java-swing-adding-action-listener-for-exit-on-close

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;



public class Test extends JFrame {

	DataBase dbConn = new DataBase();

	public Test() {
		

		// Main JFrame 설정
		setTitle("JTable 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 컨텐트 팬 설정
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		
		
		// JTable 관련 -NORTH
		JTable table;
		DefaultTableModel tableModel;
		Object[][] data = new Object[0][6]; // 일단 빈 row 값 삽입, 이때 두번째 인덱스 값 9은 9개의 열이 존제한다는 뜻으로 선언
		String[] columnNames = { "사원번호", "부서명", "이름", "연락처","직급","이메일","상태"};
		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		
	
		try {
			String str = "select 사원번호,부서명,이름,연락처,직급,이메일,상태 from 사원정보";
			ResultSet src = dbConn.executeQurey(str);
			while (src.next()) {
							
				//SQL에서 BOOK_PRE 필드 결과값에 따라 맞는 문자열 배정 - 1:보유, 2:미보유			
				Object[] tmp = new Object[7];
				
				tmp[0]  =src.getString("사원번호");
				tmp[1]  =src.getString("부서명");
				tmp[2]  =src.getString("이름");
				tmp[3]  =src.getString("연락처");
				tmp[4]  =src.getString("직급");
				tmp[5]  =src.getString("이메일");
				tmp[6]  =src.getString("상태");
				
				
				
				tableModel.addRow(tmp);
				
				// DB에서 BLOB 자료형으로 저장된 데이터 그림 데이터로 변환
				
				
			}
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(new JScrollPane(table), BorderLayout.NORTH);


		// 화면 표시
		setSize(800, 600);
		setLocationRelativeTo(null); // 화면 정중앙 배치
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test();
	}

}
