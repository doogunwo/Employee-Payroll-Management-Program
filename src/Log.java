import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class Log extends JFrame {

	private JPanel contentPane;
	private JTextField employee_number;
	private JTextField password;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		employee_number = new JTextField();
		employee_number.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				employee_number.setText("");
			}
		});
		employee_number.setText("사원번호");
		employee_number.setToolTipText("");
		employee_number.setBounds(143, 82, 244, 31);
		contentPane.add(employee_number);
		employee_number.setColumns(10);
		
		password = new JTextField();
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				password.setText("");
			}
		});
		password.setToolTipText("");
		password.setText("비밀번호");
		password.setColumns(10);
		password.setBounds(143, 136, 244, 31);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(143, 193, 244, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("급여/사원 관리 시스템");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel.setBounds(176, 10, 189, 62);
		contentPane.add(lblNewLabel);
	}
}
