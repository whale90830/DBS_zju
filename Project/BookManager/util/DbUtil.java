package liang.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/db_book?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
	//  Database credentials
	private static final String USER = "root";
	private static final String PASSWORD = "281343";
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
    public Connection initConnection() throws Exception{  
        Class.forName(JDBC_DRIVER);  
        Connection conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
        return conn;  
    }
	
    /**
     * 关闭数据库连接
     * @param conn
     * @throws Exception
     */
    public void closeConnection(Connection conn) throws Exception{  
        if(conn!=null&&!conn.isClosed()){  
            conn.close();  
            conn=null;  
        }  
    }
    
//    public static void main(String[] args) {
//    	DbUtil  dbUtil = new DbUtil ();
//    	try {
//    		Connection conn=dbUtil.initConnection();
//			System.out.println("数据库连接成功");
//			dbUtil.closeConnection(conn);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("数据库连接失败");
//		}
//	}
}
