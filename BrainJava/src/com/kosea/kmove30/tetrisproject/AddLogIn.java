package com.kosea.kmove30.tetrisproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kosea.kmove30.JDBC_Manager;

public class AddLogIn implements ActionListener {
	JTable table;
	JTextField text1, text2, text3;
	JDBC_Manager jdbcManager;

	public AddLogIn(JTable table, JTextField text1, JTextField text2, JDBC_Manager jdbcManager) {
		super();
		this.table = table;
		this.text1 = text1;
		this.text2 = text2;
		this.jdbcManager = jdbcManager;
	}

	public void actionPerformed(ActionEvent e) {

		int confirm = JOptionPane.showConfirmDialog(null, "회원가입 하시겠습니까?", "회원가입", // 삭제 경고문 창
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		System.out.println("confirm : " + confirm);

		if (confirm == 1) // 아니오를 선택하면 종료
			return;

		String ary[] = new String[2];
		ary[0] = text1.getText();
		ary[1] = text2.getText();

		boolean isCheck = false; // 입력창 체크
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = 0; i < ary.length; i++) {
			System.out.println("arr[" + i + "] : " + ary[i]);
			if (ary[i].length() > 0)
				isCheck = true;
			else
				isCheck = false;
		}

		if (isCheck)
			model.addRow(ary); // JTable에 데이터 추가

		// DB Insert 추가 작업
		try {
			jdbcManager.addIdPw(ary);
		} catch (Exception ex) {
			ex.getMessage();
		}
		

	}

}