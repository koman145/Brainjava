package com.kosea.kmove30;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class CheckPhoneNo extends JFrame {

	JDBC_Manager jdbcManager;
	JTable table;
	AddLogIn addLogin;
//	private static MainProcess main;
	private JButton btnLogin;
	private JButton btnInit;
	private static JTextField phoneNoText1, phoneNoText2, phoneNoText3;
//	private static boolean bLoginCheck;
	public static String id = null;
	public static String pw = null;

	public static void main(String[] args) {

		new CheckPhoneNo();
	}

	public CheckPhoneNo() {
		// setting
		setTitle("휴대폰  인증");
		setSize(380, 500);
		setResizable(false);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(255, 255, 255));
		placeCheckPanel(panel);

		// add
		add(panel);

		// visiible
		setVisible(true);
	}

	public void placeCheckPanel(JPanel panel) {

		// 프레임 짜기
		panel.setLayout(null);
		
		JLabel title = new JLabel("Tetris");
		title.setFont(new Font("헤드라인", Font.BOLD, 50));
		title.setForeground(java.awt.Color.red);	//글자색 변경
		title.setBounds(5, 5, 350, 60);
		panel.add(title);
		
		
		JLabel titleLabel = new JLabel("  휴대폰 본인 인증");
		titleLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		titleLabel.setForeground(java.awt.Color.white);	//글자색 변경
		titleLabel.setOpaque(true);	//배경색 설정 가능하도록 변경
		titleLabel.setBackground(java.awt.Color.darkGray);	//배경색 변경
		titleLabel.setBounds(10, 60, 350, 40);
		panel.add(titleLabel);
		
		JLabel line = new JLabel("-------------------------------------------------");
		line.setFont(new Font("Serif", Font.BOLD, 20));
		line.setBounds(10, 150, 350, 25);
		panel.add(line);
		
		
		
		
		
		JLabel phoneNoLabel = new JLabel("휴대폰 번호 ");
		phoneNoLabel.setFont(new Font("굴림체", Font.BOLD, 15));
		phoneNoLabel.setBounds(20, 190, 100, 25);
		panel.add(phoneNoLabel);

		phoneNoText1 = new JTextField(20);
		phoneNoText1.setBounds(150, 190, 50, 25);
		panel.add(phoneNoText1);
		
		JLabel phoneLabel1 = new JLabel(" - ");
		phoneLabel1.setBounds(200, 190, 10, 25);
		panel.add(phoneLabel1);
		
		phoneNoText2 = new JTextField(20);
		phoneNoText2.setBounds(210, 190, 50, 25);
		panel.add(phoneNoText2);
		
		JLabel phoneLabel2 = new JLabel(" - ");
		phoneLabel2.setBounds(260, 190, 10, 25);
		panel.add(phoneLabel2);
		
		phoneNoText3 = new JTextField(20);
		phoneNoText3.setBounds(270, 190, 50, 25);
		panel.add(phoneNoText3);


		// 회원가입 버튼을 누르면 회원 아이디, 비밀번호를 추가하는 테이블 실행
		btnInit = new JButton("확인");
		btnInit.setBounds(80, 420, 80, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MemberProc memberproc = new MemberProc();
			}
		});

		// 로그인 버튼 누르면 아이디와 패스워드 비교 시작
		btnLogin = new JButton("닫기");
		btnLogin.setBounds(190, 420, 80, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
	}

	public static void isLoginCheck() {

	}
}