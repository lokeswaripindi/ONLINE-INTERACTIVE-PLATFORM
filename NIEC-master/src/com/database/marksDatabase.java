package com.database;
import com.entity.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class marksDatabase {

	Connection con;
	PreparedStatement pst;
	
	public marksDatabase() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNIEC","root","s");
	}
	//********************************METHODS ARE CHECKED**********************
	
	public void insertMarks(String marks,String sem,String roll,String branch) throws SQLException{ //ONLY FOR 1ST SEMESTER
		if(sem.equals("1")){
		pst=con.prepareStatement("insert into RESULT_"+branch+"(MARKS,ROLL) values(?,?)");
		}
		else{
			pst=con.prepareStatement("update RESULT_"+branch+" set MARKS=concat(MARKS,?) where ROLL=? ");
		}
		pst.setString(1,marks);
		pst.setString(2,roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected");
	}
	
	
	public void updateMarks(String marks,String roll,String branch,String sem) throws SQLException{   //DEFALULT CASE 
		pst=con.prepareStatement("select MARKS from RESULT_"+branch+" where ROLL=? ");
		pst.setString(1,roll);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String marks1=rs.getString("MARKS");
		int first=marks1.indexOf("?"+sem+"?");
		int last=marks1.indexOf("?"+(Integer.parseInt(sem)+1)+"?");
		if(last==-1){
			last=marks1.lastIndexOf("#")+1;
		}
		String temp=marks1.substring(first,last);  
		String temp1=marks1.replace(temp,marks);
		pst=con.prepareStatement("update RESULT_"+branch+" set MARKS=? where ROLL=?");
		pst.setString(1, temp1);
		pst.setString(2, roll);
		int result=pst.executeUpdate();
		System.out.println(result+" Records Affected");
		
	}
	
	public String showMarksCode(String roll,String branch) throws SQLException{
		pst=con.prepareStatement("select MARKS from RESULT_"+branch+" where ROLL=?");
		pst.setString(1,roll);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
		return rs.getString("MARKS");
		}
		return "";
	}
	public void closeConnection() throws SQLException{
		if(pst!=null){
		pst.close();
		}if(con!=null){
		con.close();
		}
	}
	
	
	
}
