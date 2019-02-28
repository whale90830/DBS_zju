package liang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import liang.model.Manager;

public class ManagerDao extends BaseDao{

	/**
	 * 全信息添加管理员
	 * @param conn
	 * @param manager
	 * @throws Exception
	 */
	public int Insert_all(Connection conn, Manager manager)throws Exception{
		String sql = "insert into manager(Manager_ID, Name, Password, Connection) values(?, ?, ?, ?)";
		Object[] params = {manager.getManager_id(), manager.getName(), manager.getPassword(), manager.getConnection()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 添加id外的其余管理员信息
	 * @param conn
	 * @param manager
	 * @throws Exception
	 */
	public int Insert_autoID(Connection conn, Manager manager)throws Exception{
		String sql = "insert into manager(Name, Password, Connection) values(?, ?, ?, ?)";
		Object[] params = {manager.getName(), manager.getPassword(), manager.getConnection()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 根据id删除管理员
	 * @param conn
	 * @param id
	 * @throws Exception
	 */
	public int Delete(Connection conn, int id)throws Exception{
		String sql = "delete from manager where Manager_ID = ?";
		Object[] params = {id};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 修改管理员信息
	 * @param conn
	 * @param manager
	 * @throws Exception
	 */
	public int Update(Connection conn, Manager manager)throws Exception{
		String sql = "update manager set Name=?, Password=?, Connection=? where Manager_ID=?";
		Object[] params = {manager.getName(), manager.getPassword(), manager.getConnection(), manager.getManager_id()};
		return super.update(conn, sql, params);//调用父类BaseDao中的方法
	}
	
	/**
	 * 查询所有管理员信息
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public ResultSet SelectAll(Connection conn) throws Exception{
		String sql = "select * from manager";
		ResultSet rs = super.query(conn, sql, null);
		return rs;
	}
	
	/**
	 * 管理员登录验证
	 * @param con
	 * @param manager
	 * @return
	 * @throws Exception
	 */
	public Manager login(Connection conn,Manager manager)throws Exception{
		Manager resultManager=null;
		String sql="select * from manager where Name=? and Password=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, manager.getName());
		pstmt.setString(2, manager.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultManager=new Manager();
			resultManager.setManager_id(rs.getInt("Manager_ID"));
			resultManager.setPassword(rs.getString("Password"));
			resultManager.setName(rs.getString("Name"));
			resultManager.setConnection(rs.getString("Connection"));
		}
		return resultManager;
	}
	
}
