package liang.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrm frame = new MainMenuFrm();
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
	public MainMenuFrm() {
		setTitle("图书管理系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("图书管理系统");
		label.setFont(new Font("华文新魏", Font.BOLD, 36));
		
		JButton button = new JButton("读者登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CardCheckFrm cardCheckFrm = new CardCheckFrm();
				cardCheckFrm.setVisible(true);
			}
		});
		
		JButton button_1 = new JButton("管理员登录");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LogOnFrm logOnFrm = new LogOnFrm();
				logOnFrm.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(98)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(156)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(button)
					.addGap(39)
					.addComponent(button_1)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		// 设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}
}
