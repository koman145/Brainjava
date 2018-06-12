package com.kosea.kmove30;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckPhoneNo extends JFrame {

	public static void main(String string) {

		Dimension dim = new Dimension(400, 200);

		JFrame frame = new JFrame("휴대폰 인증");
		frame.setLocation(500, 400);
		frame.setPreferredSize(dim);
		frame.setEnabled(true);
		Container contentPane = frame.getContentPane();

		JPanel panel = new JPanel();
		
		JLabel check = new JLabel("인증번호 : ");
		check.setBounds(10, 10, 80, 25);
		panel.add(check);
		
		JTextField checkNo = new JTextField(10);
		checkNo.setBounds(100, 10, 160, 25);
		panel.add(checkNo);

		JButton checkBtn = new JButton("인증");
		JButton cancleBtn = new JButton("취소");
		panel.add(checkBtn);
		panel.add(cancleBtn);

		panel.add(checkBtn);

		contentPane.add(panel, BorderLayout.SOUTH);

		checkBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "시작하시겠습니까?", "시작", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오를 선택하면 종료
					return;
				}
				if (confirm == 0) {// 예를 선택하면 테트리스 실행
					Tetris.main("");
				}
				frame.setVisible(false);
			}
		});

		cancleBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "시작하시겠습니까?", "시작", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오를 선택하면 종료
					return;
				}
				if (confirm == 0) {// 예를 선택하면 테트리스 실행
					Tetris.main("");
				}
				frame.setVisible(false);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		System.out.println("프로그램 시작");

	}
}
