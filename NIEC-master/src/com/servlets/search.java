package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.studentDatabase;
import com.database.teacherDatabase;


@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		String svalue=request.getParameter("svalue");
		String who=request.getParameter("who");
		ResultSet rs=null;
		studentDatabase sd=null;
		teacherDatabase td=null;
		String name="",branch="";
		if(who.equals("student")){
			rs=sd.all(branch);
			while(rs.next()){
				name=rs.getString("S_NAME");
				
				
				
			}
			
			
			
			
			
		}
		
		
		
		
		else{
			
			
			
			
		}
		
		
		}catch(Exception e){
			 PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
			e.printStackTrace();
		}
		
		
		
		
	}

}
