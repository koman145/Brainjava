package chap19;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kosea.kmove30.JDBC_Manager;

public class WindowExample8 {
	public static void main(String args[]) {

		// DB관리(연결, 조회, 삭제, 추가 클래스)
		JDBC_Manager jdbcManager = new JDBC_Manager();
		JFrame frame = new JFrame("참가자 명단 프로그램");
		frame.setPreferredSize(new Dimension(500, 200));
		frame.setLocation(500, 400);
		Container contentPane = frame.getContentPane();
		String colNames[] = { "이름", "나이", "성별" };
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		JPanel panel = new JPanel();

		JTextField text1 = new JTextField(6);
		JTextField text2 = new JTextField(3);
		JTextField text3 = new JTextField(2);

		JButton button1 = new JButton("추가");
		JButton button2 = new JButton("삭제");
		JButton selectBtn = new JButton("조회");

		panel.add(new JLabel("이름"));
		panel.add(text1);
		panel.add(new JLabel("나이"));
		panel.add(text2);
		panel.add(new JLabel("성별"));
		panel.add(text3);

		panel.add(button1);
		panel.add(button2);
		panel.add(selectBtn);

		contentPane.add(panel, BorderLayout.SOUTH);

		selectBtn.addActionListener(new SelectActionListener(jdbcManager, table));
		button1.addActionListener(new AddActionListener(table, text1, text2, text3, jdbcManager));
		button2.addActionListener(new RemoveActionListener(table, jdbcManager));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		System.out.println("프로그램 시작");

		try {
			jdbcManager.DBConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mysql", "root", "12345");
			System.out.println("데이터베이스에 연결되었습니다.");
			frame.setTitle("참가자 명단 프로그램 - DB접속성공");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다.");
			cnfe.getMessage();
		} catch (Exception e) {
			e.getMessage();
			frame.setTitle("참가자 명단 프로그램 - DB접속실패");
		}

	}

}
