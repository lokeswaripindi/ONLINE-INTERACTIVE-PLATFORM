package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.decodingStudent;

public class chatRoomDatabase {

	Connection con;
	decodingStudent ds;
	PreparedStatement pst;
	
	public chatRoomDatabase() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		ds=new decodingStudent();
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNIEC","root","s");
	}
	
	public String addChat(String memberList,String name) throws Exception{
		pst=con.prepareStatement("insert into CHATROOMS(MEMBERLIST,NAME,DATE1) values(?,?,?)");
		pst.setString(1, memberList);
		pst.setString(2, name);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		pst.setString(3,dateFormat.format(date));
		int result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
		pst=con.prepareStatement("select CHATID from CHATROOMS order by CHATID desc limit 1");
		ResultSet rs=pst.executeQuery();
		rs.next();
		String id1= rs.getString("CHATID"),sub="";
		try{
		for(int i=0;i<memberList.length();i=i+4){
			sub=memberList.substring(i,i+4);
			addChatIdToMember(sub,id1);
		}
		pst=con.prepareStatement("create table CHAT_"+id1+" (MID int(10) auto_increment primary key,MESSAGE varchar(500),SENDER varchar(30),TIME DATETIME); ");
		 result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
		}catch(Exception e){
			delete(id1);
			return id1;
		}
		
		return id1;
	}
	
	public void addMemberToChat(String hex,String id) throws Exception{
		if(hex.length()==4){
		pst=con.prepareStatement("update CHATROOMS set MEMBERLIST=concat(MEMBERLIST,?) where CHATID=?");
		pst.setString(1, hex);
		pst.setString(2, id);
		int result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
		addChatIdToMember(hex,id);
		}
	}
	
	public void addChatIdToMember(String hex,String id) throws Exception{
		
		if(hex.charAt(0)=='|'){
			pst=con.prepareStatement("update TEACHER set CHATID=concat(CHATID,?) where T_ID=?");
		}else{
			String branch=ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(hex)));
		pst=con.prepareStatement("update STUDENT_"+branch+" set CHATID=concat(CHATID,?) where S_HEXCODE=?");
		}
		pst.setString(1,id+"#");
		pst.setString(2,hex);
		int result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
	}
	
	public void deleteChat(String id) throws SQLException{
		pst=con.prepareStatement("select MEMBERLIST from CHATROOMS where CHATID=?");
		pst.setString(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String list=rs.getString("MEMBERLIST");
		if(list.equals("")){
			pst=con.prepareStatement("delete from CHATROOMS where CHATID=?");
			pst.setString(1, id);
			int result=pst.executeUpdate();
			System.out.println(result +" Records Affected");
			pst=con.prepareStatement("drop table CHAT_"+id);
			 result=pst.executeUpdate();
			System.out.println(result +" Records Affected");
		}
		
	}
	
	public void delete(String id) throws SQLException{
		
			pst=con.prepareStatement("delete from CHATROOMS where CHATID=?");
			pst.setString(1, id);
			int result=pst.executeUpdate();
			System.out.println(result +" Records Affected");
			pst=con.prepareStatement("drop table CHAT_"+id);
			 result=pst.executeUpdate();
			System.out.println(result +" Records Affected");
		
		
	}
	
	
	
	
	
	
	public void removeMember(String id,String hex) throws Exception{

		pst=con.prepareStatement("select MEMBERLIST from CHATROOMS where CHATID=?");
		pst.setString(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String list=rs.getString("MEMBERLIST");
		String list1=list;
		for(int i=0;i<list.length();i=i+4){
			if(list.substring(i,i+4).equals(hex)){
				 list1=list.replace(hex,"");
			}
		}
		pst=con.prepareStatement("update CHATROOMS set MEMBERLIST=? where CHATID=?");
		pst.setString(1, list1);
		pst.setString(2, id);
		int result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
		
		String branch="";
		if(hex.charAt(0)=='|'){
			pst=con.prepareStatement("select CHATID from TEACHER where T_ID=?");
		}else{
			branch=ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(hex)));
			pst=con.prepareStatement("select CHATID from STUDENT_"+branch+" where S_HEXCODE=?");
		}
		pst.setString(1, hex);
		 rs=pst.executeQuery();
		 rs.next();
		list=rs.getString("CHATID");
		String ids[]=list.split("#");
		for(int i=0;i<ids.length;i++){
			if(ids[i].equals(id)){
				list1=list.replace(ids[i]+"#","");
			}
		}
		if(hex.charAt(0)=='|'){
			pst=con.prepareStatement("update TEACHER set CHATID=? where T_ID=?");
		}else{
			pst=con.prepareStatement("update STUDENT_"+branch+" set CHATID=? where S_HEXCODE=?");
		}
		
		pst.setString(1,list1);
		pst.setString(2,hex);
		result=pst.executeUpdate();
		System.out.println(result +" Records Affected");
		deleteChat(id);
		
	}
	
	public ResultSet fetchAll(String id) throws SQLException{
		pst=con.prepareStatement("select * from CHATROOMS where CHATID=?");
		pst.setString(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs;
	}
	
	
	public void closeConnection() throws SQLException{
		if(pst!=null){
		pst.close();
		}if(con!=null){
		con.close();
		}
	}
	
	
	
	
	
	
}
