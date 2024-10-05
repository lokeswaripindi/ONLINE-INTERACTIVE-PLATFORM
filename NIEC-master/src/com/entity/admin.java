package com.entity;

import java.sql.SQLException;

import com.database.*;

public class admin {

	//call removeAchievment()
	String name,password;
	noticeDatabase nd;
	achievementDatabase ad;
	student s;
	
	public admin() throws ClassNotFoundException, SQLException{
		nd=new noticeDatabase();
		ad=new achievementDatabase();
		ad.removeAchievement();
		
	}
	
	
	public void approveNotice(String id) throws SQLException{
		nd.approveNotice(id);
	}
	
	

	
	
	
	
	
	
}
