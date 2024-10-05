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

import com.database.studentDatabase;

/**
 * Servlet implementation class insertTeacher
 */
@WebServlet("/insertTeacher")
public class insertTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       teacherDatabase td;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		Enumeration<String> e=request.getParameterNames();
		Enumeration<String> e12=request.getParameterNames(); //TO FIND THE LENGTH OF PARAMETERS DYANAMICALLY
		String radio=request.getParameter("choice");
		String id=request.getParameter("t_id");
		
		int length=0;
		while(e12.hasMoreElements()){
		length++;
		System.out.println(e12.nextElement());
		}
		System.out.println("length "+length);
		String values[]=new String[length];
		for(int i=0;i<length;i++){
			values[i]=request.getParameter(e.nextElement());
			}	
		
		
			td=new teacherDatabase();
		if(radio.equals("insert")){
			td.addTeacher(values);
		}
		else if(radio.equals("delete")){
			td.deleteTeacher(id);
		}
		else if(radio.equals("update")){
			td.updateTeacher(id, values);
		}
		
		
		
		
	} catch (ClassNotFoundException e1) {
		PrintWriter p=response.getWriter();
		p.println("Some error occured in getting data");
		e1.printStackTrace();
	} catch (SQLException e1) {
		PrintWriter p=response.getWriter();
		p.println("Some error occured in getting data");
		e1.printStackTrace();
	}
		
		
		
		
		
		
		
		
		
		
		
	}

	

}
