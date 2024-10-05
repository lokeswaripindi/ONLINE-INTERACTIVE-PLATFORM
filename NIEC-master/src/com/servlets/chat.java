package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.chatDatabase;
import com.database.chatRoomDatabase;
import com.entity.decodingStudent;


@WebServlet("/chat")
public class chat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String id;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		chatDatabase cd=null;
		chatRoomDatabase crd=null;
		decodingStudent ds=null;
		
		try {
			
			HttpSession s=request.getSession(false);
			String whichForm=request.getParameter("whichForm");
			 id=request.getParameter("id");
			 //RequestDispatcher rd=request.getRequestDispatcher("chatScreen.jsp?cid="+id);
			if(whichForm.equals("sendmessage")){
				cd=new chatDatabase();
				String msg=request.getParameter("message");
				if(!msg.equals("")){
				cd.addMessage(id, msg,s.getAttribute("id").toString());
				}
				response.sendRedirect("chatScreen.jsp?cid="+id);
				
			}else{
				String options=request.getParameter("options");
				String mem=request.getParameter("mem");
				crd=new chatRoomDatabase();
				if(options.equals("add")){
					System.out.println("Adding member "+mem+" to chat id "+id);
					crd.addMemberToChat(mem,id);
					response.sendRedirect("chatScreen.jsp?cid="+id);
				}else if(options.equals("delete")){
					crd.removeMember(id,mem);
					System.out.println("Removing member "+mem+" to chat id "+id);
					response.sendRedirect("chatScreen.jsp?cid="+id);
				}
				else{
					ds=new decodingStudent();
					if(s.getAttribute("id").toString().charAt(0)=='|'){
						crd.removeMember(id,s.getAttribute("id").toString());
						request.setAttribute("profile","teacher");
						response.sendRedirect("teacherProfile.jsp");
					}else{
					crd.removeMember(id,ds.rollToHex(s.getAttribute("id").toString()));
					request.setAttribute("profile","student");
					response.sendRedirect("studentProfile.jsp");
					}
					
					
				}
				
			}
			
			
		
			
		} catch (Exception e) {
			PrintWriter p=response.getWriter();
			p.println("Some error occured in getting data");
			e.printStackTrace();
		}
		finally{
			try {
				if(cd!=null){
				cd.closeConnection();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

}
