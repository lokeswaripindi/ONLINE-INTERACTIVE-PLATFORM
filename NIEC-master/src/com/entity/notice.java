package com.entity;

import java.sql.SQLException;
import com.database.*;
public class notice {
	
	public String id,content,sender,receiver,approve;  //APPROVE--> 0 for FALSE, 1 for TRUE
	  										//RECEIVER--> branch, all, section, society, friendrequest related
	
	public noticeDatabase nd;
	notice() throws ClassNotFoundException, SQLException{
		nd=new noticeDatabase();
	}
	public void addNotice(String content,String sender,String receiver,String approve) throws SQLException{
		nd.addNotice(content, sender, receiver,approve);
	}

	public void removeNotice(String id) throws SQLException{
		nd.deleteNotice(id);
	}
	
	public void show(){
		
	}
	
}
