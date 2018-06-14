package com.kosea.kmove30;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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

	JPanel p;
	JTextField tfId, tfName;
	JTextField tfTel1, tfTel2, tfTel3; // 전화
	JComboBox tfYear, tfMonth, tfDate; // 생년월일
	JPasswordField pfPwd; // 비밀번호
	// JTextField ;// 생년월일
	JRadioButton rbMan, rbWoman; // 남, 여
	JTextArea taIntro;
	JButton btnInsert, btnCancel, btnUpdate, btnDelete, btnCheck; // 가입, 취소, 수정 , 탈퇴 버튼\
	public static String phoneNo = null;
	CheckPhoneNo checkPhoneNo = new CheckPhoneNo();
	int[] arrYear = new int[2018 - 1900];
	int[] arrMonth = new int[12];
	int[] arrDate = new int[31];

	GridBagLayout gb;
	GridBagConstraints gbc;
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
		this.mList = mList;

		System.out.println("id=" + id);

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
		System.out.println("실행");

		this.setTitle("회원정보");
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// 아이디
		JLabel bId = new JLabel("아이디");
		tfId = new JTextField(20);
		// 그리드백에 붙이기
		gbAdd(bId, 0, 0, 1, 1);
		gbAdd(tfId, 1, 0, 3, 1);

		// 비밀번호
		JLabel bPwd = new JLabel("비밀번호");
		pfPwd = new JPasswordField(20);
		gbAdd(bPwd, 0, 1, 1, 1);
		gbAdd(pfPwd, 1, 1, 3, 1);

		// 이름
		JLabel bName = new JLabel("이름");
		tfName = new JTextField(20);
		gbAdd(bName, 0, 2, 1, 1);
		gbAdd(tfName, 1, 2, 3, 1);

		// 전화
		JLabel bTel = new JLabel(" 전화 (인증완료)");
		JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfTel1 = new JTextField(6);
		tfTel2 = new JTextField(6);
		tfTel3 = new JTextField(6);
		pTel.add(tfTel1);
		pTel.add(new JLabel(" - "));
		pTel.add(tfTel2);
		pTel.add(new JLabel(" - "));
		pTel.add(tfTel3);
		gbAdd(bTel, 0, 3, 1, 1);
		gbAdd(pTel, 1, 3, 3, 1);

		// 생일
		JLabel bBirth = new JLabel("생년월일");
		// 년도 설정
		for (int i = 0; i <= 2017 - 1900; i++) {
			arrYear[i] = 1900 + i;
		}
		Arrays.sort(arrYear);
		String Year = Arrays.toString(arrYear);
		String[] arrYear = Year.substring(1, Year.length() - 1).split(", ");
		// 월 설정
		String[] arrMonth = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		// 일 설정
		String[] arrDate = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		tfYear = new JComboBox<>(arrYear);
		tfMonth = new JComboBox<>(arrMonth);
		tfDate = new JComboBox<>(arrDate);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(tfYear);
		pBirth.add(new JLabel("년"));
		pBirth.add(tfMonth);
		pBirth.add(new JLabel("월"));
		pBirth.add(tfDate);
		pBirth.add(new JLabel("일"));
		gbAdd(bBirth, 0, 6, 1, 1);
		gbAdd(pBirth, 1, 6, 3, 1);

		// 성별
		JLabel bGender = new JLabel("성별");
		JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rbMan = new JRadioButton("남", true);
		rbWoman = new JRadioButton("여", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rbMan);
		group.add(rbWoman);
		pGender.add(rbMan);
		pGender.add(rbWoman);
		gbAdd(bGender, 0, 8, 1, 1);
		gbAdd(pGender, 1, 8, 3, 1);

		// 자기소개
		JLabel bIntro = new JLabel("자기 소개");
		taIntro = new JTextArea(5, 20); // 행 : 열
		JScrollPane pane = new JScrollPane(taIntro);
		gbAdd(bIntro, 0, 10, 1, 1);
		gbAdd(pane, 1, 10, 3, 1);

		// 버튼
		JPanel pButton = new JPanel();
		btnInsert = new JButton("가입");
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("탈퇴");
		btnCancel = new JButton("취소");
		pButton.add(btnInsert);
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancel);
		gbAdd(pButton, 0, 11, 4, 1);

		// 버튼에 감지기를 붙이자
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);

		setSize(380, 450);
		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //프로그램종료
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose(); //현재창만 닫는다.

	}// createUI

	// 그리드백레이아웃에 붙이는 메소드
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(c, gbc);
	}// gbAdd

	public static void main(String[] args) {

		new MemberProc();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnInsert) {
			insertMember();
			System.out.println("insertMember() 호출 종료");
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
			} else {
				JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
			}
		}

		// jTable내용 갱신 메소드 호출
		mList.jTableRefresh();

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
			JOptionPane.showMessageDialog(this, "삭제완료");
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
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "수정을 취소하였습니다.");
			}

		} else {
			JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");
		}
	}

	private void insertMember() {

		// 화면에서 사용자가 입력한 내용을 얻는다.
		MemberDTO dto = getViewData();
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.insertMember(dto);

		if (ok) {

			JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
			dispose();

		} else {

			JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
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

		System.out.println(tel);
		System.out.println(birth);
		System.out.println(phoneNo);

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