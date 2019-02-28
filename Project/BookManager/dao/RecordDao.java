package liang.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import liang.model.Record;

public class RecordDao extends BaseDao{


	/**
	 * 插入借书记录
	 * @param conn
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int Insert(Connection conn, Record record)throws Exception{
		String sql = "insert into record(Book_ISBN, Card_ID, Manager_ID) values(?, ?, ?)";
		Object[] params = {record.getBook_ISBN(), record.getCard_id(), record.getManager_id()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 根据书号&借书证号删除借书记录
	 * @param conn
	 * @param ISBN
	 * @param Card_ID
	 * @return
	 * @throws Exception
	 */
	public int Delete(Connection conn, String ISBN, int Card_ID)throws Exception{
		String sql = "delete from record where Book_ISBN=? and Card_ID=?";
		Object[] params = {ISBN, Card_ID};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * @param conn
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int Update(Connection conn, Record record)throws Exception{
		String sql = "update record set Borrow_date=?, Return_date=?, Manager_ID=? where Book_ISBN=? and Card_ID=?";
		Object[] params = {record.getBorrow_date(), record.getReturn_date(), record.getManager_id()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 根据借书证号和书号查询借书记录
	 * @param conn
	 * @param ISBN
	 * @param Card_ID
	 * @return
	 */
	public ResultSet Select(Connection conn, String ISBN, int Card_ID){
		String sql = "select * from record where Book_ISBN=? and Card_ID=?";
		Object[] params = {ISBN, Card_ID};
		ResultSet rs = super.query(conn, sql, params);
		return rs;
	}
	
	/**
	 * 根据借书证号查询借书记录
	 * @param conn
	 * @param Card_ID
	 * @return
	 */
	public ResultSet Select(Connection conn, int Card_ID){
		String sql = "select * from record where Card_ID=?";
		Object[] params = {Card_ID};
		ResultSet rs = super.query(conn, sql, params);
		return rs;
	}
	
}
