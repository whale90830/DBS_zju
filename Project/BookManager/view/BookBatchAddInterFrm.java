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

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import liang.dao.BookDao;
import liang.model.Book;
import liang.util.DbUtil;
import liang.util.StringUtil;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookBatchAddInterFrm extends JInternalFrame {
	private JTextField PathNameTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBatchAddInterFrm frame = new BookBatchAddInterFrm();
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
	public BookBatchAddInterFrm() {
		setTitle("批量入库");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 443, 260);
		
		JLabel label = new JLabel("文件路径：");
		
		PathNameTxt = new JTextField();
		PathNameTxt.setColumns(10);
		
		JButton button = new JButton("确认入库");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBatchAddActionPerformed(e);
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
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addGap(18)
							.addComponent(PathNameTxt, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(PathNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(53))
		);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 批量入库事件处理
	 * @param event
	 */
	private void BookBatchAddActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(PathNameTxt.getText())) {
			JOptionPane.showMessageDialog(null, "文件路径不能为空！");
			return;
		}
		String pathname = PathNameTxt.getText();
		File file = new File(pathname);
		BookBatchAdd(file);
	}
	
	
	/**
	 * 批量入库操作
	 * @param file
	 */
	public void BookBatchAdd(File file) {  
		Connection conn=null;
		try{
			conn=dbUtil.initConnection();
			try {  
	            // 创建输入流，读取Excel  
	            InputStream is = new FileInputStream(file.getAbsolutePath());  
	            // jxl提供的Workbook类  
	            Workbook wb = Workbook.getWorkbook(is);  
	            // Excel的页签数量  
	            int sheet_size = wb.getNumberOfSheets();
	            String book_information[] = new String[8];
	            for (int index = 0; index < sheet_size; index++) {  
	                // 每个页签创建一个Sheet对象  
	                Sheet sheet = wb.getSheet(index);  
	                // sheet.getRows()返回该页的总行数  
	                for (int i = 0; i < sheet.getRows(); i++) {  
	                    // sheet.getColumns()返回该页的总列数
	                    for (int j = 0; j < 8; j++) {  
	                    	book_information[j] = String.valueOf(sheet.getCell(j, i).getContents());            	
	                    }
	                    Book book = new Book(book_information);
                    	bookDao.Insert(conn, book);
	                }  
	            }
	            JOptionPane.showMessageDialog(null, "批量入库成功！");
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "批量入库失败！");
	        } catch (BiffException e) {  
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "批量入库失败！");
	        } catch (IOException e) {  
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "批量入库失败！");
	        }
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "批量入库失败！");
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
		this.PathNameTxt.setText("");
	}

}
