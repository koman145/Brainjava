package chap19;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowExample3 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Zoo Program");
		frame.setPreferredSize(new Dimension(600, 300));
		
		//frame.setLocation(1200, 800);
		
		Container contentPane = frame.getContentPane();
		GridLayout layout = new GridLayout(3, 2);
		contentPane.setLayout(layout);
		
		contentPane.add(new JButton("얼룩말"));
		contentPane.add(new JButton("사자"));
		contentPane.add(new JButton("코끼리"));
		contentPane.add(new JButton("코뿔소"));
		contentPane.add(new JButton("펭귄"));
		contentPane.add(new JButton("하이에나"));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // 화면 정중앙 배치
		
	}

}