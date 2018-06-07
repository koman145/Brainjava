package com.kosea.kmove30;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class StartActionListener implements ActionListener {

	public StartActionListener() {
		super();
	}

	public void actionPerformed(ActionEvent arg0) {
		
		
		int confirm = JOptionPane.showConfirmDialog(null, "시작하시겠습니까?", "시작", // 삭제 경고문 창
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		System.out.println("confirm : " + confirm);

		if (confirm == 1) // 아니오를 선택하면 종료
			return;
		
		if (confirm == 0) {
			Tetris.main(null);
		}
	}

}
