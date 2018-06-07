package com.kosea.kmove30;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbLogIn extends JFrame {

	public static void main(String string) {

		JDBC_Manager jdbcManager = new JDBC_Manager();

		JFrame frame = new JFrame("테트리스 게임");
		frame.setPreferredSize(new Dimension(400, 200));
		frame.setLocation(500, 400);
		Container contentPane = frame.getContentPane();

		String colNames[] = { "아이디", "점수" };

		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		JPanel panel = new JPanel();

		JButton startBtn = new JButton("게임시작");
		JButton selectBtn = new JButton("회원정보수정");

		panel.add(startBtn);
		panel.add(selectBtn);
		

		contentPane.add(panel, BorderLayout.SOUTH);

		startBtn.addActionListener(new StartActionListener());
		selectBtn.addActionListener(new Addselect());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		System.out.println("프로그램 시작");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String arr[] = new String[2];
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();

		try {
			arr[0] = null;
			arr[1] = null;
			model.setNumRows(0);

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345");
			System.out.println("데이터베이스에 접속했습니다.");

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, tpoint FROM Tetrispoint ORDER BY tpoint DESC");

			while (rs.next()) {
				arr[0] = rs.getString(1);
				arr[1] = rs.getString(2);

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

}
