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

import com.database.achievementDatabase;
import com.database.noticeDatabase;

/**
 * Servlet implementation class insertAchievement
 */
@WebServlet("/insertAchievement")

public class insertAchievement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		Enumeration<String> e12=request.getParameterNames(); //TO FIND THE LENGTH OF PARAMETERS DYANAMICALLY
		String radio=request.getParameter("choice");
		String aid=request.getParameter("id");
		HttpSession s=request.getSession(false);
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
		radio="insert";
			achievementDatabase ad=new achievementDatabase();
			//ad=new noticeDatabase();
			if(radio.equals("insert")){
				ad.addAchievement(values[0],s.getAttribute("id")+" "+s.getAttribute("name"),values[1]);
				response.sendRedirect("teacherProfile.jsp");
			}
			else if(radio.equals("delete")){
				ad.removeAchievement(aid);
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
		}
		
		
	}

}
