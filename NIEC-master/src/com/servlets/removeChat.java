package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.chatRoomDatabase;
import com.entity.decodingStudent;


@WebServlet("/removeChat")
public class removeChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
		HttpSession s=request.getSession(false);
		decodingStudent ds=new decodingStudent();
		String me=s.getAttribute("id").toString();
		String cid=request.getParameter("cid");
		chatRoomDatabase cd=new chatRoomDatabase();
		if(me.charAt(0)=='|'){
			cd.removeMember(cid,me);
			response.sendRedirect("teacherProfile.jsp?who="+me.substring(1,4));
		}else{
			cd.removeMember(cid,ds.rollToHex(me));
			response.sendRedirect("studentProfile.jsp?who="+me);
		}
		
		} catch (Exception e) {
			PrintWriter p=response.getWriter();
			p.println("Some error occured in getting data");
			e.printStackTrace();
		}
		
		
		
		
	}

}
