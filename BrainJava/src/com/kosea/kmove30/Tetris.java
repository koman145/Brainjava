package com.kosea.kmove30;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class Tetris extends JFrame implements Runnable, KeyListener

{
	private int width; // 가로
	private int height; // 세로
	private int xCnt; // 가로배열크기
	private int yCnt; // 세로배열크기
	private int area; // 가로세로길이
	private int time; // 빠르기
	private boolean[][] grid; //
	private JPanel[][] background; // 배경판넬
	private Container fc; // 컨테이너
	private Item nextItem; // 다음나올것
	private Item currentItem; // 현재의 아이템
	private ArrayList<Item> itemList; // 아이템리스트
	private ArrayList<Color> colorList; // 컬러리스트
	private Random rnd;
	private JPanel top, next, center; // 상단 가리는부분
	private JPanel pointscreen; // 점수판
	// private JPanel hardMode;
	private boolean isKey = true; // 키보드활성화여부
	private final Color bgColor = new Color(250, 250, 220); // 배경컬러
	public static int sum = 0;
	public static int level = 1;
	public static String point = null;
	static DbLogIn dblogin;
	static String nowlevel = DbLogIn.getNowlevel();
	JLabel pointtext = new JLabel();
	JLabel leveltext = new JLabel();

	// public static boolean isRight = false; //오른쪽여부
	Thread t;

	public Tetris(String str) {
		// ========== 기본설정 셋팅 시작 ===========
		this.setTitle(str);
		this.xCnt = 14;
		this.yCnt = 23;
		this.time = 300;
		this.area = 30;
		this.width = (this.xCnt * this.area) + 11;
		this.height = (this.yCnt * this.area) + 4;
		this.itemList = new ArrayList<Item>();
		this.background = new JPanel[this.xCnt][this.yCnt];
		this.grid = new boolean[this.xCnt][this.yCnt];
		this.rnd = new Random(System.currentTimeMillis());
		this.fc = this.getContentPane();
		this.center = new JPanel();
		this.center.setSize(this.width, this.height);
		this.center.setLayout(null);
		this.center.setBackground(new Color(224, 255, 216));
		this.fc.add(this.center, "Center");
		this.addKeyListener(this);
		this.setBounds(200, 200, this.width + 8, this.height + 13);

		// ========== 기본설정 셋팅 끝 ===========
		// 아이템 추가하기
		itemList.add(new Rect(this.area, this.center, this.xCnt));
		itemList.add(new OneThree(this.area, this.center, this.xCnt));
		itemList.add(new ThreeOne(this.area, this.center, this.xCnt));
		itemList.add(new LineBlock(this.area, this.center, this.xCnt));
		itemList.add(new Triangle(this.area, this.center, this.xCnt));
		itemList.add(new RightBlock(this.area, this.center, this.xCnt));
		itemList.add(new LeftBlock(this.area, this.center, this.xCnt));

		// 색 추가
		this.colorList = new ArrayList<Color>();
		this.colorList.add(Color.red);
		this.colorList.add(Color.blue);
		this.colorList.add(Color.green);
		this.colorList.add(Color.orange);
		this.colorList.add(Color.pink);
		this.colorList.add(new Color(170, 40, 150)); // 보라

		// 상단 셋팅
		// 시작======///////////////////////////////////////////////////////////////////////////////////
		this.top = new JPanel();
		this.pointscreen = new JPanel();
		// this.hardMode = new JPanel();
		this.next = new JPanel();
		this.top.setBounds(0, 0, this.xCnt * this.area, this.area * 4);
		this.top.setBackground(new Color(244, 211, 99));
		this.next.setBounds((this.xCnt - 4) * this.area, 0, this.area * 4, this.area * 4);
		this.next.setBackground(new Color(245, 180, 250));
		this.pointscreen.setBounds(0, 0, 50, this.area * 4);
		this.pointscreen.setBackground(new Color(255, 255, 255));
		// this.hardMode.setBounds(50, 0, (this.xCnt * this.area) - 50, this.area * 4);
		// this.hardMode.setBackground(new Color(0, 0, 0));
		this.center.add(this.top);
		this.top.add(this.pointscreen);
		// this.top.add(this.hardMode);
		this.top.setLayout(null);
		this.top.add(this.next);

		// 점수 및 레벨 표시
		this.pointscreen.add(new JLabel("점수"));
		this.pointscreen.add(pointtext);
		pointtext.setText("0점");
		pointtext.setForeground(java.awt.Color.ORANGE);
//		pointtext.setEnabled(false);
		this.pointscreen.add(new JLabel("레벨"));
		this.pointscreen.add(leveltext);
		leveltext.setText("1단계");
		leveltext.setForeground(java.awt.Color.ORANGE);
//		leveltext.setEnabled(false);

		// 상단 셋팅 끝======////////////////////////////////////////////////

		// 백그라운드 패널 셋팅 시작 ==========
		for (int i = 0; i < background.length; i++) {
			for (int p = 0; p < background[i].length; p++) {
				this.background[i][p] = new JPanel();
				this.background[i][p].setBounds(i * this.area, p * this.area, this.area, this.area);
				this.background[i][p].setBackground(this.bgColor);
				this.center.add(background[i][p]);
			}
		}

		// 아이템 시작셋팅
		this.currentItem = itemList.get(rnd.nextInt(itemList.size()));
		this.currentItem.setColor(this.colorList.get(this.rnd.nextInt(this.colorList.size())));
		this.currentItem.setDefaultLocation();
		setNextItem();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// this.setResizable(false);
		t = new Thread(this);
		t.start();
	}

	// 넥스트 아이템 셋팅

	public void setNextItem()

	{
		Item temp;
		do {
			temp = itemList.get(rnd.nextInt(itemList.size()));
		} while (temp == this.currentItem); // 현재아이템과 중복X
		this.nextItem = temp;
		this.nextItem.setColor(this.colorList.get(this.rnd.nextInt(this.colorList.size())));
		this.nextItem.setNextLocation(); // 위치셋팅
	}

	// 아이템 새로 나오기 셋팅
	public void setNewItem() {
		this.currentItem = this.nextItem;
		this.currentItem.setDefaultLocation();
		setNextItem();
	}

	// 백그라운드 블럭 채우기
	public void setBack(int x, int y, Color c) {
		this.background[x][y].setBackground(c);
		this.background[x][y].setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		this.grid[x][y] = true;

		// System.out.println("x="+x+", y="+y);
	}

	// 백그라운드 블럭 비우기
	public void setEmptyBack(int x, int y) {
		this.background[x][y].setBorder(null);
		this.background[x][y].setBackground(this.bgColor);
		this.grid[x][y] = false;
	}

	// 현재의 블록 백그라운드로 복사
	public void setCopyBlock() {
		Block[] tempBlock = this.currentItem.getBlock();
		for (int i = 0; i < tempBlock.length; i++) {
			setBack(tempBlock[i].getX(), tempBlock[i].getY(), this.currentItem.getColor());
		}
		this.currentItem.setReadyLocation(); // 대기위치로 돌아가기
	}

	// 줄없애기 체크
	public void checkLine() {
		for (int i = 0; i < grid[0].length; i++) // i = Y값 = ROW
		{

			// System.out.println(
			boolean isLine = true;
			for (int p = 0; p < grid.length; p++) // p = X값 = Column
			{

				// System.out.print(p+","+i+" : " + grid[p][i]);
				if (!grid[p][i]) // 하나라도 공백이 있으면 break;
				{
					isLine = false;
					break;
				}
			}

			// 한줄 없앨 때 마다 10점(point 에 저장)
			// 없앤 줄 x 10 ( point ) 값을 sum 에 더해서 저장
			// 전체 점수는 sum 에 저장됨
			if (isLine) // 줄없앰 and 점수 증가
			{
				int point = 0;
				deleteLine(i);
				point = point + 10;
				sum += point;
				// sum 값이 증가함에 따라 게임속도(this.time 값)이 낮아짐 (속도가 빨라짐)
				System.out.println(nowlevel);

				if (sum >= 70 || nowlevel.equals("2단계") || nowlevel.equals("3단계") || nowlevel.equals("4단계")
						|| nowlevel.equals("5단계") || nowlevel.equals("6단계") || nowlevel.equals("7단계")
						|| nowlevel.equals("8단계")) {
					this.time = 270; // 2단계
					level = 2;
					if (sum >= 140 || nowlevel.equals("3단계") || nowlevel.equals("4단계") || nowlevel.equals("5단계")
							|| nowlevel.equals("6단계") || nowlevel.equals("7단계") || nowlevel.equals("8단계")) {
						this.time = 240; // 3단계
						level = 3;
						if (sum >= 210 || nowlevel.equals("4단계") || nowlevel.equals("5단계") || nowlevel.equals("6단계")
								|| nowlevel.equals("7단계") || nowlevel.equals("8단계")) {
							this.time = 210; // 4단계
							level = 4;
							if (sum >= 280 || nowlevel.equals("5단계") || nowlevel.equals("6단계") || nowlevel.equals("7단계")
									|| nowlevel.equals("8단계")) {
								this.time = 180; // 5단계
								level = 5;
								if (sum >= 350 || nowlevel.equals("6단계") || nowlevel.equals("7단계")
										|| nowlevel.equals("8단계")) {
									this.time = 150; // 6단계
									level = 6;
									if (sum >= 420 || nowlevel.equals("7단계") || nowlevel.equals("8단계")) {
										this.time = 120; // 7단계
										level = 7;
										if (sum >= 490 || nowlevel.equals("8단계")) {
											this.time = 90; // 8단계
											level = 8;
										}
									}
								}
							}
						}
					}
				}
			}

			pointtext.setText(String.valueOf(sum + "점"));
			leveltext.setText(String.valueOf(level + "단계"));
		}

	}

	public static int getSum() {
		return sum;
	}

	public static void setSum(int sum) {
		Tetris.sum = sum;
	}

	// 줄없애고 위에거 한칸씩 내리기
	public void deleteLine(int line) {
		JPanel tempPanel[] = new JPanel[xCnt];
		for (int i = line; i > 0; i--) // i = 줄 = Y
		{
			for (int p = 0; p < grid.length; p++) // p = 열 = X
			{
				if (i == line) // 현재줄 템프변수에 저장
				{
					tempPanel[p] = background[p][i];
					tempPanel[p].setLocation(p * this.area, 0);
				}

				// 모든줄 한칸씩 내리기
				grid[p][i] = grid[p][i - 1];
				background[p][i] = background[p][i - 1];
				background[p][i].setLocation(p * this.area, i * this.area);
			}
		}

		// 없앤줄 맨위로 올리기
		for (int i = 0; i < grid.length; i++) {
			background[i][0] = tempPanel[i];
			setEmptyBack(i, 0);
		}

	}

	// 프린트정보출력 임시
	public void printInfo() {
		Block temp = this.currentItem.getCurrentXY();
		System.out.println("x : " + temp.getX() + ", y : " + temp.getY());
	}

	// 아이템 회전체크 -> 회전
	public void goRotate() {
		Block[] tempBlock = this.currentItem.getNextBlock();
		for (int i = 0; i < tempBlock.length; i++) {
			int x = tempBlock[i].getX();
			int y = tempBlock[i].getY();
			if (x < 0 || x >= this.xCnt || y + 1 >= this.yCnt || this.grid[x][y]) {
				return;
			}
		}
		this.currentItem.moveRotate();
	}

	// 아이템다운체크 -> 이동
	public boolean goDown() {
		Block[] tempBlock = this.currentItem.getBlock();
		for (int i = 0; i < tempBlock.length; i++) {
			int x = tempBlock[i].getX();
			int y = tempBlock[i].getY() + 1;
			if (y + 1 >= this.yCnt || this.grid[x][y]) {
				if (!this.isKey) {
					gameEnd();
				} // 게임끝
				setCopyBlock(); // 백그라운드블럭 셋팅
				checkLine(); // 줄없애기 체크
				setNewItem(); // 다음아이템 셋팅
				return false;
			}
		}
		this.currentItem.moveDown();
		return true;
	}

	// 아이템오른쪽이동체크 -> 이동
	public void goRight() {
		Block[] tempBlock = this.currentItem.getBlock();
		for (int i = 0; i < tempBlock.length; i++) {
			int x = tempBlock[i].getX() + 1;
			int y = tempBlock[i].getY();
			if (x >= this.xCnt || this.grid[x][y]) {
				return;
			}
		}
		this.currentItem.moveRight();
	}

	// 아이템왼쪽이동체크 -> 이동
	public void goLeft() {
		Block[] tempBlock = this.currentItem.getBlock();
		for (int i = 0; i < tempBlock.length; i++) {
			int x = tempBlock[i].getX() - 1;
			int y = tempBlock[i].getY();
			if (x < 0 || this.grid[x][y]) {
				return;
			}
		}
		this.currentItem.moveLeft();
	}

	// 벽돌없애기 체크 -> 없애기

	// 키보드액션리스너
	public void keyPressed(KeyEvent e) {
		if (!this.isKey)
			return;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			goDown();
			break;
		case KeyEvent.VK_LEFT:
			goLeft();
			break;
		case KeyEvent.VK_RIGHT:
			goRight();
			break;
		case KeyEvent.VK_UP:
			goRotate();
			break;
		case KeyEvent.VK_SPACE:
			while (goDown()) {
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	// 게임종료체크
	public void gameEnd() {

		dispose(); // 프레임 닫기

		Connection conn = null;
		Statement stmt = null;

		try {
			int sum = Tetris.getSum();
			String userid = LoginView.getUserText().getText();

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root",
					"12345");
			System.out.println("데이터베이스에 접속했습니다.");
			stmt = conn.createStatement();
			String sql = "INSERT INTO tetrispoint(id, tpoint) values('";
			int changerecord = stmt.executeUpdate(sql + userid + "'," + sum + ")");

			conn.close();
			System.out.println(changerecord + "건이 입력 되었습니다.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}

		int confirm = JOptionPane.showConfirmDialog(null, "다시 하시겠습니까?", "GAME OVER", // 삭제 경고문 창
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == 1) {// 아니오를 선택하면 메인창 띄우기
			sum = 0;
			DbLogIn.main("");
		}
		if (confirm == 0) {// 예를 선택하면 게임 재시작
			sum = 0;
			Tetris.main("");
		}

		t.stop(); // 프로세스 종료
	}

	// 쓰레드메인

	public void run() {
		try {
			while (true) {
				Thread.sleep(this.time);

				// 판넬위쪽이면 키리스너 동작X
				if (this.currentItem.getCurrentXY().getY() < 3)
					this.isKey = false;
				else
					this.isKey = true;

				goDown(); // 아이템밑으로이동
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String string) {
		new Tetris("Tetris by JH");
		System.out.println("LEVEL : " + DbLogIn.getNowlevel());
	}
}