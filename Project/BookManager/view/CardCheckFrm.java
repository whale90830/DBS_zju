package liang.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import liang.dao.BookDao;
import liang.dao.LibraryCardDao;
import liang.model.LibraryCard;
import liang.model.Manager;
import liang.util.DbUtil;
import liang.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class CardCheckFrm extends JFrame {

	private JPanel contentPane;
	private JTextField CardIDTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private LibraryCardDao libraryCardDao = new LibraryCardDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardCheckFrm frame = new CardCheckFrm();
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
	public CardCheckFrm() {
		setResizable(false);
		setTitle("读者登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("图书管理系统");
		label.setFont(new Font("华文新魏", Font.BOLD, 36));
		
		JLabel label_1 = new JLabel("借书证卡号：");
		
		CardIDTxt = new JTextField();
		CardIDTxt.setColumns(10);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardCheckActionPerformed(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(64)
					.addComponent(label_1)
					.addGap(18)
					.addComponent(CardIDTxt, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(173, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(166))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(106, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(CardIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addComponent(button)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		// 设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}

	/**
	 * 读者登录事件处理
	 * @param event
	 */
	private void CardCheckActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.CardIDTxt.getText())){
			JOptionPane.showMessageDialog(null, "读书证卡号不能为空！");
			return;
		}
		int cardID=Integer.valueOf(this.CardIDTxt.getText());
		LibraryCard card=new LibraryCard(cardID);
		Connection conn=null;
		try {
			conn=dbUtil.initConnection();
			ResultSet card_result = libraryCardDao.SelectCard_ID(conn, cardID);
			if(card_result.next()) {
				dispose();
				new BookSearchFrm().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "借书证卡号错误！");
			}
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
}
