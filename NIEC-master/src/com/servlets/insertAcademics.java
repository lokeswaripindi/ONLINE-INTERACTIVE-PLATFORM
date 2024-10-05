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

import com.database.academicsDatabase;
import com.database.studentDatabase;

/**
 * Servlet implementation class insertAcademics
 */
@WebServlet("/insertAcademics")
public class insertAcademics extends HttpServlet {
	private static final long serialVersionUID = 1L;
     academicsDatabase ad;  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		Enumeration<String> e12=request.getParameterNames(); //TO FIND THE LENGTH OF PARAMETERS DYANAMICALLY
		String radio=request.getParameter("choice");
		String sem=request.getParameter("semester");
		String branch=request.getParameter("branch");
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
		
		
		
			ad=new academicsDatabase();
			if(radio.equals("insert")){
				ad.insertAll(values, branch);
			}
			else if(radio.equals("delete")){
				ad.deleteAcademics(branch, sem);
			}
			else if(radio.equals("update")){
				ad.updateAcademics(values, branch);
			}
		}
			catch (ClassNotFoundException | SQLException e2) {
				PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
			e2.printStackTrace();
		
			}
		}
		
		
	
	

	

}
