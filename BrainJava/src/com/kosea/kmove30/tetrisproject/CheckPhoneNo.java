package com.kosea.kmove30.tetrisproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.kosea.kmove30.JDBC_Manager;

public class CheckPhoneNo extends JFrame {

	public static String pNo1, pNo2, pNo3;

	JDBC_Manager jdbcManager;
	JTable table;
	AddLogIn addLogin;
	// private static MainProcess main;
	private RoundedButton btnLogin;
	private RoundedButton btnInit;
	private static JTextField phoneNoText1, phoneNoText2, phoneNoText3;
	// private static boolean bLoginCheck;
	public static String id = null;
	public static String pw = null;
	public static String pNo = null;

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

		JPanel guideLine = new JPanel();
		guideLine.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 5)));
		guideLine.setLocation(10, 205);
		guideLine.setSize(350, 40);
		panel.add(guideLine);

		// 프레임 짜기
		panel.setLayout(null);

		JLabel title = new JLabel("Tetris");
		title.setFont(new Font("헤드라인", Font.BOLD, 50));
		title.setForeground(java.awt.Color.red); // 글자색 변경
		title.setBounds(5, 5, 350, 60);
		panel.add(title);

		JLabel titleLabel = new JLabel("  휴대폰 본인 인증");
		titleLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		titleLabel.setForeground(java.awt.Color.white); // 글자색 변경
		titleLabel.setOpaque(true); // 배경색 설정 가능하도록 변경
		titleLabel.setBackground(java.awt.Color.darkGray); // 배경색 변경
		titleLabel.setBounds(10, 60, 350, 40);
		panel.add(titleLabel);

		JLabel guidePoint = new JLabel("※");
		guidePoint.setForeground(java.awt.Color.black); // 글자색 변경
		guidePoint.setBounds(20, 90, 350, 60);
		panel.add(guidePoint);

		JLabel guideLabel1 = new JLabel("고객님의 개인정보");
		guideLabel1.setForeground(java.awt.Color.red); // 글자색 변경
		guideLabel1.setFont(new Font("굴림체", Font.BOLD, 15));
		guideLabel1.setBounds(40, 90, 350, 60);
		panel.add(guideLabel1);

		JLabel guideLabel2 = new JLabel("는 항상 암호화 되어 처리되고,");
		guideLabel2.setForeground(java.awt.Color.black); // 글자색 변경
		guideLabel2.setBounds(170, 90, 350, 60);
		panel.add(guideLabel2);

		JLabel guideLabel3 = new JLabel("본인인증으로만 사용하며, 보관하지 않습니다.");
		guideLabel3.setForeground(java.awt.Color.black); // 글자색 변경
		guideLabel3.setBounds(40, 110, 350, 60);
		panel.add(guideLabel3);

		JLabel line = new JLabel("-------------------------------------------------");
		line.setFont(new Font("Serif", Font.BOLD, 20));
		line.setBounds(10, 180, 350, 25);
		panel.add(line);

		JLabel phoneNoLabel = new JLabel("○ 휴대폰 번호      ");
		phoneNoLabel.setFont(new Font("굴림체", Font.BOLD, 12));
		phoneNoLabel.setBounds(10, 25, 20, 10);
		guideLine.add(phoneNoLabel);

		phoneNoText1 = new JTextField(4);
		phoneNoText1.setBounds(30, 25, 0, 0);
		guideLine.add(phoneNoText1);
		phoneNoText1.setDocument(new JTextFieldLimit(3));

		JLabel phoneLabel1 = new JLabel(" - ");
		phoneLabel1.setBounds(45, 25, 10, 10);
		guideLine.add(phoneLabel1);

		phoneNoText2 = new JTextField(4);
		phoneNoText2.setBounds(60, 25, 4, 1);
		guideLine.add(phoneNoText2);
		phoneNoText2.setDocument(new JTextFieldLimit(4));

		JLabel phoneLabel2 = new JLabel(" - ");
		phoneLabel2.setBounds(75, 25, 10, 10);
		guideLine.add(phoneLabel2);

		phoneNoText3 = new JTextField(4);
		phoneNoText3.setBounds(100, 25, 4, 1);
		guideLine.add(phoneNoText3);
		phoneNoText3.setDocument(new JTextFieldLimit(4));

		JLabel Label1 = new JLabel("※ 본인 명의의 휴대폰 정보를 정확히 입력하여 주시기");
		Label1.setForeground(java.awt.Color.black); // 글자색 변경
		Label1.setBounds(20, 250, 380, 60);
		panel.add(Label1);

		JLabel Label2 = new JLabel("바랍니다.");
		Label2.setForeground(java.awt.Color.black); // 글자색 변경
		Label2.setBounds(34, 265, 380, 60);
		panel.add(Label2);

		JLabel Label3 = new JLabel("※ 본인인증은 무료 서비스로 결제 및 과금이 발생하지 ");
		Label3.setForeground(java.awt.Color.black); // 글자색 변경
		Label3.setBounds(20, 285, 380, 60);
		panel.add(Label3);

		JLabel Label4 = new JLabel("않습니다.");
		Label4.setForeground(java.awt.Color.black); // 글자색 변경
		Label4.setBounds(34, 300, 380, 60);
		panel.add(Label4);

		// 회원가입 버튼을 누르면 회원 아이디, 비밀번호를 추가하는 테이블 실행
		btnInit = new RoundedButton("확인");
		btnInit.setBackground(Color.LIGHT_GRAY);
		btnInit.setBounds(80, 420, 80, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int confirm = JOptionPane.showConfirmDialog(null, "휴대폰 번호가  맞습니까?", "휴대폰 번호 확인", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) { //아니오
					return;
				}
				if (confirm == 0) { //예
					pNo1 = phoneNoText1.getText();
					pNo2 = phoneNoText2.getText();
					pNo3 = phoneNoText3.getText();
					pNo = pNo1 + pNo2 + pNo3;
					System.out.println(pNo);
					dispose();
					SendExample.main(null);
					CheckCodeNo checkCodeNo = new CheckCodeNo();
					
				}
			}
		});

		// 로그인 버튼 누르면 아이디와 패스워드 비교 시작
		btnLogin = new RoundedButton("닫기");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(190, 420, 80, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "닫으시겠습니까?", "닫기 확인", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오를 선택하면 메인창 띄우기
					return;
				}
				if (confirm == 0) {// 예를 선택하면 게임 재시작
					dispose();
					MainProcess.main(null);
				}
			}
		});
	}

	public static String getpNo1() {
		return pNo1;
	}

	public static void setpNo1(String pNo1) {
		CheckPhoneNo.pNo1 = pNo1;
	}

	public static String getpNo2() {
		return pNo2;
	}

	public static void setpNo2(String pNo2) {
		CheckPhoneNo.pNo2 = pNo2;
	}

	public static String getpNo3() {
		return pNo3;
	}

	public static void setpNo3(String pNo3) {
		CheckPhoneNo.pNo3 = pNo3;
	}

	public static String getpNo() {
		return pNo;
	}

	public static void setpNo(String pNo) {
		CheckPhoneNo.pNo = pNo;
	}
	
	
	
}