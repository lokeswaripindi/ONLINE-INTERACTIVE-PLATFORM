package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.decodingStudent;
import com.entity.student;
import com.entity.teacher;

/**
 * Servlet implementation class sendRequest
 */
@WebServlet("/sendRequest")
public class sendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			student s=null;
			teacher t=null;
			
		String fid=request.getParameter("who");
		String temp=fid;
		String soc=request.getParameter("soc");
		System.out.println("fid is "+fid);
		HttpSession se=request.getSession();
		String me=se.getAttribute("id").toString();
		
		if(soc.equals("s")){   //SEND REQUEST
			
			
			if(me.length()==11){
				s=new student(me);
				s.sendFriendRequest(fid);
				if(fid.length()==11){
				response.sendRedirect("studentProfile.jsp?who="+fid);
				}else{
					response.sendRedirect("teacherProfile.jsp?who="+fid.substring(1,4));
				}
			}
			
			else{
				t=new teacher(me);
				t.sendFriendRequest(fid);
			if(fid.length()==11){
				response.sendRedirect("studentProfile.jsp?who="+fid);
				}else{
					response.sendRedirect("teacherProfile.jsp?who="+fid.substring(1,4));
				}
		}
		
		}
		
		
		
		
		
		if(soc.equals("c")){
			
			if(me.length()==11){
				s=new student(me);
				s.cancelFriendRequest(fid);
			if(fid.length()==11){
				response.sendRedirect("studentProfile.jsp?who="+fid);
				}else{
					response.sendRedirect("teacherProfile.jsp?who="+fid.substring(1,4));
				}
			}
		
			
			else{
				t=new teacher(me);
				t.cancelFriendRequest(fid);
			if(fid.length()==11){
				response.sendRedirect("studentProfile.jsp?who="+fid);
				}else{
					response.sendRedirect("teacherProfile.jsp?who="+fid.substring(1,4));
				}
		}
		}
	if(s!=null){	
	 s.closeConnection();
	}
	if(t!=null){
	 t.closeConnection();
	}
}catch(Exception e){
			 PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
