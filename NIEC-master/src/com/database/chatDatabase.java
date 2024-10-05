package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class chatDatabase {

	Connection con;
	PreparedStatement pst;
	
	public chatDatabase() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNIEC","root","s");
	}
	
	
	public void addMessage(String id,String msg,String sender) throws SQLException{
		pst=con.prepareStatement("insert into CHAT_"+id+"(MESSAGE,SENDER,TIME) values(?,?,?)");
		pst.setString(1, msg);
		pst.setString(2, sender);DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		pst.setString(3,dateFormat.format(date));
		int result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
		}
	
	public ResultSet showChat(String id) throws SQLException{
		pst=con.prepareStatement("select * from CHAT_"+id);
		ResultSet rs=pst.executeQuery();
		
		return rs;
	}
	
	
	public void closeConnection() throws SQLException{
		if(pst!=null){
		pst.close();
		}if(con!=null){
		con.close();
		}
	}
	
	public String showMembers(String id) throws SQLException{
		pst=con.prepareStatement("select MEMBERLIST from CHATROOMS where CHATID=?");
		pst.setString(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getString("MEMBERLIST");
		
	}
	
	
	
	
	
	
	
	
	
	
}
