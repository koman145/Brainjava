package chap19;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.kosea.kmove30.JDBC_Manager;

public class RemoveActionListener implements ActionListener {
	JTable table;
	JDBC_Manager jdbcManager;

	public RemoveActionListener(JTable table, JDBC_Manager jdbcManager) {
		super();
		this.table = table;
		this.jdbcManager = jdbcManager;
	}

	public void actionPerformed(ActionEvent e) {
		
		int confirm = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제",  // 삭제 경고문 창
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		System.out.println("confirm : " + confirm);
		
		if(confirm == 1) // 아니오를 선택하면 종료
			return;
		
		int row = table.getSelectedRow(); // 선택된 행의 행값 row에 저장
		if (row == -1) {
			return;
		}

		// 삭제될 이름
		Object name = table.getValueAt(row, 0); // 선택된 행의 0열(이름) 위치정보 name에 저장
		String deleteName = name.toString(); // name에 저장된 위치정보를 이용String 값으로 deleteName에 저장
		System.out.println(deleteName); // 따라서 deleteName 값을 출력하면 (row, 0)열의 값이 출력된다
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.removeRow(row); // 선택된 행 삭제
		
		
		try {
		// DB 테이블 레코드 (테이블) 삭제
		jdbcManager.deleteTable(deleteName);
		}catch (Exception ex) {
			ex.getMessage();
		}
	}

}