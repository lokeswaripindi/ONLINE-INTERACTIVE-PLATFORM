package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.student;
import com.entity.teacher;

/**
 * Servlet implementation class acceptRequest
 */
@WebServlet("/acceptRequest")
public class acceptRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		HttpSession s=request.getSession(false);
		String me=s.getAttribute("id").toString();
		String who=request.getParameter("who");
		if(me.charAt(0)=='|'){
			teacher t=new teacher(me);
			t.acceptFriendRequest(who);
			//if(who.charAt(0)=='|')
				response.sendRedirect("teacherProfile.jsp?who="+me.substring(1,4));
			
			
		}else{
			student st=new student(me);
			st.acceptFriendRequest(who);
			
				response.sendRedirect("studentProfile.jsp?who="+me);
			
		}
		}catch(Exception e){
			 PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
