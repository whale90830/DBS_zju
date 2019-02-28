package liang.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import liang.dao.BookDao;
import liang.dao.LibraryCardDao;
import liang.model.Book;
import liang.model.LibraryCard;
import liang.util.DbUtil;
import liang.util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookAddInterFrm extends JInternalFrame {
	private JTextField ISBNTxt;
	private JTextField TypeTxt;
	private JTextField TitleTxt;
	private JTextField PublisherTxt;
	private JTextField PublishYearTxt;
	private JTextField AuthorTxt;
	private JTextField PriceTxt;
	private JTextField NumberTxt;
	private JButton button;
	private JButton button_1;
	
	private DbUtil dbUtil=new DbUtil();
	private BookDao bookDao=new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
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
	public BookAddInterFrm() {
		setTitle("图书单本入库");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 474);
		
		JLabel lblisbn = new JLabel("书号ISBN：");
		
		JLabel label = new JLabel("书籍类别：");
		
		JLabel label_1 = new JLabel("书    名：");
		
		JLabel label_2 = new JLabel("出 版 社：");
		
		JLabel label_3 = new JLabel("出版年份：");
		
		JLabel label_4 = new JLabel("作    者：");
		
		JLabel label_5 = new JLabel("价    格：");
		
		JLabel label_6 = new JLabel("入库数量：");
		
		ISBNTxt = new JTextField();
		ISBNTxt.setColumns(10);
		
		TypeTxt = new JTextField();
		TypeTxt.setColumns(10);
		
		TitleTxt = new JTextField();
		TitleTxt.setColumns(10);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setColumns(10);
		
		PublishYearTxt = new JTextField();
		PublishYearTxt.setColumns(10);
		
		AuthorTxt = new JTextField();
		AuthorTxt.setColumns(10);
		
		PriceTxt = new JTextField();
		PriceTxt.setColumns(10);
		
		NumberTxt = new JTextField();
		NumberTxt.setColumns(10);
		
		button = new JButton("确认入库");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddActionPerformed(e);
			}
		});
		
		button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblisbn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
								.addComponent(label_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(TypeTxt)
								.addComponent(TitleTxt)
								.addComponent(ISBNTxt, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(PublisherTxt)
								.addComponent(PublishYearTxt)
								.addComponent(AuthorTxt)
								.addComponent(PriceTxt)
								.addComponent(NumberTxt))))
					.addGap(79))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblisbn)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(TypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(TitleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(PublisherTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(PublishYearTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(AuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(PriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(NumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	private void BookAddActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if(StringUtil.isEmpty(this.ISBNTxt.getText())||StringUtil.isEmpty(this.TypeTxt.getText())
				||StringUtil.isEmpty(this.TitleTxt.getText())||StringUtil.isEmpty(this.PublisherTxt.getText())
				||StringUtil.isEmpty(this.PublishYearTxt.getText())||StringUtil.isEmpty(this.AuthorTxt.getText())
				||StringUtil.isEmpty(this.PriceTxt.getText())||StringUtil.isEmpty(this.NumberTxt.getText())){
			JOptionPane.showMessageDialog(null, "请填写完整图书信息！");
			return;
		}
		
		String ISBN=this.ISBNTxt.getText();
		String type=this.TypeTxt.getText();
		String title=this.TitleTxt.getText();
		String publisher=this.PublisherTxt.getText();
		int publish_year=Integer.valueOf(this.PublishYearTxt.getText());
		String author=this.AuthorTxt.getText();
		float price=Float.valueOf(this.PriceTxt.getText());
		int number=Integer.valueOf(this.NumberTxt.getText());
		int stock=number;
		
		Book book=new Book(ISBN, type, title, publisher, publish_year, author, price, number, stock);
		Connection conn=null;
		try{
			conn=dbUtil.initConnection();
			int flag=bookDao.Insert(conn, book);
			if(flag==1){
				JOptionPane.showMessageDialog(null, "图书入库成功！");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "图书入库失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书入库失败！");
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
	 * 重置操作
	 */
	private void resetValue() {
		// TODO Auto-generated method stub
		this.ISBNTxt.setText("");
		this.TypeTxt.setText("");
		this.TitleTxt.setText("");
		this.PublisherTxt.setText("");
		this.PublishYearTxt.setText("");
		this.AuthorTxt.setText("");
		this.PriceTxt.setText("");
		this.NumberTxt.setText("");
	}

	/**
	 * 重置事件处理
	 * @param event
	 */
	private void resetValueActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		this.resetValue();
	}

}
