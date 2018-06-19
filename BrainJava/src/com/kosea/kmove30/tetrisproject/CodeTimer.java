package com.kosea.kmove30.tetrisproject;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CodeTimer extends Thread {
	public static String time1;
	public static JLabel countLabel = new JLabel();

	public static Timer timer = new Timer();
	public static int i;
	TimerTask task = new TimerTask() {
		public void run() {
			time1 = getTime(i);
			i--;
			countLabel.setText(time1);
			countLabel.setForeground(java.awt.Color.red); // 글자색 변경
			if (i == -1) {
				timer.cancel();
				CheckCodeNo.codeCheck = null;
				JOptionPane.showMessageDialog(null, "인증시간이 종료되었습니다.");
				JOptionPane.showMessageDialog(null, "다시 시도해 주십시오.");
			}
		}
	};

	public void runTimer() {
		timer.schedule(task, 0, 1000);
	}

	public static String getTime(int sec) {
		int remainderOfHours = 0;
		int minutes = 0;
		int seconds = 0;

		if (sec >= 3600) {
			remainderOfHours = sec % 3600;

			if (remainderOfHours >= 60) {
				minutes = remainderOfHours / 60;
				seconds = remainderOfHours % 60;
			} else {
				seconds = remainderOfHours;
			}
		} else if (sec >= 60) {
			minutes = sec / 60;
			seconds = sec % 60;
		} else if (sec < 60) {
			minutes = 0;
			seconds = sec;
		}
		String strMins;
		String strSecs;

		if (seconds < 10) {
			strSecs = "0" + Integer.toString(seconds);
		} else {
			strSecs = Integer.toString(seconds);
		}

		if (minutes < 10) {
			strMins = "0" + Integer.toString(minutes);
		} else {
			strMins = Integer.toString(minutes);
		}

		time1 = strMins + "분" + strSecs + "초";
		return time1;
	}

	public static int getI() {
		return i;
	}

	public static int setI(int i) {
		return CodeTimer.i = i;
	}

	public static void main(String[] args) {
		CodeTimer che = new CodeTimer();
		che.runTimer();
	}
}
