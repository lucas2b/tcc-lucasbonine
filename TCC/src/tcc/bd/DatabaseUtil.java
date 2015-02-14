package tcc.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseUtil {

	private static Connection conn = null;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		if(conn == null){
			 Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dump42", "root", "root");
             //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pesquisa_wagner", "root", "root");
             //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pesquisa_filipe", "root", "root");
		}
		return conn;
	}
	
	public Statement getStatement() throws ClassNotFoundException, SQLException{
		return getConnection().createStatement();
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException{
		return getConnection().prepareStatement(sql);
	}
	
	public void closeAll() throws SQLException{
		conn.close();
	}
	
}
