package chap19;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kosea.kmove30.JDBC_Manager;

public class AddActionListener implements ActionListener {
	JTable table;
	JTextField text1, text2, text3;
	JDBC_Manager jdbcManager;
	JComboBox genderCombox;

	public AddActionListener(JTable table, JTextField text1, JTextField text2, JDBC_Manager jdbcManager) {
		super();
		this.table = table;
		this.text1 = text1;
		this.text2 = text2;
		this.jdbcManager = jdbcManager;
	}

	public AddActionListener(JTable table, JTextField text1, JTextField text2, JDBC_Manager jdbcManager,
			JComboBox genderCombox) {
		super();
		this.table = table;
		this.text1 = text1;
		this.text2 = text2;
		this.jdbcManager = jdbcManager;
		this.genderCombox = genderCombox;
	}

	public AddActionListener(JTable table, JTextField text1, JTextField text2, JTextField text3,
			JDBC_Manager jdbcManager) {
		this.table = table;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.jdbcManager = jdbcManager;
	}

	public void actionPerformed(ActionEvent e) {

		String arr[] = new String[3];
		arr[0] = text1.getText();
		arr[1] = text2.getText();
		arr[2] = genderCombox.getSelectedItem().toString();

		if (arr[2].equals("선택")) {
			JOptionPane.showMessageDialog(null, "성별을 선택하세요.", "오류 메시지", JOptionPane.ERROR_MESSAGE);
			return;
		}

		/*
		 * int selectedIndex = genderCombox.getSelectedIndex(); Object item =
		 * genderCombox.getSelectedItem();
		 * 
		 * System.out.println("selectedIndex : " + selectedIndex);
		 * System.out.println("item : " + item.toString());
		 */

		// arr[2] = text3.getText(); text3 데이터를 설정

		boolean isCheck = false; // 입력창 체크
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "] : " + arr[i]);
			if (arr[i].length() > 0)
				isCheck = true;
			else
				isCheck = false;
		}

		if (isCheck)
			model.addRow(arr); // JTable에 데이터 추가

		// DB Insert 추가 작업
		try {
			jdbcManager.insertTable(arr);
		} catch (Exception ex) {
			ex.getMessage();
		}

		// 추가 후 입력창 빈칸만들기
		text1.setText("");
		text2.setText("");
		genderCombox.setSelectedIndex(0);

		// text3.setText("");

	}

}