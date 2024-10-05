package com.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.database.*;
public class society {
	
	public String id,name,type,memberList,about,contact,password,branchList;
	societyDatabase sd;
	
	public society() throws ClassNotFoundException, SQLException{
		sd=new societyDatabase();
	}
	
	public void addMember(String hexcode,String branch) throws SQLException{
		sd.addMember(id,hexcode,branch);
	}
	
	public void removeMember(String hexcode) throws SQLException{
		sd.removeMember(id,hexcode);
	}
	
	public void sendNoticeToMembers(String content) throws SQLException{
		sd.sendNoticeToMembers(content, id, name);
	}
	
	public void showMembers() throws SQLException{
		ResultSet rs=sd.showMembers(id);
		String mlist=rs.getString("MEMBERLIST");
		String blist=rs.getString("BRANCHLIST");
		for(int i=0;i<mlist.length();i=i+4){
			System.out.println("Member hexcode is- "+mlist.substring(i,i+4)+" and branch is "+blist.substring(i, i+4));
		}
		
	}
	
	
	
	


	
}
