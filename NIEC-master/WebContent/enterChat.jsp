<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.database.*" import="com.entity.*" import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body >

FILE NOT TO BE USED.
Instead use chatScreen.jsp.
(Lower portion of chatScreen.jsp)

<% String id="";

System.out.println("profile is "+request.getAttribute("profile"));
if(request.getAttribute("profile")!=null){
	response.sendRedirect(request.getAttribute("profile").toString()+"Profile.jsp");
	//response.sendRedirect("studentProfile.jsp");
}
%>
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
<select name="options">
<option value="add">Add New Member</option>
<option value="delete">Remove a Member</option>
<option value="leave">Leave Group</option>
</select>
<input type="text" name="mem">&nbsp
<input type="submit">
</form>
<h2>MemberList</h2></h2><br>
<%try{
chatDatabase cd=new chatDatabase();
decodingStudent ds=new decodingStudent();
studentDatabase sd=new studentDatabase();
teacherDatabase td=new teacherDatabase();
String list=cd.showMembers(id);

ResultSet rs;String roll;
for(int i=0;i<list.length();i=i+4){
	if(list.substring(i,i+4).charAt(0)=='|'){
		rs=td.fetchAll(list.substring(i,i+4));
		%>
		<%=rs.getString("T_NAME") %><br>
<%		
	}else{
	roll=ds.hexToRoll(list.substring(i,i+4));
rs=sd.fetchAll(roll,ds.branchName(ds.findBranchFromRoll(roll)));
	%>
	<%=rs.getString("S_NAME") %><br>
<%	
}
}}catch(Exception e){
	 out.println("Some problem occured in getting data");
	 e.printStackTrace();
	 }

%>

</body>
</html>