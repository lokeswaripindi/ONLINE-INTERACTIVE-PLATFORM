package com.entity;
import com.database.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class teacher {

	public String t_id,t_name,t_password,t_branch,t_phone,t_classes,t_email,t_gender,t_studentList,t_teacherList,
	    t_pendingList,t_sentList,t_aboutMe;
	//Image t_profilePic;      //T_ID is a 4 digit code starting with 1,2 or 3 |'s. eg.--> |||1, ||24, |224.
	public achievementDatabase acd;
	public teacherDatabase td;
	public decodingStudent ds;
	public noticeDatabase nd;
	public studentDatabase sd;
	public chatRoomDatabase cd;
	
	public teacher(String id) throws Exception{
		sd=new studentDatabase();
		cd=new chatRoomDatabase();
		acd=new achievementDatabase();
		nd=new noticeDatabase();
		ds=new decodingStudent();
		td=new teacherDatabase();
		ResultSet rs=td.fetchAll(id);
		t_id=rs.getString("T_ID");
		t_studentList=rs.getString("T_STUDENTLIST");
		t_classes=rs.getString("T_CLASSES");
		t_name=rs.getString("T_NAME");
		t_branch=rs.getString("T_BRANCH");
		t_phone=rs.getString("T_PHONE");
		t_password=rs.getString("T_PASSWORD");
		t_email=rs.getString("T_EMAIL");
		t_pendingList=rs.getString("T_PENDINGLIST");	
		t_sentList=rs.getString("T_SENTLIST");	
		t_aboutMe=rs.getString("T_ABOUTME");
		t_gender=rs.getString("T_GENDER");
		t_teacherList=rs.getString("T_TEACHERLIST");
	}
	
	public teacher() throws ClassNotFoundException, Exception{
		sd=new studentDatabase();
		acd=new achievementDatabase();
		nd=new noticeDatabase();
		ds=new decodingStudent();
		td=new teacherDatabase();
	}
	
	
	public void addAchievment(String content,String roll) throws SQLException{
		acd.addAchievement(content,t_id,roll);
	}
	
	
	public void acceptFriendRequest(String id) throws Exception{
		if(id.charAt(0)=='|'){
			td.removePendingList(t_id,id);	
			td.removeSentList(id,t_id);
			td.addFriendToTeacher(t_id,id);
			td.addFriendToTeacher(id,t_id);
		}else{
			td.removePendingList(t_id,ds.rollToHex(id));
			sd.removeSentList(id,t_id,ds.branchName(ds.findBranchFromRoll(id)));
			td.addFriendToTeacher(t_id,ds.rollToHex(id));
			sd.addFriendToStudent(id,t_id);
		}
	
	}
	
	
	public void cancelFriendRequest(String roll) throws Exception{
			
		if(roll.charAt(0)=='|'){
			td.removePendingList(roll,t_id);
			td.removeSentList(t_id,roll);
		}else{
			sd.removePendingList(roll,t_id,ds.branchName(ds.findBranchFromRoll(roll)));
			td.removeSentList(t_id,ds.rollToHex(roll));
			
		}
	}
	
	
// ID WILL BE ROLL NO FOR SENDING REQUEST TO STUDENT, OTHERWISE WILL BE TEACHER ID.
	public void sendFriendRequest(String id) throws Exception{ 
 
		if(id.charAt(0)=='|'){
		td.addSentList(t_id,id);
		td.addPendingList(id,t_id);
		}else{
			td.addSentList(t_id,ds.rollToHex(id));
			sd.insertPendingList(id,t_id,ds.branchName(ds.findBranchFromRoll(id)));
		}
	nd.addNotice("",t_name,id,"1");
	}
	   

	public void closeConnection() throws SQLException{
		if(sd!=null){
	sd.closeConnection();
		}if(td!=null){
			td.closeConnection();
		}
		if(nd!=null){
			nd.closeConnection();
		}
		if(acd!=null){
			acd.closeConnection();
		}
if(cd!=null){
			
			cd.closeConnection();
		}
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
}
