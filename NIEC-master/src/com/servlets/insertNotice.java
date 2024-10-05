package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.noticeDatabase;

/**
 * Servlet implementation class insertNotice
 */
@WebServlet("/insertNotice")
public class insertNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		
		HttpSession s=request.getSession(false);
		noticeDatabase nd;
		Enumeration<String> e12=request.getParameterNames(); //TO FIND THE LENGTH OF PARAMETERS DYANAMICALLY
		String radio=request.getParameter("choice");
		//String roll=request.getParameter("id");
		//String approve=request.getParameter("approve");
		String name=request.getParameter("sender");
		String approve=request.getParameter("approve");
		Enumeration<String> e=request.getParameterNames();
		System.out.println("Notice sender name is "+name);
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
		radio="insert";
			nd=new noticeDatabase();
			if(radio.equals("insert")){
				nd.addNotice(values[0],s.getAttribute("id").toString()+s.getAttribute("name").toString(),values[1],approve);
				response.sendRedirect("teacherProfile.jsp");
			}
			else if(radio.equals("delete")){
				nd.deleteNotice(values[4]);
			}
			else if(radio.equals("approve")){
			nd.approveNotice(values[4]);
			}
		}
		 catch (SQLException e1) {
			 PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
				response.sendRedirect("teacherProfile.jsp");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			PrintWriter p=response.getWriter();
			p.println("Some error occured in getting data");
			response.sendRedirect("teacherProfile.jsp");
			e1.printStackTrace();
		};
	
	
	}

	
	}


