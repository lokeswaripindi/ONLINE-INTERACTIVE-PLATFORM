<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">

</head>
<body>
<form action="insertChatRoom">
<%
HttpSession s=request.getSession(false);

if(s.getAttribute("id").toString().charAt(0)=='|'){ %>
<a href="teacherProfile.jsp">Homepage</a>

<%}else{ %>
<a href="studentProfile.jsp">Homepage</a>
<%} %>
<br><br>


<h2>Enter Member Hexcode</h2><input type="text" name="mlist" value="<%=s.getAttribute("hex")!=null?s.getAttribute("hex"):""%>"><br>
<input type="submit" value="Create Chat Room"><br>
<input type="hidden" value="member" name="h"><br>
</form>

<%
if(request.getAttribute("group")!=null){
	System.out.println("attribute is "+request.getAttribute("group"));
	%>Enter Group Name also<br>
<form action="insertChatRoom">
<h2>Enter Group Name</h2><input type="text" name="gname"><br>
<input type="submit" value="Enter Group Name"><br>
<input type="hidden" value="group" name="h">
</form>	


<%
}
%>


</body>
</html>