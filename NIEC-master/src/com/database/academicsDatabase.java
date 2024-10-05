package com.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.entity.*;
public class academicsDatabase {


	Connection con;
	PreparedStatement pst;
	
	public academicsDatabase() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNIEC","root","s");
	}
	
	public void insertAll(String a[],String branch) throws SQLException{
		//String branch,String sem,String sub, String subcode,String previous,String teacher,String syllabus
		pst=con.prepareStatement("insert into ACADEMICS_"+branch+" (SEMESTER,SUBJECTS,SUBJECTCODES,PREVIOUSPPR,TEACHERS,SYLLABUS)"
				+ " values(?,?,?,?,?,?)");
		for(int i=1;i<=a.length-2;i++){
			pst.setString(i,a[i]);
		}
	int result=	pst.executeUpdate();
	System.out.println(result+" records affected");
		
	}
	
	public ResultSet getSubjects(student s,String sem) throws SQLException{
		pst=con.prepareStatement("select SUBJECTS,SUBJECTCODES FROM ACADEMICS_"+s.s_branch+" where SEMESTER=?");
		pst.setString(1,sem);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			return rs;
		}
		return null;
		
	}
	
	public void updateAcademics(String a[],String branch) throws SQLException{
		
		pst=con.prepareStatement("select * from ACADEMICS_"+branch+" where SEMESTER=?");
		pst.setString(1,a[1]);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String sql="update ACADEMICS_"+branch+" set SUBJECTS=?,SUBJECTCODES=?,PREVIOUSPPR=?,TEACHERS=?,SYLLABUS=?"
			+ " where SEMESTER=?";
		pst=con.prepareStatement(sql);
		for(int i=1;i<=a.length-3;i++){  //8
			if(!a[i].equals("")){
				pst.setString(i,a[i+1]);
			}else{
				pst.setString(i,rs.getString(i+1));
			}
		}
		pst.setString(6,a[1]);
		
		System.out.println(sql);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected.");
		
		
		
	}
	public void deleteAcademics(String branch,String sem) throws SQLException{
		
		pst=con.prepareStatement("delete from ACADEMICS_"+branch+" where SEMESTER=?");
		
		
			pst.setString(1,sem);
		
	int result=	pst.executeUpdate();
	System.out.println(result+" records affected");
		
	}
	
	public void closeConnection() throws SQLException{
		if(pst!=null){
		pst.close();
		}if(con!=null){
		con.close();
		}
	}
	
	

}
