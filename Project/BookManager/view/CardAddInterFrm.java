package liang.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import liang.dao.LibraryCardDao;
import liang.dao.ManagerDao;
import liang.model.LibraryCard;
import liang.util.DbUtil;
import liang.util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class CardAddInterFrm extends JInternalFrame {
	private JTextField UserNameTxt;
	private JTextField CompanyTxt;
	private JTextField TypeTxt;
	
	private DbUtil dbUtil=new DbUtil();
	private LibraryCardDao librarycardDao=new LibraryCardDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardAddInterFrm frame = new CardAddInterFrm();
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
	public CardAddInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("借书证添加");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("读者姓名：");
		
		JLabel label_1 = new JLabel("工作单位：");
		
		JLabel label_2 = new JLabel("身份类别：");
		
		UserNameTxt = new JTextField();
		UserNameTxt.setColumns(10);
		
		CompanyTxt = new JTextField();
		CompanyTxt.setColumns(10);
		
		TypeTxt = new JTextField();
		TypeTxt.setColumns(10);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardAddActionPerformed(e);
			}
		});
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(label)
									.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(TypeTxt, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(UserNameTxt, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
								.addComponent(CompanyTxt))))
					.addGap(80))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(UserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(CompanyTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(TypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(45))
		);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 借书证添加
	 * @param event
	 */
	private void CardAddActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		String username=this.UserNameTxt.getText();
		String company=this.CompanyTxt.getText();
		String type=this.TypeTxt.getText();
		
		if(StringUtil.isEmpty(username)){
			JOptionPane.showMessageDialog(null, "读者姓名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(type)){
			JOptionPane.showMessageDialog(null, "身份类别不能为空！");
			return;
		}
		LibraryCard library_card=new LibraryCard(username,company,type);
		Connection conn=null;
		try{
			conn=dbUtil.initConnection();
			int flag=librarycardDao.Insert(conn, library_card);
			if(flag==1){
				JOptionPane.showMessageDialog(null, "借书证添加成功！");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "借书证添加失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "借书证添加失败！");
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
	 * 重置事件处理
	 * @param event
	 */
	private void resetValueActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		this.resetValue();
	}
	
	/**
	 * 重置操作
	 */
	private void resetValue() {
		// TODO Auto-generated method stub
		this.UserNameTxt.setText("");
		this.CompanyTxt.setText("");
		this.TypeTxt.setText("");
	}
	
}
