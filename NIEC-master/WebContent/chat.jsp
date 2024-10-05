<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.database.*" import="java.sql.*" import="com.entity.*" import="java.text.SimpleDateFormat" import="java.util.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body >
<script type="text/javascript">
  setTimeout(function () { location.reload(true); }, 2500);
</script>
<table border="3">
<%try{
HttpSession s=request.getSession(false);  //upper portion of chatScreen.jsp 
chatDatabase cd=new chatDatabase();
String id=request.getParameter("cid");  //chat id retrived from url to open a particular chat room
ResultSet rs=cd.showChat(id);
%>
<tr> <th>Message id &nbsp</th><th>Sender&nbsp</th><th>&nbsp&nbsp Message</th><th>&nbsp&nbsp Time</th></tr>
<%
while(rs.next()){  //retriving message from chat_CHATID database
%>	<tr>
<td><%=rs.getString("MID") %></td><td> <%=rs.getString("SENDER") %></td><td>	<%=rs.getString(2) %></td>
 <td><%=rs.getString(4) %></td>
<% 
}
cd.closeConnection();

%></table>
<%
}
catch(Exception e){
	 out.println("Some problem occured in getting data");
	 e.printStackTrace();
	 }
%>






</body>
</html>