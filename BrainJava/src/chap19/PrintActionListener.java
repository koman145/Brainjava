package chap19;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import javax.swing.*;

public class PrintActionListener implements ActionListener {
	JTable table;
	PrintActionListener(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TableModel mode1 = table.getModel();
		int rowNum = mode1.getRowCount();
		int colNum = mode1.getColumnCount();
		for (int col = 0; col < colNum; col++) {
		String colName = mode1.getColumnName(col);
			System.out.println(colName + "\t");
		}
		System.out.println();
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
			Object obj = mode1.getValueAt(row, col);
			System.out.println(obj + "\t");
			}
			System.out.println();
		}
		System.out.println("---------------------------");

		
	}


}