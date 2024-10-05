package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.studentDatabase;
import com.database.teacherDatabase;
import com.entity.decodingStudent;


@WebServlet("/removeFriend")
public class removeFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			decodingStudent ds=new decodingStudent();
		HttpSession s=request.getSession(false);
		String me=s.getAttribute("id").toString();
		String fid=request.getParameter("friend");
		if(me.charAt(0)=='|'){
			teacherDatabase td=new teacherDatabase();
			if(fid.charAt(0)=='|'){
				System.out.println("case 1");
				td.removeFriend(fid,me);
				td.removeFriend(me,fid);
			}
			else{
				System.out.println("case 2");
				studentDatabase sd=new studentDatabase();
				sd.removeFriend(fid,me);
				td.removeFriend(me,ds.rollToHex(fid));
			}
			response.sendRedirect("teacherProfile.jsp?who="+me.substring(1,4));
		}else{
			studentDatabase sd=new studentDatabase();
			if(fid.charAt(0)=='|'){
				System.out.println("case 3");
				teacherDatabase td=new teacherDatabase();
				td.removeFriend(fid,ds.rollToHex(me));
				sd.removeFriend(me,fid);
			}
			else{
				System.out.println("case 4");
				sd.removeFriend(fid,ds.rollToHex(me));
				sd.removeFriend(me, ds.rollToHex(fid));
			
			}
			response.sendRedirect("studentProfile.jsp?who="+me);
		}
		}
		catch(Exception e){
			 PrintWriter p=response.getWriter();
				p.println("Some error occured in getting data");
			e.printStackTrace();
		}
		
	

}

	}