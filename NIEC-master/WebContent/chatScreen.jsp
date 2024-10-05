<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.database.*" import="com.entity.*" import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
 <% String id="";
try{
id=request.getParameter("cid");
System.out.println("Value of cid is "+id);
}catch(Exception e){
	out.println("Some Problem with the URL");e.printStackTrace();
	}try{
%>

<!-- Main screen for chat to be shown to the user
 -->
<iframe src="chat.jsp?cid=<%=id %>" height="400px" width="100%" ></iframe>  
<!-- <iframe src="enterChat.jsp" height="100px" width="100%"></iframe> -->

<body >
<form action="chat">
<input type="hidden" name="id" value="<%=id%>">
<input type="hidden" name="whichForm" value="sendmessage">
<input type="text" name="message"> 
<input type="submit" value="submit message">

</form>
&nbsp
&nbsp
<form action="chat">
<input type="hidden" name="id" value="<%=id%>">
<input type="hidden" name="whichForm" value="memberOptions">
<select name="options">    <!-- to add,delete member and leave group -->
<option value="add">Add New Member</option>
<option value="delete">Remove a Member</option>
<option value="leave">Leave Group</option>
</select>
<input type="text" name="mem">&nbsp
<input type="submit">
</form><br>
<%
HttpSession s=request.getSession(false);
if(s.getAttribute("id").toString().charAt(0)=='|'){ %>
<a href="teacherProfile.jsp">Go to PROFILE</a>

<%}else{ %>
<a href="studentProfile.jsp">Go to PROFILE</a>
<%} %>

<h2>MemberList</h2>   <!-- To show memberlist -->
<%
chatDatabase cd=new chatDatabase();
decodingStudent ds=new decodingStudent();
studentDatabase sd=new studentDatabase();
teacherDatabase td=new teacherDatabase();
String list=cd.showMembers(id);

ResultSet rs;String roll;
for(int i=0;i<list.length();i=i+4){
	if(list.substring(i,i+4).charAt(0)=='|'){
		rs=td.fetchAll(list.substring(i,i+4));
		%><span id="val">
		<%=rs.getString("T_NAME") %></span><br>
<%		
	}else{
	roll=ds.hexToRoll(list.substring(i,i+4));
rs=sd.fetchAll(roll,ds.branchName(ds.findBranchFromRoll(roll)));
	%><span id="val">
	<%=rs.getString("S_NAME") %></span><br>
<%	
}
}}catch(Exception e){
	 out.println("Some problem occured in getting data");
	 e.printStackTrace();
	 }

%>


</body>


</html>