package com.kosea.kmove30.tetrisproject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbLogIn extends JFrame {

	static String level[] = { "1단계", "2단계", "3단계", "4단계", "5단계", "6단계", "7단계", "8단계" };
	static JComboBox<String> levelCombox = new JComboBox<String>(level);
	public static String nowlevel = null;
	public static String id = LoginView.getUserText().getText();
	public static Member_List mList = null;

	public static void main(String string) {

		JFrame frame = new JFrame("테트리스 게임");
		frame.setPreferredSize(new Dimension(400, 200));
		frame.setLocation(500, 400);
		Container contentPane = frame.getContentPane();

		String colNames[] = { "순위", "아이디", "점수" };

		DefaultTableModel model = new DefaultTableModel(colNames, 0) {
			public boolean isCellEditable(int i, int c) {
				return false; // 불러온 테이블 값 수정 불가
			}
		};

		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		JPanel panel = new JPanel();

		JLabel levelSelect = new JLabel("LEVEL");
		JButton startBtn = new JButton("게임시작");
		JButton selectBtn = new JButton("정보수정");
		JButton logoutBtn = new JButton("로그아웃");

		panel.add(levelSelect);
		panel.add(levelCombox);
		panel.add(startBtn);
		panel.add(selectBtn);
		panel.add(logoutBtn);

		contentPane.add(panel, BorderLayout.SOUTH);

		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "시작하시겠습니까?", "시작", // 삭제 경고문 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오를 선택하면 종료
					return;
				}
				if (confirm == 0) {// 예를 선택하면 테트리스 실행

					frame.setVisible(false);
					nowlevel = levelCombox.getSelectedItem().toString();
					Tetris.main("");
				}
			}
		});

		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "정보를 수정하시겠습니까?", "정보수정", // 정보 수정 알림 창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == 1) {// 아니오를 선택하면 종료
					return;
				} else {
					frame.setVisible(false);
					MemberProc memberproc = new MemberProc(id, mList);
				}
			}
		});

		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", // 로그아웃 알림창
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				System.out.println("confirm : " + confirm);

				if (confirm == 1) {// 아니오를 선택하면 종료
					return;
				} else {
					frame.setVisible(false);
					MainProcess.main(null);
				}

			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		System.out.println("프로그램 시작");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String arr[] = new String[3];

		try {

			arr[0] = null;
			arr[1] = null;
			arr[2] = null;
			model.setNumRows(0);

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345");
			System.out.println("데이터베이스에 접속했습니다.");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT id, tpoint, @vRank := @vRank + 1 AS rank FROM tetrispoint AS p, (SELECT @vRank := 0) AS r ORDER BY tpoint DESC");

			while (rs.next()) {
				arr[0] = rs.getString(3);
				arr[1] = rs.getString(1);
				arr[2] = rs.getString(2);

				System.out.println(arr[0] + " " + arr[1]);
				model.addRow(arr);
			}
			conn.close();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static String getNowlevel() {
		return nowlevel;
	}

	public static void setNowlevel(String nowlevel) {
		DbLogIn.nowlevel = nowlevel;
	}
}