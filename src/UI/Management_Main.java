
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
import javax.swing.JOptionPane;

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
	String t = "0";
	int t2 = 0;
	
	int lh = 0;
	int lm = 0;
	int ls = 0;
	int t61 = 0;
	
	JLabel count ;
	JLabel timeTime;
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
	static String Id = null;
	Thread t1;
	Thread pdis = null;
	DataBase dbConn = new DataBase();
	// 디비 클래스 선언.
	private JScrollPane scp;

	private JScrollPane sc4_1;
	private JTextField textField;
	public JTextField logtf1;
	public JTextField logtf2;
	public JTextField logtf3;
	public JTextField logtf4;
	public JTextField logtf5;
	public JTextField logtf6;

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
				String[] columnNames = { "사원번호", "부서", "이름", "연락처", "직급", "이메일" };
				tableModel = new DefaultTableModel(data, columnNames);
				table = new JTable(tableModel);

				if (ck1.isSelected()) {
					// 사원번호검색
					try {
						String search = tf2.getText();
						String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 사원번호 like" + "'" + search + '%' + "'";
						ResultSet src = dbConn.executeQurey(str);
						while (src.next()) {

							Object[] tmp = new Object[7];

							tmp[0] = src.getString("사원번호");
							tmp[1] = src.getString("부서");
							tmp[2] = src.getString("이름");
							tmp[3] = src.getString("연락처");
							tmp[4] = src.getString("직급");
							tmp[5] = src.getString("이메일");

							//

							tableModel.addRow(tmp);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc3.setLayout(null);

					scp = new JScrollPane(table);

					scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null,
							SystemColor.textText));
					scp.setBounds(0, 0, 641, 532);
					sc3.add(scp);
					// 종료

				}
				if (ck2.isSelected()) {
					// 이름검색
					try {
						String search = tf2.getText();
						String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 이름 like" + "'" + search + '%' + "'";
						ResultSet src = dbConn.executeQurey(str);
						while (src.next()) {

							Object[] tmp = new Object[7];

							tmp[0] = src.getString("사원번호");
							tmp[1] = src.getString("부서");
							tmp[2] = src.getString("이름");
							tmp[3] = src.getString("연락처");
							tmp[4] = src.getString("직급");
							tmp[5] = src.getString("이메일");

							//

							tableModel.addRow(tmp);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc3.setLayout(null);

					scp = new JScrollPane(table);

					scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null,
							SystemColor.textText));
					scp.setBounds(0, 0, 641, 532);
					sc3.add(scp);

				}
				if (ck3.isSelected()) {
					// 부서검색
					try {
						String search = tf2.getText();
						String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 부서 like" + "'" + search + '%' + "'";
						ResultSet src = dbConn.executeQurey(str);
						while (src.next()) {

							Object[] tmp = new Object[7];

							tmp[0] = src.getString("사원번호");
							tmp[1] = src.getString("부서");
							tmp[2] = src.getString("이름");
							tmp[3] = src.getString("연락처");
							tmp[4] = src.getString("직급");
							tmp[5] = src.getString("이메일");

							//

							tableModel.addRow(tmp);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc3.setLayout(null);

					scp = new JScrollPane(table);

					scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scp.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null,
							SystemColor.textText));
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
		// 시작

		JTable table;
		DefaultTableModel tableModel;
		Object[][] data = new Object[0][5]; // 일단 빈 row 값 삽입, 이때 두번째 인덱스 값 9은 9개의 열이 존제한다는 뜻으로 선언
		String[] columnNames = { "사원번호", "부서", "이름", "연락처", "직급", "이메일" };
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

				tmp[0] = src.getString("사원번호");
				tmp[1] = src.getString("부서");
				tmp[2] = src.getString("이름");
				tmp[3] = src.getString("연락처");
				tmp[4] = src.getString("직급");
				tmp[5] = src.getString("이메일");

				//

				tableModel.addRow(tmp);

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sc2.setLayout(null);

		scp = new JScrollPane(table);

		scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scp.setViewportBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
		scp.setBounds(0, 0, 622, 614);
		sc2.add(scp);
		// 종료

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

		logtf1 = new JTextField();
		logtf1.setBounds(114, 69, 116, 21);
		logtf1.setColumns(10);

		logtf2 = new JTextField();
		logtf2.setColumns(10);
		logtf2.setBounds(114, 91, 116, 21);

		logtf3 = new JTextField();
		logtf3.setColumns(10);
		logtf3.setBounds(114, 116, 116, 21);

		logtf4 = new JTextField();
		logtf4.setColumns(10);
		logtf4.setBounds(114, 141, 116, 21);

		logtf5 = new JTextField();
		logtf5.setColumns(10);
		logtf5.setBounds(114, 166, 116, 21);

		logtf6 = new JTextField();
		logtf6.setColumns(10);
		logtf6.setBounds(114, 191, 116, 21);

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

				tmp[0] = src.getString("사원번호");
				tmp[1] = src.getString("부서");
				tmp[2] = src.getString("이름");
				tmp[3] = src.getString("연락처");
				tmp[4] = src.getString("직급");
				tmp[5] = src.getString("이메일");

				tableModel.addRow(tmp);

			}
		} catch (SQLException e1) {
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
				if (e.getButton() == 1) {

					int row = table2.getSelectedRow();

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

		JLabel lblNewLabel_1 = new JLabel("로그인된 사원정보");
		lblNewLabel_1.setBounds(22, 10, 116, 49);
		panel_5.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setBounds(22, 69, 57, 15);
		panel_5.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("사원번호");
		lblNewLabel_2_1.setBounds(22, 94, 57, 15);
		panel_5.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("부서");
		lblNewLabel_2_2.setBounds(22, 119, 57, 15);
		panel_5.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("연락처");
		lblNewLabel_2_2_1.setBounds(22, 144, 57, 15);
		panel_5.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_2_1_1 = new JLabel("이메일");
		lblNewLabel_2_2_1_1.setBounds(22, 169, 57, 15);
		panel_5.add(lblNewLabel_2_2_1_1);

		JLabel lblNewLabel_3 = new JLabel("직급");
		lblNewLabel_3.setBounds(22, 194, 57, 15);
		panel_5.add(lblNewLabel_3);

		panel_5.add(logtf1);
		panel_5.add(logtf2);
		panel_5.add(logtf3);
		panel_5.add(logtf4);
		panel_5.add(logtf5);
		panel_5.add(logtf6);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(416, 23, 464, 361);
		panel5.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("나의 급여");
		lblNewLabel_7.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_7.setBounds(12, 10, 137, 30);
		panel_6.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("기본금");
		lblNewLabel_8.setBounds(12, 60, 72, 40);
		panel_6.add(lblNewLabel_8);

		JLabel lblNewLabel_8_1 = new JLabel("식대");
		lblNewLabel_8_1.setBounds(12, 110, 72, 40);
		panel_6.add(lblNewLabel_8_1);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(12, 168, 57, 15);
		panel_6.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("지급");
		lblNewLabel_11.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_11.setBounds(79, 39, 62, 30);
		panel_6.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("수당");
		lblNewLabel_12.setBounds(12, 182, 57, 15);
		panel_6.add(lblNewLabel_12);

		JLabel lblNewLabel_11_1 = new JLabel("공제");
		lblNewLabel_11_1.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_11_1.setBounds(292, 39, 62, 30);
		panel_6.add(lblNewLabel_11_1);

		JLabel lblNewLabel_13 = new JLabel("소득세");
		lblNewLabel_13.setBounds(200, 73, 57, 15);
		panel_6.add(lblNewLabel_13);

		JLabel lblNewLabel_13_1 = new JLabel("건강보험");
		lblNewLabel_13_1.setBounds(200, 123, 57, 15);
		panel_6.add(lblNewLabel_13_1);

		JLabel lblNewLabel_14 = new JLabel("국민연금");
		lblNewLabel_14.setBounds(200, 182, 57, 15);
		panel_6.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("지급액");
		lblNewLabel_15.setBounds(12, 245, 57, 15);
		panel_6.add(lblNewLabel_15);

		JLabel lblNewLabel_15_1 = new JLabel("공제액");
		lblNewLabel_15_1.setBounds(200, 245, 57, 15);
		panel_6.add(lblNewLabel_15_1);

		JLabel lblNewLabel_16 = new JLabel("실수령 액");
		lblNewLabel_16.setBounds(200, 294, 57, 15);
		panel_6.add(lblNewLabel_16);

		JLabel ta_1 = new JLabel("1200000");
		ta_1.setBounds(79, 73, 62, 22);
		panel_6.add(ta_1);

		JLabel ta_2 = new JLabel("130000");
		ta_2.setBounds(79, 123, 44, 22);
		panel_6.add(ta_2);

		JLabel ta_3 = new JLabel("0");
		ta_3.setBounds(81, 182, 44, 22);
		panel_6.add(ta_3);

		JLabel ta_4 = new JLabel("0");
		ta_4.setBounds(79, 245, 44, 22);
		panel_6.add(ta_4);

		JLabel ta_5 = new JLabel("0");
		ta_5.setBounds(292, 73, 44, 22);
		panel_6.add(ta_5);

		JLabel ta_6 = new JLabel("137000");
		ta_6.setBounds(292, 123, 44, 22);
		panel_6.add(ta_6);

		JLabel ta_7 = new JLabel("177500");
		ta_7.setBounds(292, 182, 44, 22);
		panel_6.add(ta_7);

		JLabel ta_8 = new JLabel("0");
		ta_8.setBounds(292, 245, 44, 22);
		panel_6.add(ta_8);

		JLabel ta_9 = new JLabel("0");
		ta_9.setBounds(292, 294, 44, 22);
		panel_6.add(ta_9);

		JLabel hh = new JLabel("0");
		hh.setFont(new Font("굴림", Font.PLAIN, 37));
		hh.setBounds(121, 405, 49, 57);
		panel5.add(hh);

		JLabel mm = new JLabel("0");
		mm.setFont(new Font("굴림", Font.PLAIN, 37));
		mm.setBounds(209, 405, 32, 57);
		panel5.add(mm);

		JLabel ss = new JLabel("0");
		ss.setFont(new Font("굴림", Font.PLAIN, 37));
		ss.setBounds(315, 405, 60, 57);
		panel5.add(ss);
		JButton btnNewButton_3 = new JButton("근무시작");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnNewButton_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						
						t1 = new Thread() {
							public void run() {
								while (true) {
									if (ts == false)
										break;
									t2 = t2 + 1;

									t61 = t61 + 1;
									ss.setText(Integer.toString(t61));

									if (t2 % 60 == 0) {
										lm = lm + 1;
										mm.setText(Integer.toString(lm));
										t61 = t61 - (60);
									}
									if (t2 % 3600 == 0) {
										lh = lh + 1;
										hh.setText(Integer.toString(lh));
										lm = lm - (60);
									}

									try {
										Thread.sleep(1000);
									} catch (InterruptedException ie) {

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

				

				LocalDate now = LocalDate.now();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
				String fmtnow = now.format(fmt);
				String name = null;
				String buser = null;
				ts = false;

				try {
					String MI = Id;

					String sql1 = "select 이름,부서 from 사원 where 사원번호=" + "'" + MI + "'";

					ResultSet src = dbConn.executeQurey(sql1);

					while (src.next()) {
						name = src.getString("이름");
						buser = src.getString("부서");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					String sql2 = "insert into 근무기록 values (" + t2 + "," + "'" + Id + "'" + "," + "'" + name + "'" + ","
							+ "'" + buser + "'" + ',' + "'" + fmtnow + "'" + ")";
					System.out.print(sql2);
					dbConn.stmt.execute(sql2);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				dispose();
			
			}
		});

		btnNewButton_4.setBounds(12, 477, 97, 23);
		panel5.add(btnNewButton_4);

		JPanel panel_10 = new JPanel();
		panel_10.setBounds(418, 409, 462, 171);
		panel5.add(panel_10);
		panel_10.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("총 출근 횟수");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_4.setBounds(12, 10, 95, 30);
		panel_10.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("총 근무 시간");
		lblNewLabel_4_1.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_4_1.setBounds(12, 50, 95, 30);
		panel_10.add(lblNewLabel_4_1);

		 count = new JLabel("0");
		count.setBounds(142, 14, 44, 22);
		panel_10.add(count);

		timeTime = new JLabel("0");
		timeTime.setBounds(142, 54, 49, 22);
		panel_10.add(timeTime);

		JLabel lblNewLabel_6_1_1 = new JLabel("초(s)");
		lblNewLabel_6_1_1.setBounds(233, 54, 49, 22);
		panel_10.add(lblNewLabel_6_1_1);

		JLabel lblNewLabel_6_1_1_1 = new JLabel("번");
		lblNewLabel_6_1_1_1.setBounds(233, 14, 49, 22);
		panel_10.add(lblNewLabel_6_1_1_1);

		JLabel hh_1 = new JLabel(":");
		hh_1.setFont(new Font("굴림", Font.PLAIN, 37));
		hh_1.setBounds(172, 405, 25, 57);
		panel5.add(hh_1);

		JLabel hh_2 = new JLabel(":");
		hh_2.setFont(new Font("굴림", Font.PLAIN, 37));
		hh_2.setBounds(266, 405, 25, 57);
		panel5.add(hh_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(892, 23, 315, 57);
		panel5.add(lblNewLabel_6);

		JLabel lblNewLabel = new JLabel("급여/사원 관리 시스템");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 10, 189, 62);
		contentPane.add(lblNewLabel);
		//___________________________________________________________________
		memberPart();
		//___________________________________________________________________
		record();
		
		
	}
	private void memberPart() {
		String str = "select 사원번호,이름,부서,연락처,직급,이메일 from 사원 where 사원번호=" + "'" + Id + "'";
		ResultSet src = dbConn.executeQurey(str);
		try {
			while (src.next()) {
				logtf1.setText(src.getString("사원번호"));
				logtf2.setText(src.getString("이름"));
				logtf3.setText(src.getString("부서"));
				logtf4.setText(src.getString("연락처"));
				logtf5.setText(src.getString("직급"));
				logtf6.setText(src.getString("이메일"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void aPart() {
		
	}
	private void mPart() {
		
	}
	private void amPaer() {
		
	}
	private void record() {
		String str = "select 근무시간 from 근무기록 where 사원번호=" + "'" + Id + "'";
		int cnt=0;
		int totalTime =0;
		ResultSet src = dbConn.executeQurey(str);
		try {
			while(src.next()) {
				totalTime = totalTime + src.getInt("근무시간");
				cnt =cnt+1;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		timeTime.setText(Integer.toString(totalTime));
		count.setText(Integer.toString(cnt));
	}
}