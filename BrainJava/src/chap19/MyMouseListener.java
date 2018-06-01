package chap19;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyMouseListener extends MouseAdapter {

	JTextField text1, text2, text3;
	JComboBox genderCombox;
	
	

	public MyMouseListener(JTextField text1, JTextField text2) {
		super();
		this.text1 = text1;
		this.text2 = text2;
	}

	public MyMouseListener(JTextField text1, JTextField text2, JTextField text3) {
		super();
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
	}

	public MyMouseListener(JTextField text1, JTextField text2, JComboBox genderCombox) {
		super();
		this.text1 = text1;
		this.text2 = text2;
		this.genderCombox = genderCombox;
	}

	public void mouseClicked(MouseEvent e) { // 마우스클릭이벤트발생시
		super.mouseClicked(e);
		if (e.getButton() == 1) {
			JTable table = (JTable) e.getSource();
			int row = table.getSelectedRow(); // 선택되어진 row구하기
			if (row != -1) { // 셀이 선택되어진 상태인경우
				System.out.println(row);
				System.out.println(table.getValueAt(row, 0));

				// for (int i = 0; i<3; i++) {
				// text.setText(table.getValueAt(row, i).toString());
				// }

				text1.setText(table.getValueAt(row, 0).toString());
				text2.setText(table.getValueAt(row, 1).toString());
				String genderStr = table.getValueAt(row, 2).toString();

				if (genderStr.equals("남")) {
					// 콤보박스 "남" 을 선택
					genderCombox.setSelectedIndex(1);
				} else if (genderStr.equals("여")) {
					genderCombox.setSelectedIndex(2);
					// 콤보박스 "여" 를 선택
				} else {
					genderCombox.setSelectedIndex(0);
					// 콤보박스 "선택" 을 선택
				}

				/*
				 * int selectedIndex = genderCombox.getSelectedIndex(); Object item =
				 * genderCombox.getSelectedItem();
				 * 
				 * System.out.println("selectedIndex : " + selectedIndex);
				 * System.out.println("item : " + item.toString());
				 */

				// text3.setText(table.getValueAt(row, 2).toString());

			}
		}
	}
}
