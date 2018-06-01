package chap19;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import com.kosea.kmove30.JDBC_Manager;

public class UpdateActionListener implements ActionListener {

	JTextField text1, text2;
	JDBC_Manager jdbcManager;
	JTable table;
	
	

	public UpdateActionListener(JTextField text1, JTextField text2, JDBC_Manager jdbcManager, JTable table) {
		super();
		this.text1 = text1;
		this.text2 = text2;
		this.jdbcManager = jdbcManager;
		this.table = table;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String name = text1.getText();
			String age	= text2.getText();
			jdbcManager.updateTable(name, age);
			
			SelectActionListener selectActionListener = new SelectActionListener(jdbcManager, table);
			selectActionListener.actionPerformed(e);
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
