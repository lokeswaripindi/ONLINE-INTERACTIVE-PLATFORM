package com.database;
import java.sql.*;
import com.entity.*;

public class teacherDatabase {
	
	Connection con;
	PreparedStatement pst;
	decodingStudent ds;
	public teacherDatabase() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNIEC","root","s");
		ds=new decodingStudent();
	}
	
	public ResultSet fetchAll(String id) throws SQLException{

		pst=con.prepareStatement("select * from TEACHER where T_ID=?"); 
			pst.setString(1,id);
			ResultSet rs=pst.executeQuery();
			rs.next();
			return rs;
		}
	
	public void addSentList(String id,String friendCode) throws SQLException{
		pst=con.prepareStatement("update TEACHER set T_SENTLIST=concat(T_SENTLIST,?) where T_ID=?");
		pst.setString(1,friendCode);
		pst.setString(2, id);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	                       //01215602713     //||24
	public void addPendingList(String id,String friendCode)throws SQLException,Exception{
		
		pst=con.prepareStatement("update TEACHER set T_PENDINGLIST=concat(T_PENDINGLIST,?) where T_ID=?");
		pst.setString(1,friendCode);
		pst.setString(2, id);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	
	public void removePendingList(String id,String friendCode) throws Exception{
		String list;
		pst=con.prepareStatement("select T_PENDINGLIST from TEACHER where T_ID=?");
			pst.setString(1,id);
			ResultSet rs=pst.executeQuery();
			rs.next();
			list=rs.getString("T_PENDINGLIST");
			String temp="";
			for(int i=0;i<list.length();i=i+4){
				if(!list.substring(i,i+4).equals(friendCode)){
					temp=temp+list.substring(i,i+4);
				}
			}
			
			pst=con.prepareStatement("update TEACHER  set T_PENDINGLIST=? where T_ID=?");
			pst.setString(1,temp);
			pst.setString(2,id);
			int result=pst.executeUpdate();
				System.out.println(result+" Records Affected.");
		}
	
	
	public void removeSentList(String id,String friendCode) throws Exception{
		String list;
		pst=con.prepareStatement("select T_SENTLIST from TEACHER where T_ID=?");
			pst.setString(1,id);
			ResultSet rs=pst.executeQuery();
			rs.next();
			list=rs.getString("T_SENTLIST");
			String temp="";
			for(int i=0;i<list.length();i=i+4){
				if(!list.substring(i,i+4).equals(friendCode)){
					temp=temp+list.substring(i,i+4);
				}
			}
			
			pst=con.prepareStatement("update TEACHER  set T_SENTLIST=? where T_ID=?");
			pst.setString(1,temp);
			pst.setString(2,id);
			int result=pst.executeUpdate();
				System.out.println(result+" Records Affected.");
		}
	
	public void addFriendToTeacher(String id,String friendId) throws SQLException{
		if(friendId.charAt(0)=='|'){
			pst=con.prepareStatement("update TEACHER set T_TEACHERLIST=concat(T_TEACHERLIST,?) where T_ID=?");
		}else{
			pst=con.prepareStatement("update TEACHER set T_STUDENTLIST=concat(T_STUDENTLIST,?) where T_ID=?");
		}
		pst.setString(1,friendId);
		pst.setString(2,id);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	public 	String fetchStudentList(String id) throws SQLException{
		pst=con.prepareStatement("select T_STUDENTLIST from TEACHER where T_ID=?");
		pst.setString(1,id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getString("T_STUDENTLIST");
	}
	
	public String fetchTeacherList(String id ) throws SQLException{
		pst=con.prepareStatement("select T_TEACHERLIST from TEACHER where T_ID=?");
		pst.setString(1,id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getString("T_TEACHERLIST");
	}
	
	public Boolean isFriend(String id,String friendId) throws Exception{
		String list;
		if(friendId.charAt(0)=='|'){   //TEACHER
			list=fetchTeacherList(id);
		}else{
			friendId=ds.rollToHex(friendId);
		list=fetchStudentList(id);
		}
		if(((list.indexOf(friendId))!=-1) && ((list.indexOf(friendId)%4)==0)){
				return true;
			}
			return false;
		}
	
	
	/*	
	public void addTeacher(String t[]) throws SQLException{
			pst=con.prepareStatement("insert into TEACHER(T_ID,T_NAME,T_PASSWORD,T_BRANCH,T_PHONE,T_CLASSES,T_EMAIL,T_GENDER,T_STUDENTLIST,T_TEACHERLIST,T_PENDINGLIST,T_SENTLIST,T_ABOUTME) values("
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?)");

			for(int i=1;i<=13;i++){
				pst.setString(i,t[i-1]);
			}
			int result=pst.executeUpdate();
			System.out.println(result+" Records Affected.");
		
		}
	*/
	
	public void addTeacher(String t[]) throws SQLException{
		pst=con.prepareStatement("insert into TEACHER(T_NAME,T_PASSWORD,T_BRANCH,T_PHONE,T_CLASSES,T_EMAIL,T_GENDER,T_STUDENTLIST,T_TEACHERLIST,T_PENDINGLIST,T_SENTLIST,T_ABOUTME) values("
				+ "?,?,?,?,?,?,?,?,?,?,?,?)");

		for(int i=1;i<=12;i++){
			pst.setString(i,t[i-1]);
		}
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		
		
		//select SNO from TEACHER order by SNO desc limit 1;
		
		pst=con.prepareStatement("select SNO from TEACHER order by SNO desc limit 1");
        ResultSet rs=pst.executeQuery();
		rs.next();
		String sno=rs.getString("SNO");
		System.out.println("Sno is."+sno);
		if(Integer.parseInt(sno)<100)
			sno=Integer.parseInt(sno)+100+"";
		String t_id="|"+sno;
		String sql="update TEACHER set T_ID=? where SNO=?";
		
		System.out.println(sql);
		pst=con.prepareStatement(sql);
		pst.setString(1,t_id);	
		pst.setString(2,sno);
		result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	
	}
	
	public void deleteTeacher(String id) throws SQLException{
		pst=con.prepareStatement("delete from TEACHER where T_ID=?");
		pst.setString(1, id);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	
	public void updateTeacher(String id,String t[]) throws SQLException{
		pst=con.prepareStatement("select * from TEACHER where T_ID=?");
		pst.setString(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		System.out.println("email "+t[5]+" gender "+t[6]);
		String sql="update TEACHER set T_NAME=?,T_PASSWORD=?,T_BRANCH=?,T_PHONE=?,T_CLASSES=?,T_EMAIL=?,T_GENDER=?,"
				+ "T_STUDENTLIST=?,T_TEACHERLIST=?,T_PENDINGLIST=?,T_SENTLIST=?,T_ABOUTME=? where T_ID=? ";
		pst=con.prepareStatement(sql);
		for(int i=1;i<=12;i++){
			System.out.println(i-1+"- "+t[i-1]);
			if(!t[i-1].equals("")){
				pst.setString(i,t[i-1]);
			}else{
				pst.setString(i,rs.getString(i+1));
			}
		}
		pst.setString(13, id);
		System.out.println(sql);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	public Boolean loginTeacher(String roll,String pass) throws Exception,SQLException{
		pst=con.prepareStatement("select * from TEACHER where T_ID=? and T_PASSWORD=?");
		pst.setString(1, roll);
		pst.setString(2, pass);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	
	public boolean isPending(String id,String roll) throws Exception{
		String hex=roll;
		if(roll.charAt(0)!='|'){
			hex=ds.rollToHex(roll);
		}
		pst=con.prepareStatement("select T_PENDINGLIST from TEACHER where T_ID=?");
		pst.setString(1,id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String list=rs.getString("T_PENDINGLIST");
		if(((list.indexOf(hex))!=-1) && ((list.indexOf(hex)%4)==0)){
			return true;
		}
		return false;
	}
	
	public boolean isSent(String id,String fid) throws Exception{
		pst=con.prepareStatement("select T_SENTLIST from TEACHER where T_ID=?");
		pst.setString(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		if(fid.charAt(0)!='|'){
			fid=ds.rollToHex(fid);
		}
		
		String list=rs.getString("T_SENTLIST");
		
		if(((list.indexOf(fid))!=-1) && ((list.indexOf(fid)%4)==0)){
			return true;
		}
		return false;
	}
	
	public void removeFriend(String id,String hex) throws Exception{
		String list,temp="";
		if(hex.charAt(0)=='|'){
			pst=con.prepareStatement("select T_TEACHERLIST from TEACHER where T_ID=?");
			pst.setString(1,id);
		     ResultSet rs=pst.executeQuery();
			rs.next();
			list=rs.getString("T_TEACHERLIST");
		}else{
			pst=con.prepareStatement("select T_STUDENTLIST from TEACHER where T_ID=?");
			pst.setString(1,id);
		     ResultSet rs=pst.executeQuery();
			rs.next();
			list=rs.getString("T_STUDENTLIST");
			
		}
		for(int i=0;i<list.length();i=i+4){
			if(!list.substring(i,i+4).equals(hex)){
			temp=temp+list.substring(i,i+4);
		}
		}
		if(hex.charAt(0)=='|'){
		pst=con.prepareStatement("update TEACHER set T_TEACHERLIST=? where T_ID=?");
		}else{
			pst=con.prepareStatement("update TEACHER set t_STUDENTLIST=? where T_ID=?");
		}
		System.out.println("After Removing "+hex+" from "+ temp);
		pst.setString(1, temp);
		pst.setString(2,id);		
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		
	}
	

	public ResultSet all() throws SQLException{
		pst=con.prepareStatement("select * from TEACHER");
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
	
	


}
	



