package com.kosea.kmove30;

import java.awt.Color;
import java.awt.Dimension;
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

public class CheckCodeNo extends JFrame {

	JPanel guideLine = new JPanel();
	public static String pNo1, pNo2, pNo3;

	JDBC_Manager jdbcManager;
	JTable table;
	AddLogIn addLogin;
	// private static MainProcess main;
	private JButton cancleLogin;
	private JButton checkbtn;
	private JButton btnRe, btnReCount;
	private static JTextField CodeNoText;
	// private static boolean bLoginCheck;
	public static String id = null;
	public static String pw = null;
	public static String codeCheck; // = SendExample.getCode();
	public static String code;
	public static CodeTimer codeTimer = new CodeTimer();
	public static JLabel countLabel;
	public static Thread t2;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		new CheckCodeNo();
		// 카운트 다운 시작
	}

	public CheckCodeNo() {
		CodeTimer.main(null);

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

		int i = CodeTimer.setI(10);

		codeCheck = SendExample.getCode();
		
		guideLine.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 5)));
		guideLine.setLocation(5, 205);
		guideLine.setSize(360, 90);
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

		JLabel guideLabel4 = new JLabel("인증 요청하신 휴대폰 번호");
		guideLabel4.setForeground(java.awt.Color.red); // 글자색 변경
		guideLabel4.setFont(new Font("굴림체", Font.BOLD, 12));
		guideLabel4.setBounds(25, 140, 350, 60);
		panel.add(guideLabel4);

		JLabel pNoLabel1 = new JLabel("mm");
		pNoLabel1.setText(CheckPhoneNo.getpNo1());
		pNoLabel1.setForeground(java.awt.Color.black); // 글자색 변경
		pNoLabel1.setFont(new Font("굴림체", Font.BOLD, 12));
		pNoLabel1.setBounds(220, 140, 350, 60);
		panel.add(pNoLabel1);

		JLabel point1 = new JLabel("-");
		point1.setBounds(245, 140, 350, 60);
		panel.add(point1);

		JLabel pNoLabel2 = new JLabel("mm");
		pNoLabel2.setText(CheckPhoneNo.getpNo2());
		pNoLabel2.setForeground(java.awt.Color.black); // 글자색 변경
		pNoLabel2.setFont(new Font("굴림체", Font.BOLD, 12));
		pNoLabel2.setBounds(250, 140, 350, 60);
		panel.add(pNoLabel2);

		JLabel point2 = new JLabel("-");
		point2.setBounds(280, 140, 350, 60);
		panel.add(point2);

		JLabel pNoLabel3 = new JLabel("mm");
		pNoLabel3.setText(CheckPhoneNo.getpNo3());
		pNoLabel3.setForeground(java.awt.Color.black); // 글자색 변경
		pNoLabel3.setFont(new Font("굴림체", Font.BOLD, 12));
		pNoLabel3.setBounds(285, 140, 350, 60);
		panel.add(pNoLabel3);

		JLabel line = new JLabel("-------------------------------------------------");
		line.setFont(new Font("Serif", Font.BOLD, 20));
		line.setBounds(10, 180, 350, 25);
		panel.add(line);

		JLabel checkLabel = new JLabel("※ 받으신 인증번호 6자리를 입력하신 후 확인을 눌러주세요.");
		checkLabel.setForeground(java.awt.Color.black); // 글자색 변경
		checkLabel.setBounds(20, 250, 380, 60);
		guideLine.add(checkLabel);

		JLabel phoneNoLabel = new JLabel("○ 인증 번호 ");
		phoneNoLabel.setFont(new Font("굴림체", Font.BOLD, 12));
		phoneNoLabel.setBounds(10, 25, 20, 10);
		guideLine.add(phoneNoLabel);

		CodeNoText = new JTextField(10);
		CodeNoText.setBounds(30, 25, 0, 0);
		guideLine.add(CodeNoText);
		// CodeNoText.getText().toString();

		btnRe = new JButton("재전송");
		btnRe.setBounds(80, 420, 80, 25);
		guideLine.add(btnRe);
		btnRe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int confirm = JOptionPane.showConfirmDialog(null, "재전송 하시겠습니까?", "재전송 확인", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오
					return;
				}
				if (confirm == 0) {// 예
					dispose();
					codeCheck = SendExample.getCode();
					CodeTimer.setI(180);
					SendExample.main(null); // 인증번호 재전송
					CheckCodeNo checkCodeNo = new CheckCodeNo();
				}
			}
		});

		JLabel countLabel1 = new JLabel("( 남은시간");
		countLabel1.setFont(new Font("굴림체", Font.BOLD, 12));
		guideLine.add(countLabel1);

		countLabel = CodeTimer.countLabel; // 카운트다운 구현 03:00
		guideLine.add(countLabel);

		JLabel countLabel2 = new JLabel(")");
		countLabel2.setFont(new Font("굴림체", Font.BOLD, 12));
		guideLine.add(countLabel2);

		btnReCount = new JButton("시간연장");
		btnReCount.setPreferredSize(new Dimension(87, 20));
		btnReCount.setFont(new Font("굴림체", Font.BOLD, 12));
		btnReCount.setForeground(Color.white);
		;
		btnReCount.setBorderPainted(false);
		btnReCount.setBackground(new Color(255, 102, 0));
		guideLine.add(btnReCount);
		btnReCount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CodeTimer.setI(180);
			}
		});

		JLabel Label1 = new JLabel("※ 승인번호가 도착하지 않은 경우");
		Label1.setForeground(java.awt.Color.black); // 글자색 변경
		Label1.setBounds(20, 290, 380, 60);
		panel.add(Label1);

		JLabel Label2 = new JLabel("1. 문자메세지 수신 가능 지역 확인");
		Label2.setForeground(java.awt.Color.black); // 글자색 변경
		Label2.setBounds(34, 305, 380, 60);
		panel.add(Label2);

		JLabel Label3 = new JLabel("2. 전화번호 확인");
		Label3.setForeground(java.awt.Color.black); // 글자색 변경
		Label3.setBounds(34, 320, 380, 60);
		panel.add(Label3);

		JLabel Label4 = new JLabel("※ 인증번호는 시간 내에 입력해주시기 바랍니다.");
		Label4.setForeground(java.awt.Color.black); // 글자색 변경
		Label4.setBounds(20, 340, 380, 60);
		panel.add(Label4);

		// 회원가입 버튼을 누르면 회원 아이디, 비밀번호를 추가하는 테이블 실행
		checkbtn = new JButton("확인");
		checkbtn.setBounds(90, 420, 80, 25);
		panel.add(checkbtn);
		checkbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				code = CodeNoText.getText();
				System.out.println("코드 : " + code);
				System.out.println("코드체크 : " + codeCheck);
				if (code.equals(codeCheck)) {
					JOptionPane.showMessageDialog(null, "인증되었습니다.");
					CodeTimer.timer.cancel();
					dispose();
					MemberProc memberProc = new MemberProc();
					// CodeTimer.t.stop();
				} else {
					JOptionPane.showMessageDialog(null, "인증번호가 다릅니다.");
					return;
				}
			}
		});

		// 로그인 버튼 누르면 아이디와 패스워드 비교 시작
		cancleLogin = new JButton("닫기");
		cancleLogin.setBounds(190, 420, 80, 25);
		panel.add(cancleLogin);
		cancleLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "닫으시겠습니까?", "닫기 확인", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오
					return;
				}
				if (confirm == 0) {// 예
					dispose();
					MainProcess.main(null);
				}
			}
		});
	}
}
