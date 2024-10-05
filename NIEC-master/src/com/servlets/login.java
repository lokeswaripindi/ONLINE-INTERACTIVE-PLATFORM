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

import com.database.studentDatabase;
import com.database.teacherDatabase;
import com.entity.decodingStudent;
import com.entity.student;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p=response.getWriter();
		RequestDispatcher rd=null;
		try {
			response.setContentType("text/html");
			
			String whos=request.getParameter("whos");
		
			String id=request.getParameter("id");
			String pass=request.getParameter("pass");
			if(whos.equals("Teacher")){
				
				//response.sendRedirect("teacherProfile.jsp?who="+id);
				teacherDatabase td=new teacherDatabase();
				if(td.loginTeacher(id, pass)){
					HttpSession s=request.getSession(true);
					 s.setAttribute("id",id);
					 System.out.println("teacher login as "+id);
					 System.out.println("teacherProfile.jsp?who="+id);
					 id=id.substring(1,4);
					 response.sendRedirect("teacherProfile.jsp?who="+id);
						 
				}else{
					p.println("Invalid Roll or Password");
					rd=request.getRequestDispatcher("login.jsp");
					rd.include(request, response);
				}
				
			}else if(whos.equals("Student")){

				studentDatabase sd=new studentDatabase();
				if(sd.loginStudent(id, pass)){
					decodingStudent ds=new decodingStudent();
					
					 HttpSession s=request.getSession(true);
					 s.setAttribute("id",id);
					 s.setAttribute("branch",ds.branchName(ds.findBranchFromRoll(id)));
					// rd=request.getRequestDispatcher("studentProfile.jsp?who="+id);
					//rd.forward(request, response);
					 response.sendRedirect("studentProfile.jsp?who="+id);
				
					 
				}else{
					p.println("Invalid T_ID or Password");
					 rd=request.getRequestDispatcher("login.jsp");
					rd.include(request, response);
				}
			}else if(id.equals("admin") && pass.equals("admin")){
				rd=request.getRequestDispatcher("adminProfile.jsp");
				rd.include(request, response);
			}else{
				p.println("Invalid Admin ID or Password");
				 rd=request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}
			
				
		} catch (Exception e) {
			p.println("<b><font color='red'>Invalid ID or Password</font></b>");
			System.out.println("login.java catch");
			rd=request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} 
		
	}

}
