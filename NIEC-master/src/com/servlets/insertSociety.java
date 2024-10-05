package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import com.database.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertSociety
 */
@WebServlet("/insertSociety")
public class insertSociety extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	societyDatabase sd;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		Enumeration<String> e12=request.getParameterNames(); //TO FIND THE LENGTH OF PARAMETERS DYANAMICALLY
		String radio=request.getParameter("choice");
		String id=request.getParameter("id");
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
		
				sd=new societyDatabase();
				if(radio.equals("insert")){
					sd.addSociety(values);
				}
				else if(radio.equals("delete")){
					sd.removeSociety(id);
				}
				else if(radio.equals("update")){
				sd.updateSociety(values,id);
				}
			}
			 catch (SQLException e1) {
				 PrintWriter p=response.getWriter();
					p.println("Some error occured in getting data");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
				e1.printStackTrace();
			};
		}
		
		
		
		
	}

	


