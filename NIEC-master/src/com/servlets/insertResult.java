package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.marksDatabase;
import com.database.studentDatabase;

/**
 * Servlet implementation class insertResult
 */
@WebServlet("/insertResult")
public class insertResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		marksDatabase md;
		Enumeration<String> e12=request.getParameterNames(); //TO FIND THE LENGTH OF PARAMETERS DYANAMICALLY
		String radio=request.getParameter("choice");
		String roll=request.getParameter("roll");
		String branch=request.getParameter("s_branch");
		String sem=request.getParameter("semester");
		Enumeration<String> e=request.getParameterNames();
		
		int length=0;
		while(e12.hasMoreElements()){
		length++;
			System.out.println(	e12.nextElement());
		}
		System.out.println(length);
		String values[]=new String[length];
		for(int i=0;i<length;i++){
			values[i]=request.getParameter(e.nextElement());
			}	
		
	     
		md=new marksDatabase();
		if(radio.equals("insert")){
			md.insertMarks(values[3],sem,roll, branch);
			response.sendRedirect("teacherProfile.jsp");
		}
		else if(radio.equals("show")){
			String t=md.showMarksCode(roll, branch);
			System.out.println(t);
		}
		else if(radio.equals("update")){
			md.updateMarks(values[3], roll, branch,sem);
		}
	     }catch(Exception e5){
	    	 PrintWriter p=response.getWriter();
	    	 
				p.println("Some error occured in getting data");
				response.sendRedirect("teacherProfile.jsp");
	    	 e5.printStackTrace();
	     }
		}
		
		}