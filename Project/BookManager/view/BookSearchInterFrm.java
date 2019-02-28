package liang.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import liang.dao.BookDao;
import liang.model.Manager;
import liang.util.DbUtil;
import liang.util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class BookSearchInterFrm extends JInternalFrame {
	private JTextField ISBNTxt;
	private JTextField TitleTxt;
	private JTextField TypeTxt;
	private JTextField AuthorTxt;
	private JTextField PublisherTxt;
	private JTextField LowerYearTxt;
	private JTextField LowerPriceTxt;
	private JTextField UpperYearTxt;
	private JTextField UpperPriceTxt;
	private JTable BookTable;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearchInterFrm frame = new BookSearchInterFrm();
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
	public BookSearchInterFrm() {
		setTitle("图书查询");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 804, 514);
		
		JLabel label = new JLabel("书号：");
		
		JLabel label_1 = new JLabel("书名：");
		
		JLabel label_2 = new JLabel("类别：");
		
		JLabel label_3 = new JLabel("作者：");
		
		JLabel label_4 = new JLabel("出版社：");
		
		JLabel label_5 = new JLabel("年份：");
		
		JLabel label_6 = new JLabel("价格：");
		
		ISBNTxt = new JTextField();
		ISBNTxt.setColumns(10);
		
		JButton button = new JButton("精确查找");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ISBNSearchActionPerformed(e);
			}
		});
		
		TitleTxt = new JTextField();
		TitleTxt.setColumns(10);
		
		TypeTxt = new JTextField();
		TypeTxt.setColumns(10);
		
		AuthorTxt = new JTextField();
		AuthorTxt.setColumns(10);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setColumns(10);
		
		LowerYearTxt = new JTextField();
		LowerYearTxt.setColumns(10);
		
		LowerPriceTxt = new JTextField();
		LowerPriceTxt.setColumns(10);
		
		JLabel label_7 = new JLabel("至");
		
		JLabel label_8 = new JLabel("至");
		
		UpperYearTxt = new JTextField();
		UpperYearTxt.setColumns(10);
		
		UpperPriceTxt = new JTextField();
		UpperPriceTxt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button_1 = new JButton("模糊查找");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearchActionPerformed(e);
			}
		});
		
		JButton button_2 = new JButton("重置");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(LowerYearTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(label_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(LowerPriceTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(label_8)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(UpperPriceTxt)
								.addComponent(UpperYearTxt, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ISBNTxt, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(TitleTxt, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(AuthorTxt, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(PublisherTxt)
									.addComponent(TypeTxt, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
					.addGap(55))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(TitleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(AuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PublisherTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(LowerYearTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7)
						.addComponent(UpperYearTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(LowerPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(UpperPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2))
					.addGap(40)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		BookTable = new JTable();
		BookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u53F7", "\u7C7B\u522B", "\u4E66\u540D", "\u51FA\u7248\u793E", "\u51FA\u7248\u5E74\u4EFD", "\u4F5C\u8005", "\u4EF7\u683C", "\u603B\u85CF\u4E66\u91CF", "\u5728\u67B6\u6570\u91CF"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(BookTable);
		getContentPane().setLayout(groupLayout);

	}

	

	/**
	 * 重置事件处理
	 * @param event
	 */
	private void resetValueActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		this.resetValue();
		this.fillTable(null);
	}

	/**
	 * 精确查找事件处理
	 * @param event
	 */
	private void ISBNSearchActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(ISBNTxt.getText())) {
			JOptionPane.showMessageDialog(null, "精确查找书号不能为空！");
			return;
		}
		String ISBN = ISBNTxt.getText();
		Connection conn=null;
		try {
			conn=dbUtil.initConnection();
			ResultSet book = bookDao.SelectISBN(conn, ISBN);
			this.fillTable(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	/**
	 * 模糊查找事件处理
	 * @param event
	 */
	private void BookSearchActionPerformed(ActionEvent event) {
		if(StringUtil.isEmpty(this.ISBNTxt.getText())
				&&StringUtil.isEmpty(this.TypeTxt.getText())&&StringUtil.isEmpty(this.AuthorTxt.getText())
				&&StringUtil.isEmpty(this.TitleTxt.getText())&&StringUtil.isEmpty(this.PublisherTxt.getText())
				&&StringUtil.isEmpty(this.LowerYearTxt.getText())&&StringUtil.isEmpty(this.LowerPriceTxt.getText())
				&&StringUtil.isEmpty(this.UpperYearTxt.getText())&&StringUtil.isEmpty(this.UpperPriceTxt.getText())){
			JOptionPane.showMessageDialog(null, "请输入检索信息！");
			return;
		}
		Connection conn=null;
		try {
			conn=dbUtil.initConnection();
			ResultSet book;
			//若填写了书号，则默认采用精确查找
			if(StringUtil.isNotEmpty(ISBNTxt.getText())) {
				String ISBN = ISBNTxt.getText();
				book = bookDao.SelectISBN(conn, ISBN);
			}
			else {
				StringBuffer sql=new StringBuffer("select * from book");
				if(StringUtil.isNotEmpty(TitleTxt.getText())){
					sql.append(" and book.Title like '%"+TitleTxt.getText()+"%'");
				}
				if(StringUtil.isNotEmpty(TypeTxt.getText())){
					sql.append(" and book.Type like '%"+TypeTxt.getText()+"%'");
				}
				if(StringUtil.isNotEmpty(AuthorTxt.getText())){
					sql.append(" and book.Author like '%"+AuthorTxt.getText()+"%'");
				}
				if(StringUtil.isNotEmpty(PublisherTxt.getText())){
					sql.append(" and book.Publisher like '%"+PublisherTxt.getText()+"%'");
				}
				if(StringUtil.isNotEmpty(LowerYearTxt.getText())){
					sql.append(" and book.Publish_year >="+Integer.valueOf(LowerYearTxt.getText()));
				}
				if(StringUtil.isNotEmpty(LowerPriceTxt.getText())){
					sql.append(" and book.Price >="+Float.valueOf(LowerPriceTxt.getText()));
				}
				if(StringUtil.isNotEmpty(UpperYearTxt.getText())){
					sql.append(" and book.Publish_year <="+Integer.valueOf(UpperYearTxt.getText()));
				}
				if(StringUtil.isNotEmpty(UpperPriceTxt.getText())){
					sql.append(" and book.Price <="+Float.valueOf(UpperPriceTxt.getText()));
				}
				PreparedStatement pstmt=conn.prepareStatement(sql.toString().replaceFirst("and", "where"));
				book = pstmt.executeQuery();
			}
			this.fillTable(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	
	/**
	 * 初始化表格
	 * @param book
	 */
	private void fillTable(ResultSet book){
		DefaultTableModel dtm=(DefaultTableModel) BookTable.getModel();
		dtm.setRowCount(0); // 将表格设置成0行
		try {
			while(book.next()) {
					Vector v=new Vector();
					v.add(book.getString("ISBN"));
					v.add(book.getString("Type"));
					v.add(book.getString("Title"));
					v.add(book.getString("Publisher"));
					v.add(book.getString("Publish_year"));
					v.add(book.getString("Author"));
					v.add(book.getString("Price"));
					v.add(book.getString("Number"));
					v.add(book.getString("Stock"));
					dtm.addRow(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 重置操作
	 */
	private void resetValue() {
		// TODO Auto-generated method stub
		this.ISBNTxt.setText("");
		this.TypeTxt.setText("");
		this.TitleTxt.setText("");
		this.PublisherTxt.setText("");
		this.LowerYearTxt.setText("");
		this.LowerPriceTxt.setText("");
		this.UpperYearTxt.setText("");
		this.UpperPriceTxt.setText("");
		this.AuthorTxt.setText("");
	}
	
}
