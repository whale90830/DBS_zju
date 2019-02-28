package liang.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import liang.dao.ManagerDao;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField ManagerNameTxt;
	private JPasswordField PassWordTxt;
	
	private DbUtil dbUtil=new DbUtil();
	private ManagerDao managerDao=new ManagerDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {
		
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		setTitle("管理员登录");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("图书管理系统");
		lblNewLabel.setFont(new Font("华文新魏", Font.BOLD, 36));
		
		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel label_1 = new JLabel("密  码：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		ManagerNameTxt = new JTextField();
		ManagerNameTxt.setColumns(10);
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		PassWordTxt = new JPasswordField();
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(119)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(label, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(ManagerNameTxt, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(label_1)
												.addGap(18)
												.addComponent(PassWordTxt, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))))))
					.addGap(107))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(ManagerNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(PassWordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button))
					.addGap(54))
		);
		contentPane.setLayout(gl_contentPane);
		
		// 设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}

	/**
	 * 登录事件处理
	 * @param event
	 */
	private void loginActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String manager_name=this.ManagerNameTxt.getText();
		String password=new String(this.PassWordTxt.getPassword());
		if(StringUtil.isEmpty(manager_name)){
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		Manager manager=new Manager(manager_name,password);
		Connection conn=null;
		try {
			conn=dbUtil.initConnection();
			Manager currentManager=managerDao.login(conn, manager);
			if(currentManager!=null){
				dispose();
				new MainFrm().setVisible(true);
				MainFrm.manager_id = currentManager.getManager_id();
			}else{
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
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

	/**
	 * 重置事件处理
	 * @param event
	 */
	private void resetValueActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		this.ManagerNameTxt.setText("");
		this.PassWordTxt.setText("");
	}
}
