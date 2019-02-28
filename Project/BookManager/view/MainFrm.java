package liang.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

public class MainFrm extends JFrame {
	
	public static int manager_id;

	private JPanel contentPane;
	private JDesktopPane table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("图书管理系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 552);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("图书查询");
		menuBar.add(menu);
		
		JMenuItem menuItem_3 = new JMenuItem("馆藏查询");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearchInterFrm bookSearchInterFrm = new BookSearchInterFrm();
				bookSearchInterFrm.setVisible(true);
				table.add(bookSearchInterFrm);
			}
		});
		menu.add(menuItem_3);
		
		JMenu menu_1 = new JMenu("图书管理");
		menuBar.add(menu_1);
		
		JMenu menu_3 = new JMenu("图书入库");
		menu_1.add(menu_3);
		
		JMenuItem menuItem_6 = new JMenuItem("单本入库");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddInterFrm bookAddInterFrm = new BookAddInterFrm();
				bookAddInterFrm.setVisible(true);
				table.add(bookAddInterFrm);
			}
		});
		menu_3.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("批量入库");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBatchAddInterFrm bookBatchAddInterFrm = new BookBatchAddInterFrm();
				bookBatchAddInterFrm.setVisible(true);
				table.add(bookBatchAddInterFrm);
			}
		});
		menu_3.add(menuItem_7);
		
		JMenuItem menuItem_1 = new JMenuItem("借阅书籍");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBorrowInterFrm bookBorrowInterFrm = new BookBorrowInterFrm();
				bookBorrowInterFrm.manager_id = manager_id;
				bookBorrowInterFrm.setVisible(true);
				table.add(bookBorrowInterFrm);
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("归还书籍");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookReturnInterFrm bookReturnInterFrm = new BookReturnInterFrm();
				bookReturnInterFrm.setVisible(true);
				table.add(bookReturnInterFrm);
			}
		});
		menu_1.add(menuItem_2);
		
		JMenu menu_2 = new JMenu("借书证管理");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("添加借书证");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardAddInterFrm cardAddInterFrm = new CardAddInterFrm();
				cardAddInterFrm.setVisible(true);
				table.add(cardAddInterFrm);
			}
		});
		menu_2.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("管理借书证");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardManageInterFrm cardManageInterFrm = new CardManageInterFrm();
				cardManageInterFrm.setVisible(true);
				table.add(cardManageInterFrm);
			}
		});
		menu_2.add(menuItem_5);
		
		JMenu menu_4 = new JMenu("帮助");
		menuBar.add(menu_4);
		
		JMenuItem menuItem = new JMenuItem("安全退出");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "是否退出系统");
				if(result==0){
					dispose();
				}
			}
		});
		menu_4.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JDesktopPane();
		contentPane.add(table, BorderLayout.CENTER);
		
		// 设置JFrame最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
