package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.*;
import com.entity.*;


@WebServlet("/insertChatRoom")
public class insertChatRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String hex,me,id;
	chatRoomDatabase cd;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try{
			id="";
		teacherDatabase td=new teacherDatabase();
		HttpSession s=request.getSession(false);
		studentDatabase sd=new studentDatabase();
		 cd=new chatRoomDatabase();
		decodingStudent ds=new decodingStudent();
		response.setContentType("text/html");
		String h=request.getParameter("h");
		//String me=request.getAttribute("me").toString();
		 me= s.getAttribute("id").toString();
		if(h.equals("member")){
			 hex=request.getParameter("mlist");
			System.out.println("CHAT-> hex is "+hex);
			 s.setAttribute("hex",hex);
			 if(hex.length()>4){
				 request.setAttribute("group","true");
				RequestDispatcher rd=request.getRequestDispatcher("insertChatRoom.jsp");
				 rd.forward(request, response);
				 /*if(me.charAt(0)=='|'){
						response.sendRedirect("teacherProfile.jsp?who="+me.substring(1,4));
					}else{
						response.sendRedirect("studentProfile.jsp?who="+me);
					}*/
				 
				 
			 }
			 else {
				 String member="";
				 if(me.charAt(0)=='|'){
					 member=hex+me;
					 if(hex.charAt(0)=='|'){
						 ResultSet rs=td.fetchAll(hex);
						id= cd.addChat(member,rs.getString("T_NAME"));
						System.out.println("Group made with chat id "+id);
					 }else{
						 ResultSet rs=sd.fetchAll(hex,ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(hex))));
						id= cd.addChat(member,rs.getString("S_NAME"));
						System.out.println("Group made with chat id "+id);
					 }
					 
				 }else{
				 member=hex+ds.rollToHex(s.getAttribute("id").toString());
				 
				 if(hex.charAt(0)=='|'){
					 ResultSet rs=td.fetchAll(hex);
					id= cd.addChat(member,rs.getString("T_NAME"));
					System.out.println("Group made with chat id "+id);
				 }else{
					 ResultSet rs=sd.fetchAll(hex,ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(hex))));
					id= cd.addChat(member,rs.getString("S_NAME"));
					System.out.println("Group made with chat id "+id);
				 }
			 
				 
				 }
				 
				 
			 }
			
		}else{
			String name=request.getParameter("gname");
			String member="";
			 if(me.charAt(0)=='|'){
				 member=hex+me;
				 //ResultSet rs=td.fetchAll(me);
				id= cd.addChat(member,name);
				System.out.println("Group made with chat id "+id);
				 
			 }else{
			 member=hex+ds.rollToHex(s.getAttribute("id").toString());
			// ResultSet rs=sd.fetchAll(ds.hexToRoll(hex),ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(hex))));
			id= cd.addChat(member,name);
			System.out.println("Group made with chat id "+id);
			 }
		
		}
		
		if(me.charAt(0)=='|'){
			response.sendRedirect("teacherProfile.jsp?who="+me.substring(1,4));
		}else{
			response.sendRedirect("studentProfile.jsp?who="+me);
		}
		
		
		
		}catch(Exception e){
			PrintWriter p=response.getWriter();
			p.println("Some error occured in getting data");
			e.printStackTrace();
			
			try {
				System.out.println("tring to delete chat id "+id);
				cd.delete(id);
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
			finally{
				response.sendRedirect("insertChatRoom.jsp");
			}
			
			
		}
		
		
		
		
		
		
	}

}
