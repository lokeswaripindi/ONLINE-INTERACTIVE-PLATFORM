<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="com.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body >
<h1>Mark Sheet</h1><br><br>
<a href="teacherProfile.jsp">My Profile</a>
<% try{
	HttpSession se=request.getSession(false);
	String roll=se.getAttribute("id").toString();
	student s=new student(roll);
	if(Integer.parseInt(request.getParameter("semester"))<Integer.parseInt(s.s_semester)){
	
HashMap<String,String[]> hm1=new HashMap<String,String[]>();
 hm1=s.showMarks(s,request.getParameter("semester"));//s.s_semester);
 if(hm1!=null){
 String subjects[]=hm1.get("subject");
 String codes[]=hm1.get("codes");
 String marksCodes[]=hm1.get("marks");
 %><table border="5" align="center">
 <tr><th>Subject</th><th>Code</th><th>Insert/Update</th></tr>
 <%
 for(int i=0;i<marksCodes.length&&(subjects.length==marksCodes.length);i++){   %>
	 
	 <tr>
	<td><br><%=subjects[i] %></td><td><%=codes[i]%>  <td><%= marksCodes[i]%>  </td></tr>
	<%
	} 
 }

}else{
	out.println("Sorry No Academic Record yet");
}
}	
	
	catch(Exception e){
	 out.println("Sorry No Academic Record yet");e.printStackTrace();
 }
	%>
</table>



</body>
</html>