package liang.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import liang.dao.LibraryCardDao;
import liang.model.LibraryCard;
import liang.util.DbUtil;
import liang.util.StringUtil;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardManageInterFrm extends JInternalFrame {
	
	private JTable LibraryCardTable;
	private JTextField CardIDTxt;
	
	private DbUtil dbUtil=new DbUtil();
	private LibraryCardDao librarycardDao=new LibraryCardDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardManageInterFrm frame = new CardManageInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CardManageInterFrm() {
		setTitle("借书证管理");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 587, 374);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardSelectActionPerformed(e);
			}
		});
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardDeleteActionEvent(e);
			}
		});
		
		JLabel label = new JLabel("借书证卡号：");
		
		CardIDTxt = new JTextField();
		CardIDTxt.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addComponent(CardIDTxt, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(112))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CardIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		
		LibraryCardTable = new JTable();
		LibraryCardTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		LibraryCardTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u501F\u4E66\u8BC1\u5361\u53F7", "\u8BFB\u8005\u59D3\u540D", "\u5DE5\u4F5C\u5355\u4F4D", "\u8EAB\u4EFD\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(LibraryCardTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(0);
	}
	
	/**
	 * 借书证删除事件处理
	 * @param event
	 */
	private void CardDeleteActionEvent(ActionEvent event) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.CardIDTxt.getText())){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int Card_ID=Integer.valueOf(this.CardIDTxt.getText());
		
		int flag=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(flag==0){
			Connection conn=null;
			try{
				conn=dbUtil.initConnection();
				int delete_flag=librarycardDao.Delete(conn, Card_ID);
				if(delete_flag==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.fillTable(0);
					this.resetValue();
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}finally{
				try {
					dbUtil.closeConnection(conn);;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 重置操作
	 */
	private void resetValue() {
		// TODO Auto-generated method stub
		this.CardIDTxt.setText("");
	}

	/**
	 * 借书证查询事件处理
	 * @param event
	 */
	private void CardSelectActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.CardIDTxt.getText())){
			JOptionPane.showMessageDialog(null, "请输入借书证卡号");
			return;
		}
		int Card_ID=Integer.valueOf(this.CardIDTxt.getText());
		this.fillTable(Card_ID);
	}

	/**
	 * 表格行点击事件处理
	 * @param event
	 */
	private void bookTypeTableMousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		int row=LibraryCardTable.getSelectedRow();
		CardIDTxt.setText((String)LibraryCardTable.getValueAt(row, 0));
	}

	/**
	 * 初始化表格
	 * @param Card_ID
	 */
	private void fillTable(int Card_ID){
		DefaultTableModel dtm=(DefaultTableModel) LibraryCardTable.getModel();
		dtm.setRowCount(0); // 将表格设置成0行
		Connection conn=null;
		try{
			conn=dbUtil.initConnection();
			ResultSet rs=librarycardDao.SelectCard_ID(conn, Card_ID);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("Card_ID"));
				v.add(rs.getString("Username"));
				v.add(rs.getString("Company"));
				v.add(rs.getString("Type"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection(conn);;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
