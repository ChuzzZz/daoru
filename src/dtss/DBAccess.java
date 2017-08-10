package dtss;
import java.sql.*;

import model.User;

public class DBAccess {
	Connection conn;

	public DBAccess() {
		try {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("驱动加载失败！");
		}
		try {
			conn =DriverManager.getConnection("jdbc:mysql://localhost/company?useSSL=false&" +
			        "user=naive&password=123456");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("数据库连接失败！");
			}
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean insertPerformance(int id,String pname,String performer,String time,String apartment){
		try {
			CallableStatement cStmt = conn.prepareCall("{call insertPerformance(?,?,?,?,?)}");
			cStmt.setInt(1, id);
			cStmt.setString(2, pname);
			cStmt.setString(3, performer);
			cStmt.setString(4, time);
			cStmt.setString(5, apartment);
			if(!cStmt.execute()){
				return false;
			}
			cStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean insertUser(User u){
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement("insert into user values(?,?,?,?)");
			pStmt.setInt(1, u.getId());
			pStmt.setInt(2, u.getItcode());
			pStmt.setString(3, u.getName());
			pStmt.setInt(4, u.getOnsite());
			pStmt.execute();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		
	}
	
}
