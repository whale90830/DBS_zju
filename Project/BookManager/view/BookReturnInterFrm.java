package liang.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import liang.dao.BookDao;
import liang.dao.RecordDao;
import liang.model.Record;
import liang.util.DbUtil;
import liang.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class BookReturnInterFrm extends JInternalFrame {

	private JTable RecordTable;
	private JTextField CardIDTxt;
	private JTextField ISBNTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	private RecordDao recordDao = new RecordDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookReturnInterFrm frame = new BookReturnInterFrm();
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
	public BookReturnInterFrm() {
		setTitle("归还书籍");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 688, 415);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("借书证卡号：");
		
		CardIDTxt = new JTextField();
		CardIDTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordSearchActionPerformed(e);
			}
		});
		
		JLabel label_1 = new JLabel("归还书号：");
		
		ISBNTxt = new JTextField();
		ISBNTxt.setColumns(10);
		
		JButton button_1 = new JButton("确认归还");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookReturnActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(CardIDTxt, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 599, GroupLayout.PREFERRED_SIZE))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(CardIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		
		RecordTable = new JTable();
		RecordTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RecordTableMousePressed(e);
			}
		});
		RecordTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u53F7", "\u7C7B\u522B", "\u4E66\u540D", "\u51FA\u7248\u793E", "\u5E74\u4EFD", "\u4F5C\u8005", "\u4EF7\u683C", "\u501F\u9605\u65E5\u671F", "\u5E94\u8FD8\u65E5\u671F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(RecordTable);
		getContentPane().setLayout(groupLayout);

	}
	
	/**
	 * 表格行点击事件处理
	 * @param event
	 */
	private void RecordTableMousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		int row=RecordTable.getSelectedRow();
		ISBNTxt.setText((String)RecordTable.getValueAt(row, 0));
	}

	/**
	 * 还书事件处理
	 * @param event
	 */
	private void BookReturnActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		int Card_ID=Integer.valueOf(this.CardIDTxt.getText());
		if(StringUtil.isEmpty(ISBNTxt.getText())) {
			JOptionPane.showMessageDialog(null, "书号不能为空！");
			return;
		}
		String ISBN = ISBNTxt.getText();
		
		Connection conn=null;
		try{
			conn=dbUtil.initConnection();
			
			ResultSet borrow_record = recordDao.Select(conn, Card_ID);
			int flag=0;
			while(borrow_record.next()) {
				if(borrow_record.getString("Book_ISBN").equals(ISBN)) {
					flag=1;
					recordDao.Delete(conn, ISBN, Card_ID);
					JOptionPane.showMessageDialog(null, "归还成功！");
					this.fillTable(Card_ID);
				}
			}
			if(flag==0) {
				JOptionPane.showMessageDialog(null, "归还失败！");
			}

		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "归还失败！");
		}finally{
			try {
				dbUtil.closeConnection(conn);;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 借书记录检索事件处理
	 * @param event
	 */
	private void RecordSearchActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		int Card_ID=Integer.valueOf(this.CardIDTxt.getText());
		this.fillTable(Card_ID);
	}
	
	/**
	 * 初始化表格
	 * @param Card_ID
	 */
	private void fillTable(int Card_ID){
		DefaultTableModel dtm=(DefaultTableModel) RecordTable.getModel();
		dtm.setRowCount(0); // 将表格设置成0行
		Connection conn=null;
		try{
			conn=dbUtil.initConnection();
			
			ResultSet borrow_record = recordDao.Select(conn, Card_ID);
			ResultSet book_information;
			while(borrow_record.next()) {
				book_information = bookDao.SelectISBN(conn, borrow_record.getString("Book_ISBN"));
				Vector v=new Vector();
				book_information.next();
				v.add(book_information.getString("ISBN"));
				v.add(book_information.getString("Type"));
				v.add(book_information.getString("Title"));
				v.add(book_information.getString("Publisher"));
				v.add(book_information.getString("Publish_year"));
				v.add(book_information.getString("Author"));
				v.add(book_information.getString("Price"));
				v.add(borrow_record.getString("Borrow_date"));
				v.add(borrow_record.getString("Return_date"));
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
