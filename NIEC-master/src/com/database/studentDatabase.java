package com.database;


import java.sql.*;
import java.util.HashMap;

import com.entity.*;

public class studentDatabase {
	decodingStudent ds;
	Connection con;
	//Statement smt;
	PreparedStatement pst;
	teacherDatabase td;
	societyDatabase sd;
	
	public studentDatabase() throws Exception,ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		ds=new decodingStudent();
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNIEC","root","s");
		//smt=con.createStatement();
		td=new teacherDatabase();
		sd=new societyDatabase();
	}

	
	
//**********************SELECT***********************
	
	public ResultSet fetchAll(String roll,String branch) throws SQLException{
		//System.out.println("fetchAll student roll- "+roll+" branch is - "+branch);
	pst=con.prepareStatement("select * from STUDENT_"+branch+" where S_ROLL=?");  //add concatenation to student - csse
		pst.setString(1,roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs;
	}
	
	public ResultSet fetchOtherFriendList(String roll,String branch) throws SQLException{

		pst=con.prepareStatement("select S_OTHERFRIENDLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1,roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		/*String list="";
		if(rs.next()){
		 list =rs.getString("S_OTHERFRIENDLIST");
		}
		System.out.println("friendList is- "+list);*/
		return rs;
	}
	
	public ResultSet fetchNameRoll(String hex,String branch) throws SQLException{
		
			pst=con.prepareStatement("select S_NAME,S_ROLL from STUDENT_"+branch+" where S_HEXCODE=?");
			pst.setString(1,hex);
			ResultSet rs=pst.executeQuery();
			rs.next();
			return rs;
		
	}
	
	public Boolean loginStudent(String roll,String pass) throws Exception,SQLException{
		String branch=ds.branchName(ds.findBranchFromRoll(roll));
		pst=con.prepareStatement("select * from STUDENT_"+branch+" where S_ROLL=? and S_PASSWORD=?");
		pst.setString(1, roll);
		pst.setString(2, pass);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	public ResultSet fetchTeacherList(String roll,String branch) throws SQLException{
		pst=con.prepareStatement("select S_TEACHERLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1, roll);
		
		ResultSet rs=pst.executeQuery();
		rs.next();
	/*	String tchr= rs.getString("S_TEACHERLIST");
	 * HashMap<String,String> hm=new HashMap<String,String>();
		System.out.println("TeacherList in Student "+tchr);
		String temp="";
		ResultSet rs1;
		System.out.println("before loop");
		for(int i=0;i<tchr.length();i=i+4){
			System.out.println("inside loop");
			temp=tchr.substring(i,i+4);
			rs1=td.fetchAll(temp);
			System.out.println("ID IS "+rs1.getString("T_ID"));
			hm.put(rs1.getString("T_ID"),rs1.getString("T_NAME"));
		}
		System.out.println("after loop");*/
		return rs;
	}
	
	public String fetchTeacherList(String roll,String branch,Boolean val) throws SQLException{
		pst=con.prepareStatement("select S_TEACHERLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1, roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String tchr= rs.getString("S_TEACHERLIST");
		return tchr;
	}
	
	
	

	
//*********************INSERT*******************
	

	public void insert(String roll,String val) throws SQLException{
		pst.setString(1,val);
		pst.setString(2,roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	
	public int insertPendingList(String roll,String hexcode,String branch) throws SQLException{
		
		pst=con.prepareStatement("update STUDENT_"+branch+" set S_PENDINGLIST=concat(S_PENDINGLIST,?) where S_ROLL=?");
		pst.setString(1,hexcode);
		pst.setString(2,roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		return result;
	}
	
	
	public int insertSentList(String roll,String hexcode,String branch) throws SQLException{ 
		
		pst=con.prepareStatement("update STUDENT_"+branch+" set S_SENTLIST=concat(S_SENTLIST,?) where S_ROLL=?");
		pst.setString(1,hexcode);
		pst.setString(2,roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		return result;
		
	}
	
	public void updateStudentAll(String s[],String branch) throws SQLException{
		
		pst=con.prepareStatement("select * from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1,s[0]);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String sect=rs.getString("S_SECTION");
		String hex=rs.getString("S_HEXCODE");

		String sql="update STUDENT_"+branch+ " set S_HEXCODE=?,S_NAME=?,S_PASSWORD=?,S_BRANCH=?,S_SECTION=?,S_SEMESTER=?,S_ADDRESS=?,S_PHONE=?,S_EMAIL=?,S_GENDER=?,"
				+ "S_OTHERFRIENDLIST=?,S_TEACHERLIST=?,S_PENDINGLIST=?,S_SENTLIST=?,S_ABOUTME=?,S_SOCIETY=?,CHATID=? where S_ROLL=?";
		pst=con.prepareStatement(sql);
		
		for(int i=1;i<=17;i++){
			if(!s[i].equals("")){
				pst.setString(i,s[i]);
			}else{
				pst.setString(i,rs.getString(i+1));
			}
		}
		pst.setString(18, s[0]);
		
		System.out.println(sql);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		
		pst=con.prepareStatement("select S_CLASSFRIENDS from CLASSINFO where S_SECTION=? and S_BRANCH=?");
		pst.setString(1,sect);
		pst.setString(2,branch);
		rs=pst.executeQuery();rs.next();
		String list=rs.getString("S_CLASSFRIENDS");
		String temp="",flist="";
		for(int i=0;i<list.length();i=i+4){
			flist=list.substring(i,i+4);
			if(!flist.equals(hex)){
				temp=temp+flist;
			}
		}
		pst=con.prepareStatement("update CLASSINFO set S_CLASSFRIENDS=? where S_BRANCH=? and S_SECTION=?");
		pst.setString(1,temp);
		pst.setString(2,branch);
		pst.setString(3,sect);
		result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		addToClassInfo(branch,hex,s[5]);
		
		
		
	}
	

	public void addStudent(String s[],String branch) throws Exception{
	
	pst=con.prepareStatement("insert into STUDENT_"+branch+"(S_ROLL,S_HEXCODE,S_NAME,S_PASSWORD,S_BRANCH,S_SECTION,S_SEMESTER,S_ADDRESS,S_PHONE,S_EMAIL,S_GENDER,"
			+ "S_OTHERFRIENDLIST,S_TEACHERLIST,S_PENDINGLIST,S_SENTLIST,S_ABOUTME,S_SOCIETY,CHATID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		for(int i=1;i<=18;i++){
			pst.setString(i,s[i-1]);
		}
		pst.setString(2,ds.rollToHex(s[0]));
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		addToClassInfo(branch,ds.rollToHex(s[0]),s[5]);
	}
	
	public void addToClassInfo(String branch,String hex,String sec) throws SQLException{
	
		pst=con.prepareStatement("update CLASSINFO set S_CLASSFRIENDS=concat(S_CLASSFRIENDS,?) where S_BRANCH=? and S_SECTION=?");
		pst.setString(1,hex);
		pst.setString(2,branch);
		pst.setString(3,sec);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
//*********************UPDATE****************
	
	public void update(String roll,String val) throws SQLException{
		pst.setString(1,val);
		pst.setString(2,roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
}
	
	public void updateSem(String roll,String val,String branch) throws SQLException{
		pst=con.prepareStatement("update STUDENT_"+branch+"  set S_SEMESTER=? where S_ROLL=? ");
		update(roll,val);
	}
	
	public void updatePhone(String roll,String val,String branch) throws SQLException{
		pst=con.prepareStatement("update STUDENT_"+branch+"  set S_PHONE=? where S_ROLL=? ");
		update(roll,val);
	}

	public void updateAddress(String roll,String val,String branch) throws SQLException{
		pst=con.prepareStatement("update STUDENT_"+branch+"  set S_ADDRESS=? where S_ROLL=? ");
		update(roll,val);
	}

	public void updateEmail(String roll,String val,String branch) throws SQLException{
		pst=con.prepareStatement("update STUDENT_"+branch+"  set S_EMAIL=? where S_ROLL=? ");
		update(roll,val);
	}
	
	public void updatePassword(String roll,String val,String branch) throws SQLException{
		pst=con.prepareStatement("update STUDENT_"+branch+"  set S_PASSWORD=? where S_ROLL=? ");
		update(roll,val);
	}
	
	public void updateAboutMe(String roll,String val,String branch) throws SQLException{
		pst=con.prepareStatement("update STUDENT_"+branch+"  set S_ABOUTME=? where S_ROLL=? ");
		update(roll,val);
	}
	
	
	
	
//********************CLASSINFO DATABASE*****************************************
	
	public ResultSet fetchClassFriendList(String roll,String branch,String section) throws SQLException{

		pst=con.prepareStatement("select S_CLASSFRIENDS from CLASSINFO where S_BRANCH=? and S_SECTION=?");
		pst.setString(1,branch);
		pst.setString(2,section);
		ResultSet rs=pst.executeQuery();
		rs.next();
		/*String list="";
		if(rs.next()){
		list=rs.getString("S_CLASSFRIENDS");
		}
		return list;*/
		return rs;
		
		}
	
	
	public int addStudentHexcodeToClass(String roll,String branch,String section,String value) throws SQLException{
		pst=con.prepareStatement("update CLASSINFO set S_CLASSFRIENDS=concat(S_CLASSFRIENDS,?) where S_SECTION=? and S_BRANCH=?");
		pst.setString(1,value);
		pst.setString(2,section);
		pst.setString(3,branch);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		return result;
	}
	
	public void addFriendToStudent(String roll,String id) throws Exception{
		if(id.charAt(0)=='|'){
			pst=con.prepareStatement("update STUDENT_"+ds.branchName(ds.findBranchFromRoll(roll))+" set S_TEACHERLIST=concat(S_TEACHERLIST,?) where S_ROLL=?");
		}else{
			pst=con.prepareStatement("update STUDENT_"+ds.branchName(ds.findBranchFromRoll(roll))+" set S_OTHERFRIENDLIST=concat(S_OTHERFRIENDLIST,?) where S_ROLL=?");
		}
		pst.setString(1,id);
		pst.setString(2,roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	
//***********************DELETE******************
	
	public void removeSentList(String roll,String hexcode,String branch) throws SQLException{
		
		
		pst=con.prepareStatement("select S_SENTLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1, roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		
		String list=rs.getString("S_SENTLIST"),temp="";
		for(int i=0;i<list.length();i=i+4){
			if(!list.substring(i,i+4).equals(hexcode)){
				temp=temp+list.substring(i,i+4);
			}
		}
		pst=con.prepareStatement("update STUDENT_"+branch+" set S_SENTLIST=? where S_ROLL=?");
			pst.setString(1,temp);
			pst.setString(2, roll);
			int result=pst.executeUpdate();
			System.out.println(result+" Records Affected.");
		
	}
	
	
	public void removePendingList(String roll,String hexcode,String branch) throws SQLException,Exception{
		pst=con.prepareStatement("select S_PENDINGLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1, roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		
		String list=rs.getString("S_PENDINGLIST"),temp="";
		for(int i=0;i<list.length();i=i+4){
			if(!list.substring(i,i+4).equals(hexcode)){
				temp=temp+list.substring(i,i+4);
			}
		}
		pst=con.prepareStatement("update STUDENT_"+branch+" set S_PENDINGLIST=? where S_ROLL=?");
			pst.setString(1,temp);
			pst.setString(2, roll);
			int result=pst.executeUpdate();
			System.out.println(result+" Records Affected.");
	}
	
	
	
	public Boolean isFriend(student s,String id) throws SQLException,Exception{
		if(id.charAt(0)=='|'){   //TEACHER
			String teacherList=fetchTeacherList(s.s_roll,s.s_branch,true);
			if((teacherList.indexOf(id)==-1) || ((teacherList.indexOf(id)%4)!=0)){
				return false;
			}
			return true;
		}
		
		else{  //STUDENT
			if(s.s_section.equals(findSectionFromRoll(id))){
				return true;
			}
			else {
				ResultSet rs=fetchOtherFriendList(s.s_roll,s.s_branch);
				String list=rs.getString("S_OTHERFRIENDLIST");
				String hex=ds.rollToHex(id);
				if((!(list.indexOf(hex)==-1) && ((list.indexOf(hex)%4)==0))){
					System.out.println("true...is friend");
					return true;
					
			}
			return false;
			}
		}
	}
	
	public String findSectionFromRoll(String roll) throws SQLException, Exception{
		pst=con.prepareStatement("select S_SECTION from STUDENT_"+ds.branchName(ds.findBranchFromRoll(roll))+" where S_ROLL=?");
		pst.setString(1,roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getString("S_SECTION");
		
		
	}
	
		
	public void removeStudent(String roll,String branch) throws SQLException{
		pst=con.prepareStatement("delete from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1, roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
	}
	
	public ResultSet getSociety(String roll,String branch) throws SQLException{
		pst=con.prepareStatement("select S_SOCIETY from STUDENT_"+branch+" where S_ROLL=?");
		HashMap<String,String> hm=new HashMap<String,String>();
		pst.setString(1, roll); 
		ResultSet rs=pst.executeQuery();
		/*if(rs.next()){
		String id[]=rs.getString("S_SOCIETY").split("#");
		if(!rs.getString("S_SOCIETY").equals("")&&(!rs.getString("S_SOCIETY").equals(" "))){
		for(int i=0;i<id.length;i++){
			rs=sd.fetchAll(id[i]);
			System.out.println("society "+id[i]);
			hm.put(rs.getString("ID"),rs.getString("NAME"));
		}
		}
		}
		return hm;*/
		return rs;
		
	}
	
	public HashMap<String,HashMap<String,String>> decodePendingList(String id) throws Exception{
		String temp="";
		HashMap<String,String> tchr=new HashMap<String,String>();
		HashMap<String,String> student=new HashMap<String,String>();
		HashMap<String,HashMap<String,String>> hm=new HashMap<String,HashMap<String,String>>();
		ResultSet rs;
		for(int i=0;i<id.length();i=i+4){
			temp=id.substring(i,i+4);
			if(temp.charAt(0)=='|'){
				rs=td.fetchAll(temp);
			tchr.put(temp,rs.getString("T_NAME"));
			}else{
				rs=fetchAll(ds.hexToRoll(temp),ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(temp))));
				student.put(temp,rs.getString("S_NAME"));
			}
		}
		hm.put("teacher",tchr);
		hm.put("student",student);
		return hm;
	}
	
	public boolean isSent(String roll,String id) throws Exception{
		String branch=ds.branchName(ds.findBranchFromRoll(roll));
		pst=con.prepareStatement("select S_SENTLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1,roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		if(id.charAt(0)!='|'){
		id=ds.rollToHex(id);
		}
		String list=rs.getString("S_SENTLIST");
		if(((list.indexOf(id))!=-1) && ((list.indexOf(id)%4)==0)){
			return true;
		}
		return false;
	}
	
	public boolean isPending(String id,String roll) throws Exception{
		String hex=roll;
		System.out.println("PENDING FUNTION ID- "+id);
		String branch=ds.branchName(ds.findBranchFromRoll(id));
		System.out.println("PENDING FUNTION BRANCH- "+branch);
		if(roll.charAt(0)!='|'){
			hex=ds.rollToHex(roll);
		}
		pst=con.prepareStatement("select S_PENDINGLIST from STUDENT_"+branch+" where S_ROLL=?");
		pst.setString(1,id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String list=rs.getString("S_PENDINGLIST");
		if(((list.indexOf(hex))!=-1) && ((list.indexOf(hex)%4)==0)){
			return true;
		}
		return false;
	}
	
	
	public void removeFriend(String roll,String hex) throws Exception{
		String branch=ds.branchName(ds.findBranchFromRoll(roll));
		String list,temp="";
		if(hex.charAt(0)=='|'){
			pst=con.prepareStatement("select S_TEACHERLIST from STUDENT_"+branch+" where S_ROLL=?");
			pst.setString(1,roll);
		     ResultSet rs=pst.executeQuery();
			rs.next();
			list=rs.getString("S_TEACHERLIST");
		}else{
			pst=con.prepareStatement("select S_OTHERFRIENDLIST from STUDENT_"+branch+" where S_ROLL=?");
			pst.setString(1,roll);
		     ResultSet rs=pst.executeQuery();
			rs.next();
			list=rs.getString("S_OTHERFRIENDLIST");
			
		}
		for(int i=0;i<list.length();i=i+4){
			if(!list.substring(i,i+4).equals(hex)){
			temp=temp+list.substring(i,i+4);
		}
		}
		if(hex.charAt(0)=='|'){
		pst=con.prepareStatement("update STUDENT_"+branch+" set S_TEACHERLIST=? where S_ROLL=?");
		}else{
			pst=con.prepareStatement("update STUDENT_"+branch+" set S_OTHERFRIENDLIST=? where S_ROLL=?");
		}
		System.out.println("After Removing "+hex+" from "+ temp);
		
		pst.setString(1, temp);
		pst.setString(2,roll);		
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		
	}
	
	
	public ResultSet all(String branch) throws SQLException{
		pst=con.prepareStatement("select * from STUDENT_"+branch+" ");
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
	
	
	
	
	
	
	
	
	
	

