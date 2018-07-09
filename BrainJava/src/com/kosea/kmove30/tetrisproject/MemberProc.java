package com.kosea.kmove30.tetrisproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MemberProc extends JFrame implements ActionListener {

	JPanel p, pTel, pBirth, pGender, pButton;
	JTextField tfId, tfName;
	JTextField tfTel1, tfTel2, tfTel3; // 전화
	JComboBox tfYear, tfMonth, tfDate; // 생년월일
	public static JPasswordField pfPwd, pfPwdCh; // 비밀번호
	JLabel bId, bPwd, bName, bTel, bBirth, bGender, bIntro;
	JScrollPane pane;

	// JTextField ;// 생년월일
	JRadioButton rbMan, rbWoman; // 남, 여
	JTextArea taIntro;
	RoundedButton btnInsert, btnCancel, btnUpdate, btnDelete, btnCheck, btnIdCheck; // 가입, 취소, 수정 , 탈퇴 버튼\
	public static String phoneNo = null;
	int[] arrYear = new int[2018 - 1900];
	int[] arrMonth = new int[12];
	int[] arrDate = new int[31];
	public static int insertA, insertB;
	public static JLabel bPwdCh, pwCheck, bIdCh;

	Member_List mList;

	public MemberProc() { // 가입용 생성자

		createUI(); // UI작성해주는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);

	}// 생성자

	public MemberProc(Member_List mList) { // 가입용 생성자

		createUI(); // UI작성해주는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
		this.mList = mList;

	}// 생성자

	public MemberProc(String id, Member_List mList) { // 수정/삭제용 생성자
		createUI();
		btnInsert.setEnabled(false);
		btnInsert.setVisible(false);
		btnIdCheck.setEnabled(false);
		btnIdCheck.setVisible(false);
		pfPwdCh.setEnabled(false);
		pfPwdCh.setVisible(false);
		bPwdCh.setVisible(false);
		bPwdCh.setEnabled(false);
		bIdCh.setVisible(false);
		bIdCh.setEnabled(false);
		setSize(500, 530);
		tfId.setBounds(100, 25, 350, 30);
		bPwd.setBounds(10, 70, 100, 30);
		pfPwd.setBounds(100, 70, 350, 30);
		pwCheck.setText("비밀번호를 꼭 입력해주세요.");
		pwCheck.setBounds(100, 100, 350, 30);
		bName.setBounds(10, 150, 200, 30);
		tfName.setBounds(100, 150, 350, 30);
		bTel.setBounds(10, 190, 200, 30);
		pTel.setBounds(120, 190, 350, 30);
		bBirth.setBounds(10, 230, 200, 30);
		pBirth.setBounds(100, 230, 350, 30);
		bGender.setBounds(10, 270, 200, 30);
		pGender.setBounds(100, 270, 350, 30);
		bIntro.setBounds(10, 310, 200, 30);
		pane.setBounds(100, 310, 350, 120);
		pButton.setBounds(70, 450, 350, 30);
		this.mList = mList;

		MemberDAO dao = new MemberDAO();
		MemberDTO vMem = dao.getMemberDTO(id);
		viewData(vMem);

	}// id를 가지고 생성

	// MemberDTO 의 회원 정보를 가지고 화면에 셋팅해주는 메소드
	private void viewData(MemberDTO vMem) {

		String id = vMem.getId();
		String pwd = vMem.getPwd();
		String name = vMem.getName();
		String tel = vMem.getTel();
		String birth = vMem.getBirth();
		String gender = vMem.getGender();
		String intro = vMem.getIntro();

		// 화면에 세팅
		tfId.setText(id);
		tfId.setEditable(false); // 편집 안되게
		pfPwd.setText(""); // 비밀번호는 안보여준다.
		tfName.setText(name);
		String[] tels = tel.split("-");
		tfTel1.setText(tels[0]);
		tfTel2.setText(tels[1]);
		tfTel3.setText(tels[2]);

		tfYear.setSelectedItem(birth.substring(0, 4));
		tfYear.setEnabled(false);
		tfMonth.setSelectedItem(birth.substring(4, 6));
		tfMonth.setEnabled(false);
		tfDate.setSelectedItem(birth.substring(6, 8));
		tfDate.setEnabled(false);

		if (gender.equals("M")) {
			rbMan.setSelected(true);
		} else if (gender.equals("W")) {
			rbWoman.setSelected(true);
		}

		taIntro.setText(intro);

	}// viewData

	private void createUI() {

		// setting
		setTitle("회원가입");
		setSize(500, 600);
		setResizable(false);
		setLocation(700, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		placeCheckPanel(panel);

		// add
		add(panel);

		// visiible
		setVisible(true);

	}// createUI

	public void placeCheckPanel(JPanel panel) {

		panel.setLayout(null);
		bId = new JLabel("아이디");
		bId.setForeground(Color.WHITE);
		bId.setBounds(10, 10, 100, 60);
		panel.add(bId);
		tfId = new JTextField();
		tfId.setBounds(100, 25, 250, 30);
		panel.add(tfId);
		bIdCh = new JLabel("아이디 중복확인을 해주세요.");
		bIdCh.setBounds(100, 55, 200, 30);
		bIdCh.setForeground(Color.LIGHT_GRAY);
		panel.add(bIdCh);

		// ID 중복확인 버튼
		btnIdCheck = new RoundedButton("중복확인");
		btnIdCheck.setBackground(Color.WHITE);
		btnIdCheck.setBounds(380, 25, 100, 30);
		panel.add(btnIdCheck);
		btnIdCheck.setPreferredSize(new Dimension(1, 1));
		btnIdCheck.setFont(new Font("Times", Font.PLAIN, 11));
		btnIdCheck.setForeground(Color.black);
		btnIdCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText().equals("")) {
					bIdCh.setText("아이디를 입력해주세요");
					bIdCh.setForeground(Color.red);
					btnInsert.setEnabled(false);
				} else {
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					try {
						System.out.println(tfId.getText());
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345");
						System.out.println("데이터베이스에 접속했습니다.");

						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM Tetrismember where id = '" + tfId.getText() + "'");
						String rsString = String.valueOf(rs.next()); // 왜인지 모르겠지만 rs.next()는 boolean값인데 if문에서 true값으로
																		// 인식되지 않는다.
																		// 따라서 boolean값을 String으로 변경하여 조건문에서 비교에 사용하도록
																		// 했다.
						if (rsString.equals("true")) { // 입력한 ID가 tetrismember에 존재한다면 (true값이라면) 가입불가
							System.out.println("true 실행");
							bIdCh.setText("이미  사용중인 아이디입니다.");
							bIdCh.setForeground(java.awt.Color.red);
							insertA = 0;
						}

						if (rsString.equals("false")) { // 입력한 ID가 tetrismember에 존재하지않는다면 (false값이라면) 가입가능
							System.out.println("false 실행");
							bIdCh.setText("사용할 수 있는 아이디입니다.");
							bIdCh.setForeground(java.awt.Color.blue);
							insertA = 1;
						}
						conn.close();
					} catch (ClassNotFoundException cnfe) {
						System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
					} catch (SQLException se) {
						System.out.println(se.getMessage());
					}
				}

				if (insertA == 1 & insertB == 1) {
					btnInsert.setEnabled(true);
				}
			}
		});

		// 비밀번호
		bPwd = new JLabel("비밀번호");
		bPwd.setForeground(Color.WHITE);
		bPwd.setBounds(10, 100, 100, 30);
		panel.add(bPwd);
		pfPwd = new JPasswordField(20);
		pfPwd.setBounds(100, 100, 350, 30);
		panel.add(pfPwd);

		// 비밀번호 확인
		bPwdCh = new JLabel("비밀번호확인");
		bPwdCh.setForeground(Color.WHITE);
		bPwdCh.setBounds(10, 140, 100, 30);
		panel.add(bPwdCh);
		pfPwdCh = new JPasswordField(20);
		pfPwdCh.setBounds(100, 140, 350, 30);
		panel.add(pfPwdCh);

		pwCheck = new JLabel("비밀번호를 입력해주세요.");
		pwCheck.setBounds(100, 170, 350, 30);
		pwCheck.setForeground(Color.LIGHT_GRAY);
		panel.add(pwCheck);

		pfPwdCh.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (pfPwd.getText().equals(pfPwdCh.getText())) { // 비밀번호와 비밀번호확인에 입력된 값이 같다면 가입가능
					pwCheck.setText("비밀번호가 같습니다.");
					pwCheck.setForeground(java.awt.Color.blue);
					insertB = 1;
				}
				if (!pfPwd.getText().equals(pfPwdCh.getText())) { // 비밀번호와 비밀번호확인에 입력된 값이 다르다면 가입불가
					pwCheck.setText("비밀번호가 다릅니다.");
					pwCheck.setForeground(java.awt.Color.red);
					insertB = 0;
				}

				if (insertA == 1 & insertB == 1) {
					btnInsert.setEnabled(true);
				}
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// 이름
		bName = new JLabel("이름");
		bName.setForeground(Color.WHITE);
		bName.setBounds(10, 220, 100, 30);
		panel.add(bName);
		tfName = new JTextField(20);
		tfName.setBounds(100, 220, 350, 30);
		panel.add(tfName);

		// 전화
		bTel = new JLabel(" 전화 (인증완료)");
		bTel.setForeground(Color.WHITE);
		bTel.setBounds(10, 260, 100, 30);
		panel.add(bTel);
		pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTel.setBackground(Color.darkGray);
		tfTel1 = new JTextField(6);
		tfTel1.setText(CheckPhoneNo.getpNo1());
		tfTel1.setEnabled(false);
		tfTel2 = new JTextField(6);
		tfTel2.setText(CheckPhoneNo.getpNo2());
		tfTel2.setEnabled(false);
		tfTel3 = new JTextField(6);
		tfTel3.setText(CheckPhoneNo.getpNo3());
		tfTel3.setEnabled(false);
		pTel.add(tfTel1);
		pTel.add(new JLabel(" - "));
		pTel.add(tfTel2);
		pTel.add(new JLabel(" - "));
		pTel.add(tfTel3);
		pTel.setBounds(120, 260, 350, 30);
		panel.add(pTel);

		// 생일
		bBirth = new JLabel("생년월일");
		bBirth.setForeground(Color.WHITE);
		bBirth.setBounds(10, 300, 100, 30);
		panel.add(bBirth);
		// 년도 설정
		for (int i = 0; i <= 2017 - 1900; i++) {
			arrYear[i] = 1900 + i;
		}
		Arrays.sort(arrYear);
		String Year = Arrays.toString(arrYear);
		String[] arrYear = Year.substring(1, Year.length() - 1).split(", ");
		// 월 설정
		String[] arrMonth = { "--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		// 일 설정
		String[] arrDate = { "--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		tfYear = new JComboBox<>(arrYear);
		tfMonth = new JComboBox<>(arrMonth);
		tfDate = new JComboBox<>(arrDate);
		pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.setBackground(Color.darkGray);
		JLabel year = new JLabel("년");
		year.setForeground(Color.LIGHT_GRAY);
		JLabel month = new JLabel("월");
		month.setForeground(Color.LIGHT_GRAY);
		JLabel date = new JLabel("일");
		date.setForeground(Color.LIGHT_GRAY);
		pBirth.add(tfYear);
		pBirth.add(year);
		pBirth.add(tfMonth);
		pBirth.add(month);
		pBirth.add(tfDate);
		pBirth.add(date);
		pBirth.setBounds(100, 300, 350, 30);
		panel.add(pBirth);

		// 성별
		bGender = new JLabel("성별");
		bGender.setForeground(Color.WHITE);
		bGender.setBounds(10, 340, 100, 30);
		panel.add(bGender);
		pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pGender.setBackground(Color.darkGray);
		rbMan = new JRadioButton("남", true);
		rbMan.setForeground(Color.LIGHT_GRAY);
		rbMan.setBackground(Color.darkGray);
		rbWoman = new JRadioButton("여", true);
		rbWoman.setForeground(Color.LIGHT_GRAY);
		rbWoman.setBackground(Color.darkGray);
		ButtonGroup group = new ButtonGroup();
		group.add(rbMan);
		group.add(rbWoman);
		pGender.add(rbMan);
		pGender.add(rbWoman);
		pGender.setBounds(100, 340, 350, 30);
		panel.add(pGender);

		// 자기소개
		bIntro = new JLabel("자기 소개");
		bIntro.setForeground(Color.WHITE);
		bIntro.setBounds(10, 380, 100, 30);
		panel.add(bIntro);
		taIntro = new JTextArea(5, 20); // 행 : 열
		pane = new JScrollPane(taIntro);
		pane.setBounds(100, 380, 350, 120);
		panel.add(pane);

		// 버튼
		pButton = new JPanel();
		pButton.setBackground(Color.darkGray);
		btnInsert = new RoundedButton("가입");
		btnInsert.setBackground(Color.white);
		btnInsert.setForeground(Color.darkGray);
		btnUpdate = new RoundedButton("수정");
		btnUpdate.setBackground(Color.white);
		btnUpdate.setForeground(Color.darkGray);
		btnDelete = new RoundedButton("탈퇴");
		btnDelete.setBackground(Color.white);
		btnDelete.setForeground(Color.darkGray);
		btnCancel = new RoundedButton("취소");
		btnCancel.setBackground(Color.white);
		btnCancel.setForeground(Color.darkGray);
		pButton.add(btnInsert);
		btnInsert.setEnabled(false);
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancel);
		pButton.setBounds(70, 520, 350, 30);
		panel.add(pButton);

		// 버튼에 감지기를 붙이자
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);

	}

	public static void main(String[] args) {
		new MemberProc();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnInsert) {
			insertMember();
		} else if (ae.getSource() == btnCancel) {
			this.dispose(); // 창닫기 (현재창만 닫힘)
			// system.exit(0)=> 내가 띄운 모든 창이 다 닫힘
		} else if (ae.getSource() == btnUpdate) {
			UpdateMember();
		} else if (ae.getSource() == btnDelete) {
			// int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
			int x = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
			if (x == JOptionPane.OK_OPTION) {
				deleteMember();
				MainProcess.main(null);
			} else {
				JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
			}
		}

		// jTable내용 갱신 메소드 호출
		// mList.jTableRefresh();

	}// actionPerformed

	private void deleteMember() {
		String id = tfId.getText();
		String pwd = pfPwd.getText();
		if (pwd.length() == 0) { // 길이가 0이면
			JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
			return; // 메소드 끝
		}
		// System.out.println(mList);
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.deleteMember(id, pwd);

		if (ok) {
			JOptionPane.showMessageDialog(this, "삭제되었습니다.");
			dispose();

		} else {
			JOptionPane.showMessageDialog(this, "삭제실패");
		}
	}// deleteMember

	private void UpdateMember() {

		// 1. 화면의 정보를 얻는다.
		MemberDTO dto = getViewData();
		// 2. 그정보로 DB를 수정
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.updateMember(dto);

		if (ok) {
			int x = JOptionPane.showConfirmDialog(this, "정말 수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION);

			if (x == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(this, "수정되었습니다.");
				DbLogIn.main(null);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "수정을 취소하였습니다.");
			}

		} else {
			JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");
		}
	}

	private void insertMember() {
		String name = tfName.getText();
		String birth1 = (String) tfYear.getSelectedItem();
		String birth2 = (String) tfMonth.getSelectedItem();
		String birth3 = (String) tfDate.getSelectedItem();
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요.");
		} else if (birth1.equals("1900") || birth2.equals("--") || birth3.equals("--")) {
			JOptionPane.showMessageDialog(this, "생년월일을 선택해주세요");
		} else {

			int x = JOptionPane.showConfirmDialog(this, "생년월일은 수정할 수 없습니다. 다시한번 확인하여 주십시오. 가입하시겠습니까? ", "가입",
					JOptionPane.YES_NO_OPTION);
			if (x == JOptionPane.OK_OPTION) {
				MemberDTO dto = getViewData();
				MemberDAO dao = new MemberDAO();
				boolean ok = dao.insertMember(dto);
				if (ok) {
					JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
					MainProcess.main(null);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "취소하였습니다.");
			}
		}
	}

	// insertMember
	public MemberDTO getViewData() {

		// 화면에서 사용자가 입력한 내용을 얻는다.
		MemberDTO dto = new MemberDTO();
		String id = tfId.getText();
		String pwd = pfPwd.getText();
		String name = tfName.getText();
		String tel1 = tfTel1.getText();
		String tel2 = tfTel2.getText();
		String tel3 = tfTel3.getText();
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		String birth1 = (String) tfYear.getSelectedItem();
		String birth2 = (String) tfMonth.getSelectedItem();
		String birth3 = (String) tfDate.getSelectedItem();
		// String birth1 = tfYear.getText();
		// String birth2 = tfMonth.getText();
		// String birth3 = tfDate.getText();
		// String birth = birth1+"/"+birth2+"/"+birth3;
		String birth = birth1 + birth2 + birth3;
		String gender = "";

		tfYear.setSelectedItem(birth);
		tfMonth.setSelectedItem(birth);
		tfDate.setSelectedItem(birth);

		if (rbMan.isSelected()) {
			gender = "M";
		} else if (rbWoman.isSelected()) {
			gender = "W";
		}

		String intro = taIntro.getText();
		phoneNo = tel1 + tel2 + tel3;

		// dto에 담는다.
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setTel(tel);
		dto.setBirth(birth);
		dto.setGender(gender);
		dto.setIntro(intro);

		return dto;
	}

}// end