package chap19;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kosea.kmove30.JDBC_Manager;

public class LoginView extends JFrame {

	JDBC_Manager jdbcManager;
	JTable table;
	AddLogIn addLogin;
	private static MainProcess main;
	private JButton btnLogin;
	private JButton btnInit;
	private static JPasswordField passText;
	private static JTextField userText;
	private static boolean bLoginCheck;

	public static void main(String[] args) {

		// new LoginView();
	}

	public LoginView() {
		// setting
		setTitle("로그인");
		setSize(280, 150);
		setResizable(false);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		placeLoginPanel(panel);

		// add
		add(panel);

		// visiible
		setVisible(true);
	}

	public void placeLoginPanel(JPanel panel) {

		// 프레임 짜기
		panel.setLayout(null);
		JLabel userLabel = new JLabel("아이디");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JLabel passLabel = new JLabel("비밀번호");
		passLabel.setBounds(10, 40, 80, 25);
		panel.add(passLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);
		passText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});

		// 회원가입 버튼을 누르면 회원 아이디, 비밀번호를 추가하는 테이블 실행
		btnInit = new JButton("회원가입");
		btnInit.setBounds(10, 80, 100, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// DB관리(회원추가 클래스)
				JDBC_Manager jdbcManager = new JDBC_Manager();
				JFrame frame = new JFrame("회원가입");
				frame.setPreferredSize(new Dimension(550, 200));
				frame.setLocation(500, 400);
				Container contentPane = frame.getContentPane();

				String colNames[] = { "아이디", "비밀번호" };

				DefaultTableModel model = new DefaultTableModel(colNames, 0);
				JTable table = new JTable(model);
				contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
				JPanel panel = new JPanel();

				JTextField text1 = new JTextField(10);
				JTextField text2 = new JTextField(10);

				JButton addBtn = new JButton("회원가입");

				panel.add(new JLabel("아이디"));
				panel.add(text1);
				panel.add(new JLabel("비밀번호"));
				panel.add(text2);

				panel.add(addBtn);

				contentPane.add(panel, BorderLayout.SOUTH);

				addBtn.addActionListener(new AddLogIn(table, text1, text2, jdbcManager));
				// 버튼을 누르면 member 테이블에 아이디와 비밀번호 값 추가

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);

				System.out.println("프로그램 시작");

				try {
					jdbcManager.DBConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mysql", "root",
							"12345");
					System.out.println("데이터베이스에 연결되었습니다.");
					frame.setTitle("회원등록");
				} catch (ClassNotFoundException cnfe) {
					System.out.println("해당 클래스를 찾을 수 없습니다.");
					System.out.println(cnfe.getMessage());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					frame.setTitle("접속실패");
				}
			}
		});

		// 로그인 버튼 누르면 아이디와 패스워드 비교 시작
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(160, 80, 100, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
	}

	public static void isLoginCheck() {

		// 멤버 테이블에 있는 id, pw 속성값과 입력한 속성값 비교하기 위해 DB 접속
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			String id = null;
			String pw = null;

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345");
			System.out.println("데이터베이스에 접속했습니다.");

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, pw FROM member");

			while (rs.next()) {

				id = rs.getString(1);
				pw = rs.getString(2);

				if (userText.getText().equals(id)) {
					if (new String(passText.getPassword()).equals(pw)) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						bLoginCheck = true;

					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
					}

				}
			}

			// 로그인 성공이라면 MainProcess 클래스의 showFrameTest 메소드 실행
			if (isLogin()) {
				main.showFrameTest(); // 메인창 메소드를 이용해 창띄우기
			}

			if (bLoginCheck != true) { // 모든 아이디, 비밀번호 값이 일치하지 않는다면 Faild 창 띄우기
				JOptionPane.showMessageDialog(null, "로그인 실패");
			}

			// 4단계: DB연결을 종료한다
			conn.close();

		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}

	}

	// mainProcess와 연동
	public void setMain(MainProcess main) {
		LoginView.main = main;
	}

	public static boolean isLogin() {
		return bLoginCheck;
	}

}