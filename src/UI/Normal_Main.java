package UI;




import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class Normal_Main extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tab1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Normal_Main frame = new Normal_Main();
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
	public Normal_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 803);
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
		tab1.setBounds(22, 67, 988, 595);
		contentPane.add(tab1);
		
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
		
		JButton btnNewButton_3 = new JButton("근무시작");
		btnNewButton_3.setBounds(12, 393, 97, 23);
		panel5.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("근무종료");
		btnNewButton_4.setBounds(12, 439, 97, 23);
		panel5.add(btnNewButton_4);
		
		JLabel lblNewLabel_8 = new JLabel("근무시간");
		lblNewLabel_8.setBounds(133, 397, 253, 81);
		panel5.add(lblNewLabel_8);
		
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