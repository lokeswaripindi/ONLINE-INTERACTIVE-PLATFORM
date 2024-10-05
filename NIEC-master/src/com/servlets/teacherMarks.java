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

import com.database.marksDatabase;

/**
 * Servlet implementation class teacherMarks
 */
@WebServlet("/teacherMarks")
public class teacherMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	marksDatabase md;
	String roll,branch,sem;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String formName=request.getParameter("formType");
			if(formName.equals("insertForm")){
				branch=request.getParameter("branch");
		roll=request.getParameter("roll");
	
		 sem=request.getParameter("semester");
		System.out.println("branch is "+branch);
		
		
		request.setAttribute("roll",roll);
		request.setAttribute("branch",branch);
		request.setAttribute("semester",sem);
		
		
			RequestDispatcher rd=request.getRequestDispatcher("teacherMarks.jsp");
			rd.forward(request, response);
		}else{
			
			String radio=request.getParameter("choice");
			String len=request.getParameter("len");
		md=new marksDatabase();
		System.out.println("branch is "+branch);
		String temp="?"+sem+"?";
		for(int i=0;i<Integer.parseInt(len);i++){
			//mrks[i]=request.getParameter("marks"+i);
			temp=temp+request.getParameter("marks"+i)+"#";
		}
		
		if(radio.equals("insert")){
		md.insertMarks(temp,sem,roll,branch);
		response.sendRedirect("teacherMarks.jsp");
		}
		else{
			md.updateMarks(temp, roll, branch, sem);
			response.sendRedirect("teacherMarks.jsp");
		}
		

		
		}
		
		}catch(Exception e){
			PrintWriter p=response.getWriter();
			p.println("Some error occured in getting data");
			response.sendRedirect("teacherMarks.jsp");
			e.printStackTrace();
		}
		finally{
			try {
				if(md!=null){
				md.closeConnection();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}
		
	
		
	}


