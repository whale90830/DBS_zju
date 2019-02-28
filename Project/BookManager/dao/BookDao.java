package liang.dao;

import java.sql.*;

import liang.model.Book;
import liang.util.StringUtil;

public class BookDao extends BaseDao{  
	
	/**
	 * 添加书籍
	 * @param conn
	 * @param book
	 * @throws Exception
	 */
	public int Insert(Connection conn, Book book)throws Exception{
		ResultSet rs = SelectISBN(conn, book.getISBN());
		if(rs.next()){
			String sql = "update book set Number=?, Stock=?  where ISBN=?";
			Object[] params = {book.getNumber() + rs.getInt("Number"), book.getStock() + rs.getInt("Stock"), book.getISBN()};
			return super.update(conn, sql, params);//调用父类BaseDao中的方法
		}
		else{
			String sql = "insert into book(ISBN, Type, Title, Publisher, Publish_year, Author, Price, Number, Stock) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Object[] params = {book.getISBN(), book.getType(), book.getTitle(), book.getPublisher(), book.getPublish_year(), book.getAuthor(), book.getPrice(), book.getNumber(), book.getStock()};
			return super.update(conn, sql, params);//调用父类BaseDao中的方法
		}
	}
	
	/**
	 * 根据书号删除书籍
	 * @param conn
	 * @param ISBN
	 * @throws Exception
	 */
	public int Delete(Connection conn, String ISBN)throws Exception{
		String sql = "delete from book where ISBN = ?";
		Object[] params = {ISBN};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 修改书籍信息
	 * @param conn
	 * @param book
	 * @throws Exception
	 */
	public int Update(Connection conn, Book book)throws Exception{
		String sql = "update book set Type=?, Titil=?, Publisher=?, Publish_year=?, Author=?, Price=?, Number=?, Stock=?  where ISBN=?";
		Object[] params = {book.getType(), book.getTitle(), book.getPublisher(), book.getPublish_year(), book.getAuthor(), book.getPrice(), book.getNumber(), book.getStock(), book.getISBN()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 查询所有书籍信息
	 * @param conn
	 * @return
	 */
	public ResultSet SelectAll(Connection conn) throws Exception{
		String sql = "select * from book";
		ResultSet rs = super.query(conn, sql, null);
		return rs;
	}
	
	/**
	 * 根据书号查询
	 * @param conn
	 * @param ISBN
	 * @return
	 */
	public ResultSet SelectISBN(Connection conn, String ISBN) throws Exception{
		String sql = "select * from book where ISBN = ?";
		Object[] params = {ISBN};
		ResultSet rs = super.query(conn, sql, params);
		return rs;
	}
	
	/**
	 * 图书信息查询
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Book book) throws Exception{
		StringBuffer sql=new StringBuffer("select * from book where book.Type=type.Book_type");
		if(StringUtil.isNotEmpty(book.getTitle())){
			sql.append(" and book.Title like '%"+book.getTitle()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getPublisher())){
			sql.append(" and book.Publisher like '%"+book.getPublisher()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())){
			sql.append(" and book.Author like '%"+book.getAuthor()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
//	public static void main(String[] args) {
//		
//		DbUtil dbUtil = new DbUtil();
//		Connection conn;
//		try {
//			conn = dbUtil.initConnection();
//			BookDao bookdao = new BookDao();
//			try {
//				ResultSet rs = bookdao.SelectAll(conn);
//				ResultSetMetaData resultSetMD = rs.getMetaData();
//				int column_count = resultSetMD.getColumnCount();
//				while(rs.next()) {
//					for(int i=1;i<column_count+1;i++)
//					{
//						System.out.println(rs.getString(i));
//					}				
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				dbUtil.closeConnection(conn);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}  