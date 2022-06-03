package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import Object.User;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import DB.DataBase;
public class Log extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JTextField PW;
	private boolean my=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log frame = new Log();
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
	public Log() {
		DataBase db = new DataBase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ID = new JTextField();
		
		ID.setToolTipText("");
		ID.setBounds(143, 82, 244, 31);
		contentPane.add(ID);
		ID.setColumns(10);
		
		PW = new JTextField();
		
		PW.setToolTipText("");
		PW.setColumns(10);
		PW.setBounds(143, 136, 244, 31);
		contentPane.add(PW);
		
		JButton BTN = new JButton("로그인");
		BTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				User us1 = new User();
				try {
					String str = "select 사원번호,비밀번호,직급 from 로그인정보";
					ResultSet src =db.executeQurey(str);
					while(src.next()) {
						String input_id = ID.getText();
						String input_pw = PW.getText();
						
						if(input_id==src.getString("사원번호") && input_pw==src.getString("비밀번호")) {
							my= true;
						}
						if(my=true ) {
							if(src.getString("직급")=="관리") {
								Management_Main m1 = new  Management_Main(input_id);
								m1.setVisible(true);
								break;
							}
							else {
								Management_Main m1 = new  Management_Main(input_id);
								m1.setVisible(true);
								break;
							}
						}
						
						
						
					}
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		BTN.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		BTN.setForeground(Color.BLACK);
		BTN.setBounds(143, 193, 244, 31);
		contentPane.add(BTN);
		
		JLabel lblNewLabel = new JLabel("급여/사원 관리 시스템");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel.setBounds(176, 10, 189, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("사원번호");
		lblNewLabel_1.setBounds(74, 90, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(74, 144, 57, 15);
		contentPane.add(lblNewLabel_2);
	}
}
