<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.database.*" import="java.sql.*" import="com.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body>

<!-- Visitor is the person who has logged in 
Teacher ID is always prefied with a "|" but URL doesn't directly support "|" character
so URL contains just the teacher id without "|". And "|" need to be concatenated with ID after id is fetched from
the URL.-->

<h1>Teacher Profile Page</h1>
<br><a href="logout">LOGOUT</a><br>


<%
Boolean profile=true;
HttpSession se=request.getSession(false);
String id="",me="",pending="",sent="",chatGroups="";
if(se.getAttribute("id")!=null){
 id=se.getAttribute("id").toString();
 me=id;
}else{
	response.sendRedirect("login.jsp");
}
teacherDatabase td=new teacherDatabase();
ResultSet rs=null;
String who="";

String tid=null,tnme=null,list=null;
if(request.getParameter("who")!=null){  //To check if user session is maintaied or not.
	 who="|"+request.getParameter("who");
	if(!me.equals(who)){
	id=who;
	profile=false;
	}
	}

//ID/who--> myself as teacher T_ID     ME--> visitor  ROLL NO


teacher t=null;
studentDatabase sd=new studentDatabase();
decodingStudent ds=new decodingStudent();
noticeDatabase nd=new noticeDatabase();
achievementDatabase ad=new achievementDatabase();
try{
t=new teacher(id);
//ID/who--> myself as teacher T_ID     ME--> visitor  ROLL NO
if(!profile && !td.isFriend(id,me)){
	 if(me.charAt(0)=='|'){  // Visitor is teacher
		 if(!td.isSent(id,me) &&  !td.isPending(id,me)){  //TEACHER
			//me bhutta   id--uma mam
			 %>
				<form action="sendRequest">
				<input type="hidden" value="<%=id%>" name="who">
					 <input type="hidden" name="soc" value="s">
				<input type="submit" value="Send Friend Request">
				</form>
				<%
				
		 }
		 else if(td.isSent(id,me)){     //td.isPending(me,id)
			 
			 %>
				<form action="acceptRequest" > <input type="submit" value="Accept Request">
			<input type="hidden" name="who" value="<%=id%>">
				</form>
		 <% 
		 }
		 
		 else{   // Visitor is student
			 %>
			 <form action="sendRequest">
			 <input type="hidden" name="soc" value="c">
			<input type="hidden" value="<%=id%>" name="who">
			<input type="submit" value="Cancel Friend Request">
			</form>
			 <%
			 
		 }
	 }
	 else if(!td.isSent(id,me) &&  !td.isPending(id,me)){
		 
		 %>Trying to send request
			<form action="sendRequest">
			<input type="hidden" value="<%=id%>" name="who">
				 <input type="hidden" name="soc" value="s">
			<input type="submit" value="Send Friend Request">
			</form>
			<% 
		 
	 }
	 
	 else if(td.isSent(id,me)){           //td.isPending(me,id)
		 
		 %>
			<form action="acceptRequest" > <input type="submit" value="Accept Request">
		<input type="hidden" name="who" value="<%=id%>">
			</form>
	 <% 
	 }
	 
	else{ 
%>
	 <form action="sendRequest">
	 <input type="hidden" name="soc" value="c">
	<input type="hidden" value="<%=id%>" name="who">
	<input type="submit" value="Cancel Friend Request">
	</form>
	 <%
	 }
}
	
 

 rs=td.fetchAll(id);
 tid=rs.getString("T_ID");
 chatGroups=rs.getString("CHATID");
tnme=rs.getString("T_NAME");
pending=rs.getString("T_PENDINGLIST");
sent=rs.getString("T_SENTLIST");
se.setAttribute("name",tnme);
%><br>

 <%
 if(se.getAttribute("id").toString().charAt(0)=='|'){
	 
	 %>
	 <a href="teacherProfile.jsp">My Profile</a>
	 <%
 }else{
	 %>
	 <a href="studentProfile.jsp">My Profile</a>
	 <%
 }
 
 
%>


<table id="chat">

<th id="chat">
<h2>Basic Info</h2>
</th>

<th id="chat" style="text-align:right">
<%if(profile){ %>
<h2>Chat Groups</h2>
<%} %>
</th>

<tr id="chat"><td width="25%">

<span id="prop">Name- </span><span id="val"><%=rs.getString("T_NAME") %></span><br>
<span id="prop">ID- </span><span id="val"><%=rs.getString("T_ID") %></span><br>

<%-- <%
if(profile||t.td.isFriend(me,who)){
	%>
	<span id="prop">ID- </span><span id="val"><%=rs.getString("T_ID") %></span><br>
	<%	
}

%> --%>



<span id="prop">Branch-</span><span id="val"> <%=rs.getString("T_BRANCH") %></span><br>
<span id="prop">Classes- </span><span id="val"><%=rs.getString("T_CLASSES") %></span><br>
<span id="prop">Phone- </span><span id="val"><%=rs.getString("T_PHONE") %></span><br>
<span id="prop">About Me- </span><span id="val"><%=rs.getString("T_ABOUTME") %></span><br>
<span id="prop">Email- </span><span id="val"><%=rs.getString("T_EMAIL") %></span><br>
<span id="prop">Gender- </span><span id="val"><%=rs.getString("T_GENDER") %></span><br><br>
<br><br>
</td>
<td width="75%" style="text-align:right">

<%

try{
	if(profile){
%>

<%
String cid[]=chatGroups.split("#"),chatid="CHATID";
for(int i=0;i<cid.length && !chatGroups.equals("");i++){
	rs=t.cd.fetchAll(cid[i]); 
	%>
	<a href="chatScreen.jsp?cid=<%=rs.getString(chatid)%>"><%=rs.getString("NAME") %></a>
	<form action="removeChat"> <input type="submit" value="Leave Group"> 
	<input type="hidden" name="cid" value="<%=rs.getString(chatid)%>" > </form>
	
	
	<%
}
%>
<h2>Create a New Chat Room</h2>
<a href="insertChatRoom.jsp">Create</a>

<%
	}
}catch(Exception e){
	out.println("Error in getting chat groups ");e.printStackTrace();
	}



%>
</td>
</tr>
</table>








<h2>Student FriendList</h2><br>
<%}catch(Exception e){
	 out.println("Sorry error occured");
	 e.printStackTrace();
	 }
try{

rs=td.fetchAll(id);
String sr="S_ROLL";
list=rs.getString("T_STUDENTLIST");
if(list.length()>3){
for(int i=0;i<list.length();i=i+4){
	rs=sd.fetchAll(ds.hexToRoll(list.substring(i,i+4)),ds.branchName(ds.findBranchFromRoll(ds.hexToRoll((list.substring(i,i+4))))));
	%>
		<a href="studentProfile.jsp?who=<%=rs.getString(sr)%>" ><%=rs.getString("S_NAME") %></a></a><%if(profile){ %>
		&nbsp<form action="removeFriend">
		<input type="hidden" name="friend" value="<%=rs.getString(sr)%>">
		<input type="submit" value="Remove Friend"></form>
		<%} %> <br> 
<%	
}
 }	}catch(Exception e){
	 out.println("Sorry No FriendList");
	 e.printStackTrace();
	 }
 
 %>





<%if(profile){ %>   <!-- // To check who is viewing the profile as private content need not be displayed to everyone -->
	<h2>Teacher FriendList</h2><br>
	<%try{
		String idt="T_ID";
	rs=td.fetchAll(id);
	 list=rs.getString("T_TEACHERLIST");
	 if(list.length()>3){
	for(int i=0;i<list.length();i=i+4){
		rs=td.fetchAll(list.substring(i,i+4));
		%>
			<a href="teacherProfile.jsp?who=<%=rs.getString(idt).substring(1,4)%>" ><%=rs.getString("T_NAME") %></a>&nbsp
		<form action="removeFriend">
		<input type="hidden" name="friend" value="<%=rs.getString(idt)%>">
		<input type="submit" value="Remove Friend"></form>
		 <br>
	<%	
	}
	 }}catch(Exception e){
		 out.println("Sorry some problem occured in getting basic information");
		 e.printStackTrace();
		 }
	

%>
<h2>Notice</h2> 
<% try{
 rs=t.nd.showNoticeTeacher(t);
 %>
 <table border="3">
 <tr><th>Notice ID</th><th>Notice Content</th><th>Notice Sender</th><th>Notice Reciever</th>
 <th>Notice approve</th></tr>
 <% 
while(rs.next()){
	%>
 <tr><td><%=rs.getString("ID")%></td> <td><%=rs.getString("CONTENT") %></td>  <td><%=rs.getString("SENDER") %></td>

 <td><%=rs.getString("RECEIVER") %></td> <td> <%=rs.getString("APPROVE") %></td></tr>
	
<%
}
%></table>
<% 
 
}catch(Exception e){
out.println("Sorry No New Notices");e.printStackTrace();
}
%><br>
<form action="noticeTeacher.jsp">
<input type="submit" value="Add Notice"> </form>
<br>
<form action="teacherAchievement.jsp">
<input type="submit" value="Add Achevement"> </form><br>
<form action="teacherMarks.jsp">
<input type="submit" value="Show/Update Marks"> </form>



<br><br>
	<h2>Pending List</h2><br>
	
	<%
	String temp="";
	try{
		String idt="T_ID";
		String ids="S_ROLL";
		for(int i=0;i<pending.length();i=i+4){
			temp=pending.substring(i,i+4);
			if(temp.charAt(0)=='|'){
				rs=td.fetchAll(temp);
				%>
				
<a href="teacherProfile.jsp?who=<%=rs.getString(idt).substring(1,4)%>" ><%=rs.getString("T_NAME") %></a>

<form action="acceptRequest" > <input type="submit" value="Accept Request">
<input type="hidden" name="who" value="<%= rs.getString(idt)%>">


</form>	<br>	
		<%
			}else{
				rs=sd.fetchAll(ds.hexToRoll(temp),ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(temp))));
				%>
	<a href="studentProfile.jsp?who=<%=rs.getString(ids)%>" ><%=rs.getString("S_NAME") %></a> 
	<form action="acceptRequest" > <input type="submit" value="Accept Request">
	<input type="hidden" name="who" value="<%= rs.getString(ids)%>">
	</form>	<br>			
				<%
			}
		}
%>

 <% 
	}catch(Exception e){
		out.println("Sorry No Pending List");e.printStackTrace();
		}
	%>
	
<h2>Sent List</h2>	<br>
<%
try{
	 temp="";

	String idt="T_ID";
	String ids="S_ROLL";
	for(int i=0;i<sent.length();i=i+4){
		temp=sent.substring(i,i+4);
		if(temp.charAt(0)=='|'){
			rs=td.fetchAll(temp);
			%>
			
<a href="teacherProfile.jsp?who=<%=rs.getString(idt).substring(1,4)%>" ><%=rs.getString("T_NAME") %></a> <br>			
	<%
		}else{
			rs=sd.fetchAll(ds.hexToRoll(temp),ds.branchName(ds.findBranchFromRoll(ds.hexToRoll(temp))));
			%>
<a href="studentProfile.jsp?who=<%=rs.getString(ids)%>" ><%=rs.getString("S_NAME") %></a> <br>			
			<%
		}
	}
%>


	
<%	
}catch(Exception e){
	out.println("Sorry No Pending List");e.printStackTrace();
	}



}

if(sd!=null){
	sd.closeConnection();
}
if(td!=null){
	td.closeConnection();
}
if(nd!=null){
	nd.closeConnection();
}if(ad!=null){
	ad.closeConnection();
}

  %>





</body>
</html>