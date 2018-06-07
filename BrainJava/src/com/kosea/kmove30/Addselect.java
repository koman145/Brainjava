package com.kosea.kmove30;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Addselect implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		String id = LoginView.getUserText().getText();
		Member_List mList = null ;

		int confirm = JOptionPane.showConfirmDialog(null, "정보를 수정하시겠습니까?", "정보수정", // 정보 수정 알림 창
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		System.out.println("confirm : " + confirm);

		if (confirm == 1) {// 아니오를 선택하면 종료
			return;
		}else {
			MemberProc memberproc = new MemberProc(id, mList);
//			Member_List memberlist = new Member_List();
		}

		

	}

}