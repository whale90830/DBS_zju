package liang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import liang.model.LibraryCard;
import liang.util.StringUtil;


public class LibraryCardDao extends BaseDao{

	/**
	 * 添加借书证
	 * @param conn
	 * @param card
	 * @return
	 * @throws Exception
	 */
	public int Insert(Connection conn, LibraryCard card)throws Exception{
		String sql = "insert into library_card(Card_ID, Username, Company, Type) values(?, ?, ?, ?)";
		Object[] params = {card.getCard_id(), card.getUsername(), card.getCompany(), card.getType()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 根据id删除借书证
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int Delete(Connection conn, int id)throws Exception{
		String sql = "delete from library_card where Card_ID = ?";
		Object[] params = {id};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	

	/**
	 * 修改借书证信息
	 * @param conn
	 * @param card
	 * @return
	 * @throws Exception
	 */
	public int Update(Connection conn, LibraryCard card)throws Exception{
		String sql = "update library_card set Username=?, Company=?, Type=? where Card_ID=?";
		Object[] params = {card.getUsername(), card.getCompany(), card.getType(), card.getCard_id()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 查询所有借书证信息
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public ResultSet SelectAll(Connection conn) throws Exception{
		String sql = "select * from library_card";
		ResultSet rs = super.query(conn, sql, null);
		return rs;
	}
	
	/**
	 * 根据借书证id
	 * @param conn
	 * @param Card_ID
	 * @return
	 * @throws Exception
	 */
	public ResultSet SelectCard_ID(Connection conn, int Card_ID) throws Exception{
		String sql = "select * from library_card where Card_ID = ?";
		Object[] params = {Card_ID};
		ResultSet rs = super.query(conn, sql, params);
		return rs;
	}
	
	
	
	public ResultSet Select(Connection conn, LibraryCard card)throws Exception{
		StringBuffer sql=new StringBuffer("select * from library_card");
		if(card.getCard_id()!=0){
			sql.append(" and Card_ID="+card.getCard_id());
		}
		if(StringUtil.isNotEmpty(card.getUsername())){
			sql.append(" and Username like '%"+card.getUsername()+"%'");
		}
		if(StringUtil.isNotEmpty(card.getCompany())){
			sql.append(" and Company like '%"+card.getCompany()+"%'");
		}
		if(StringUtil.isNotEmpty(card.getCompany())){
			sql.append(" and Type='"+card.getCompany()+"'");
		}
		PreparedStatement pstmt=conn.prepareStatement(sql.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

}
