
package UI;
//임포트 
import DB.DataBase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import Object.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
public class Management_Main extends JFrame {
	String t="0";
	int t2=0;
	boolean ts = true;
	private JPanel contentPane;
	private JTabbedPane tab1;
	private JTextField tf2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	static String Id=null;
	Thread t1;
	Thread pdis = null;
	DataBase dbConn = new DataBase();
	//디비 클래스 선언.
	private JScrollPane scp;
	
	private JScrollPane sc4_1;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Management_Main frame = new Management_Main(Id);
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
	
	public Management_Main(String n) {
		Id = n;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1799, 803);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tab1 = new JTabbedPane(JTabbedPane.LEFT);
		tab1.setBorder(null);
		
		tab1.setFont(new Font("Arial Black", Font.BOLD, 12));
		tab1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tab1.setToolTipText("");
		tab1.setBounds(22, 67, 1723, 687);
		contentPane.add(tab1);
		
		Panel panel3 = new Panel();
		tab1.addTab("TAB2", null, panel3, null);
		panel3.setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.LIGHT_GRAY);
		searchPanel.setBounds(664, 155, 640, 501);
		panel3.add(searchPanel);
		searchPanel.setLayout(null);
		
		JScrollPane sc3 = new JScrollPane();
		sc3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		sc3.setBounds(0, 0, 641, 501);
		searchPanel.add(sc3);
		
		tf2 = new JTextField();
		tf2.setBounds(664, 124, 157, 21);
		panel3.add(tf2);
		tf2.setColumns(10);
		JCheckBox ck1 = new JCheckBox("사원번호검색모드");
		ck1.setBounds(665, 37, 156, 23);
		panel3.add(ck1);
		
		JCheckBox ck2 = new JCheckBox("이름검색모드");
		ck2.setBounds(665, 66, 156, 23);
		panel3.add(ck2);
		
		JCheckBox ck3 = new JCheckBox("부서검색모드");
		ck3.setBounds(664, 95, 171, 23);
		panel3.add(ck3);
		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTable table;
				DefaultTableModel tableModel;
				Object[][] data = new Object[0][5]; // 일단 빈 row 값 삽입, 이때 두번째 인덱스 값 9은 9개의 열이 존제한다는 뜻으로 선언
				String[] columnNames = { "사원번호", "부서", "이름", "연락처","직급","이메일"};
				tableModel = new DefaultTableModel(data, columnNames);
				table = new JTable(tableModel);
				
				
				if(ck1.isSelected()) {
					//사원번호검색
					try {
						String search = tf2.getText();
						String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 사원번호 like"+"'"+search+'%'+"'";
						ResultSet src = dbConn.executeQurey(str);
						while (src.next()) {
										
							Object[] tmp = new Object[7];
							
							tmp[0]  =src.getString("사원번호");
							tmp[1]  =src.getString("부서");
							tmp[2]  =src.getString("이름");
							tmp[3]  =src.getString("연락처");
							tmp[4]  =src.getString("직급");
							tmp[5]  =src.getString("이메일");
							
							//
						
							tableModel.addRow(tmp);
					
						}
					}
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc3.setLayout(null);
					
					scp = new JScrollPane(table);
					
					scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
					scp.setBounds(0, 0, 641, 532);
					sc3.add(scp);
					//종료 
				
					
				}
				if(ck2.isSelected()) {
					//이름검색
					try {
						String search = tf2.getText();
						String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 이름 like"+"'"+search+'%'+"'";
						ResultSet src = dbConn.executeQurey(str);
						while (src.next()) {
										
							Object[] tmp = new Object[7];
							
							tmp[0]  =src.getString("사원번호");
							tmp[1]  =src.getString("부서");
							tmp[2]  =src.getString("이름");
							tmp[3]  =src.getString("연락처");
							tmp[4]  =src.getString("직급");
							tmp[5]  =src.getString("이메일");
							
							//
						
							tableModel.addRow(tmp);
					
						}
					}
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc3.setLayout(null);
					
					scp = new JScrollPane(table);
					
					scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
					scp.setBounds(0, 0, 641, 532);
					sc3.add(scp);
					
				}
				if(ck3.isSelected()) {
					//부서검색
					try {
						String search = tf2.getText();
						String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 부서 like"+"'"+search+'%'+"'";
						ResultSet src = dbConn.executeQurey(str);
						while (src.next()) {
										
							Object[] tmp = new Object[7];
							
							tmp[0]  =src.getString("사원번호");
							tmp[1]  =src.getString("부서");
							tmp[2]  =src.getString("이름");
							tmp[3]  =src.getString("연락처");
							tmp[4]  =src.getString("직급");
							tmp[5]  =src.getString("이메일");
							
							//
						
							tableModel.addRow(tmp);				
						}
					}
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc3.setLayout(null);
					
					scp = new JScrollPane(table);
					
					scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
					scp.setBounds(0, 0, 641, 532);
					sc3.add(scp);
				}
			}
		});
		
		btnNewButton.setBounds(833, 123, 74, 23);
		panel3.add(btnNewButton);
		
		JPanel sc2 = new JPanel();
		sc2.setBackground(Color.LIGHT_GRAY);
		sc2.setBounds(12, 44, 626, 614);
		panel3.add(sc2);
		sc2.setLayout(null);
		//시작
		
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
			String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 ";
			ResultSet src = dbConn.executeQurey(str);
			while (src.next()) {
							
				Object[] tmp = new Object[7];
				
				tmp[0]  =src.getString("사원번호");
				tmp[1]  =src.getString("부서");
				tmp[2]  =src.getString("이름");
				tmp[3]  =src.getString("연락처");
				tmp[4]  =src.getString("직급");
				tmp[5]  =src.getString("이메일");
				
				//
			
				tableModel.addRow(tmp);
		
			}
		}
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sc2.setLayout(null);
		
		scp = new JScrollPane(table);
		
		scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
		scp.setBounds(0, 0, 622, 614);
		sc2.add(scp);
		//종료 
		
		scp.setBounds(0, 0, 622, 532);
		sc2.add(scp);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(1316, 37, 271, 619);
		panel3.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("등록", null, panel_2, null);
		panel_2.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setText("이름");
		textField_3.setBounds(12, 21, 116, 21);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setText("부서");
		textField_4.setColumns(10);
		textField_4.setBounds(12, 52, 116, 21);
		panel_2.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("연락처");
		textField_5.setColumns(10);
		textField_5.setBounds(12, 83, 116, 21);
		panel_2.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("직급");
		textField_6.setColumns(10);
		textField_6.setBounds(12, 114, 116, 21);
		panel_2.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText("사원번호");
		textField_7.setColumns(10);
		textField_7.setBounds(12, 151, 116, 21);
		panel_2.add(textField_7);
		
		JButton btnNewButton_1 = new JButton("등록");
		btnNewButton_1.setBounds(23, 492, 97, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("사원번호부여");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnNewButton_2.setBounds(140, 150, 116, 23);
		panel_2.add(btnNewButton_2);
		
		textField_8 = new JTextField();
		textField_8.setText("이메일");
		textField_8.setColumns(10);
		textField_8.setBounds(12, 182, 116, 21);
		panel_2.add(textField_8);
		
		textField = new JTextField();
		textField.setText("비밀번호");
		textField.setBounds(12, 213, 116, 21);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("수정", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(0, 0, 266, 590);
		panel_3.add(panel_2_1);
		
		textField_9 = new JTextField();
		textField_9.setText("이름");
		textField_9.setColumns(10);
		textField_9.setBounds(12, 21, 116, 21);
		panel_2_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("부서");
		textField_10.setColumns(10);
		textField_10.setBounds(12, 52, 116, 21);
		panel_2_1.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("연락처");
		textField_11.setColumns(10);
		textField_11.setBounds(12, 83, 116, 21);
		panel_2_1.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setText("직급");
		textField_12.setColumns(10);
		textField_12.setBounds(12, 114, 116, 21);
		panel_2_1.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText("사원번호");
		textField_13.setColumns(10);
		textField_13.setBounds(12, 151, 116, 21);
		panel_2_1.add(textField_13);
		
		JButton btnNewButton_1_1 = new JButton("수정");
		btnNewButton_1_1.setBounds(23, 492, 97, 23);
		panel_2_1.add(btnNewButton_1_1);
		
		textField_14 = new JTextField();
		textField_14.setText("이메일");
		textField_14.setColumns(10);
		textField_14.setBounds(12, 182, 116, 21);
		panel_2_1.add(textField_14);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("삭제", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBounds(0, 0, 266, 590);
		panel_4.add(panel_2_1_1);
		
		textField_15 = new JTextField();
		textField_15.setText("이름");
		textField_15.setColumns(10);
		textField_15.setBounds(12, 21, 116, 21);
		panel_2_1_1.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setText("부서");
		textField_16.setColumns(10);
		textField_16.setBounds(12, 52, 116, 21);
		panel_2_1_1.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setText("연락처");
		textField_17.setColumns(10);
		textField_17.setBounds(12, 83, 116, 21);
		panel_2_1_1.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setText("직급");
		textField_18.setColumns(10);
		textField_18.setBounds(12, 114, 116, 21);
		panel_2_1_1.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.setText("사원번호");
		textField_19.setColumns(10);
		textField_19.setBounds(12, 151, 116, 21);
		panel_2_1_1.add(textField_19);
		
		JButton btnNewButton_1_1_1 = new JButton("삭제");
		btnNewButton_1_1_1.setBounds(23, 492, 97, 23);
		panel_2_1_1.add(btnNewButton_1_1_1);
		
		textField_20 = new JTextField();
		textField_20.setText("이메일");
		textField_20.setColumns(10);
		textField_20.setBounds(12, 182, 116, 21);
		panel_2_1_1.add(textField_20);
		Panel panel4 = new Panel();
		tab1.addTab("TAB3", null, panel4, null);
		panel4.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(37, 10, 790, 642);
		panel4.add(panel_7);
		panel_7.setLayout(null);
		
		
		
		JTable table2;
		
		tableModel = new DefaultTableModel(data, columnNames);
		table2 = new JTable(tableModel);
		
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
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sc2.setLayout(null);
		
		
		JPanel panel_8 = new JPanel();
		
		JLabel no1 = new JLabel("1");
		no1.setBounds(49, 76, 106, 55);
		panel_8.add(no1);
		
		sc4_1 = new JScrollPane(table2);
		sc4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( e.getButton()==1) {
				
					int row=table2.getSelectedRow();
					
					System.out.print(table2.getValueAt(row, 0));
				}
				
			}
		});
		sc4_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		
		
		sc4_1.setBounds(0, 0, 790, 642);
		
		
		panel_7.add(sc4_1);
		
		panel_8.setBounds(858, 10, 482, 366);
		panel4.add(panel_8);
		panel_8.setLayout(null);
		
		
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(858, 404, 483, 244);
		panel4.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("클릭한 사원 출근내역");
		lblNewLabel_5.setBounds(120, 72, 245, 50);
		panel_9.add(lblNewLabel_5);
		
		Panel panel5 = new Panel();
		tab1.addTab("TAB4", null, panel5, null);
		panel5.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 23, 338, 361);
		panel5.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("사원정보");
		lblNewLabel_3.setBounds(126, 110, 125, 94);
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(416, 23, 464, 361);
		panel5.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("이번달 급여 내역");
		lblNewLabel_4.setBounds(193, 177, 116, 113);
		panel_6.add(lblNewLabel_4);
		
		JLabel hh = new JLabel("00");
		hh.setFont(new Font("굴림", Font.PLAIN, 37));
		hh.setBounds(123, 405, 60, 57);
		panel5.add(hh);
		
		JLabel mm = new JLabel("00");
		mm.setFont(new Font("굴림", Font.PLAIN, 37));
		mm.setBounds(179, 405, 60, 57);
		panel5.add(mm);
		
		JLabel ss = new JLabel("00");
		ss.setFont(new Font("굴림", Font.PLAIN, 37));
		ss.setBounds(238, 405, 60, 57);
		panel5.add(ss);
		JButton btnNewButton_3 = new JButton("근무시작");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				btnNewButton_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						t1 = new Thread() {
							public void run() {
								while(true) {
									if(ts==false)break;
									t2= t2+1;
									ss.setText(Integer.toString(t2));
									
									try {
										Thread.sleep(1000);
									}
									catch(InterruptedException ie) {
										
									}
								}
							}
						};
						t1.start();
					}
				});
				
				
			}
		});
		
		btnNewButton_3.setBounds(12, 393, 97, 23);
		panel5.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("근무종료");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ss.setText("00");
				mm.setText("00");
				hh.setText("00");
				
				
				LocalDate now = LocalDate.now();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
				String fmtnow = now.format(fmt);
				String name=null;
				String buser =null;
				ts = false;
				
				try {
				String MI = Id;
				
				String sql1 = "select 이름,부서 from 사원 where 사원번호="+"'"+MI+"'";
				
				ResultSet src = dbConn.executeQurey(sql1);
				
				while(src.next()) {
					name = src.getString("이름");
					buser = src.getString("부서");
				}
				
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				try {
					String sql2 = "insert into 근무기록 values ("+t2+","+"'"+
							Id+"'"+","+"'"+name+"'"+","+"'"+buser+"'"+','+"'"+fmtnow+"'"+")";
					System.out.print(sql2);
					dbConn.stmt.execute(sql2);
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
				
			}
		});
		
		btnNewButton_4.setBounds(12, 439, 97, 23);
		panel5.add(btnNewButton_4);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(418, 409, 462, 171);
		panel5.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("이번달 출근 이력");
		lblNewLabel_9.setBounds(145, 47, 148, 43);
		panel_10.add(lblNewLabel_9);
		
		JLabel lblNewLabel = new JLabel("급여/사원 관리 시스템");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 10, 189, 62);
		contentPane.add(lblNewLabel);
	}
}