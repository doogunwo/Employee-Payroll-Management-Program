import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.DataBase;
import UI.Log;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.util.ArrayList;
import java.awt.ScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;


public class Main1 extends JFrame {

	private JPanel contentPane;
	private JTabbedPane 관리;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main1 frame = new Main1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	DataBase dbConn = new DataBase();
	public Main1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 803);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		관리 = new JTabbedPane(JTabbedPane.LEFT);
		
		관리.setFont(new Font("Arial Black", Font.BOLD, 12));
		관리.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		관리.setBorder(new LineBorder(new Color(0, 0, 0)));
		관리.setToolTipText("");
		관리.setBounds(22, 67, 988, 595);
		contentPane.add(관리);
		
		Panel panel2 = new Panel();
		panel2.setBackground(SystemColor.inactiveCaptionBorder);
		관리.addTab("Member", null, panel2, "");
		panel2.setLayout(null);
		
		Panel Panel_See = new Panel();
		Panel_See.setBackground(SystemColor.inactiveCaptionBorder);
		Panel_See.setBounds(583, 0, 283, 560);
		panel2.add(Panel_See);
		Panel_See.setLayout(null);
		
		Panel sc = new Panel();
		sc.setBackground(Color.WHITE);
		sc.setBounds(10, 10, 574, 550);
		panel2.add(sc);
		
		
		
		/*JTable table = new JTable(new DefaultTableModel(
			new Object[][] {
				{"1351","개발팀","도건우","0103232","팀장","dgw0601@naver.com","근무"},
			},
			new String[] {
					"사원번호","부서명","이름","연락처","직급","이메일","상태"
			}
		));
		*/
		
		JTable table;
		DefaultTableModel tableModel;
		Object[][] data = new Object[0][5]; // 일단 빈 row 값 삽입, 이때 두번째 인덱스 값 9은 9개의 열이 존제한다는 뜻으로 선언
		String[] columnNames = { "사원번호", "부서", "이름", "연락처","직급","이메일"};
		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		
		
		Panel Panel_table = new Panel();
		Panel_table.setBounds(10, 0, 458, 560);
		
		Panel_table.setLayout(null);
		
		try {
			String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원";
			ResultSet src = dbConn.executeQurey(str);
			while (src.next()) {
							
					
				
				Object[] tmp = new Object[7];
				
				tmp[0]  =src.getString("사원번호");
				tmp[1]  =src.getString("부서");
				tmp[2]  =src.getString("이름");
				tmp[3]  =src.getString("연락처");
				tmp[4]  =src.getString("직급");
				tmp[5]  =src.getString("이메일");
				
				
				
				
				tableModel.addRow(tmp);
		
			}
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(table);
		
		
		
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 574, 550);
		sc.add(scrollPane);
		
		
		
		
		
		Panel panel3 = new Panel();
		관리.addTab("Control", null, panel3, null);
		panel3.setLayout(null);
		Panel panel4 = new Panel();
		관리.addTab("Status", null, panel4, null);
		panel4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("급여/사원 관리 시스템");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel.setBounds(23, 10, 189, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("로그인 화면 열기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(224, 34, 246, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Log log1 = new Log();
				log1.setVisible(true);
				
			}
		});
	}
}
