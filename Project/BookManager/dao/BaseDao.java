package liang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 通用dao，所有其余dao均从此类继承
 * 包含两个通用方法：查询&更新
 * @author liang
 *
 */
public class BaseDao { 
	
	public int update(Connection conn, String sql, Object[] params){
		PreparedStatement pstmt = null;
		try {
			//创建执行命令的stmt对象
			pstmt = conn.prepareStatement(sql);
			//通过参数元数据，得到占位参数的个数
			int count = pstmt.getParameterMetaData().getParameterCount();
			//设置占位符参数的值
			if(params != null && params.length>0){
				//循环给参数赋值
				for(int i=0; i<count; i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			//执行更新
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ResultSet query(Connection conn, String sql, Object[] params){
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 //创建执行命令的stmt对象
			 pstmt = conn.prepareStatement(sql);
			 //通过参数元数据，得到占位参数的个数
			 int paramsCount = pstmt.getParameterMetaData().getParameterCount();
			 //设置占位符参数的值
			 if(params!=null && params.length>0){
				 //循环给参数赋值
				 for(int i=0; i<paramsCount; i++){
					 pstmt.setObject(i+1, params[i]);
				 }
			 }
			 //执行查询
			 rs = pstmt.executeQuery();
			 return rs;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	 }

} 