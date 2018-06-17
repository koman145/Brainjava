package com.kosea.kmove30;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class CodeTimer {
	public static String time1;
	public static JLabel countLabel = new JLabel();

	Timer timer = new Timer();
	public static int i = 180;
	TimerTask task = new TimerTask() {
		public void run() {
			time1 = getTime(i);
			System.out.println(getTime(i));
			i--;
			countLabel.setText(time1);
			countLabel.setForeground(java.awt.Color.red); // 글자색 변경
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

	public static void main(String[] args) {
		CodeTimer che = new CodeTimer();
		che.runTimer();
	}
}
