<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.database.*" import="java.sql.*" import="com.entity.*" import="java.text.SimpleDateFormat" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body >
<h1>Student Profile Page</h1><br><p><a href="logout">LOGOUT</a><br></p>

<%
String who="",chatGroups="";
HttpSession se=request.getSession(false);
Boolean profile=true;
String roll="",branch="",me="",br="";
ResultSet rs=null;
societyDatabase sod=new societyDatabase();
String pending="",sent="";
student s=null;
if(se.getAttribute("id")!=null){  //to check if user session is maintained or not
	roll=se.getAttribute("id").toString();
	me=roll;br=branch;
}else{
	response.sendRedirect("login.jsp");
}

try{
	
	
	
if(request.getParameter("who")!=null){
	// ROLL/WHO is myself as student and me is visitor
	if(!roll.equals(request.getParameter("who"))){   //to check who is opening the profile page-> owner or visitor
		roll=request.getParameter("who");
		s=new student(roll);
		 profile=false;
	}else{
		s=new student(roll);
	}
}else{
	s=new student(roll);
}
	  branch=s.ds.branchName(s.ds.findBranchFromRoll(roll));  
	rs=s.sd.fetchAll(roll,branch);
 pending=rs.getString("S_PENDINGLIST");
 sent=rs.getString("S_SENTLIST");
 chatGroups=rs.getString("CHATID");
 if(!profile && !s.sd.isFriend(s,me)){ /// Code to check if friendrequest is to be sent,accepted or cancelled
	 
	 if(me.charAt(0)=='|'){     //visitor is teacher
		 if(!s.sd.isSent(roll,me) && !s.sd.isPending(roll,me)){  //TEACHER
			 %>				<form action="sendRequest">
				<input type="hidden" value="<%=roll%>" name="who">
					 <input type="hidden" name="soc" value="s">
				<input type="submit" value="Send Friend Request">
				</form>
				<%
				
		 }
		 else if(s.sd.isSent(roll,me)){  
			 %>
				<form action="acceptRequest" > <input type="submit" value="Accept Request">
			<input type="hidden" name="who" value="<%=roll%>">
				</form>
		 <% 
		 }
		 else{   // visitor is student
			 %>
			 <form action="sendRequest">
			 <input type="hidden" name="soc" value="c">
			<input type="hidden" value="<%=roll%>" name="who">
			<input type="submit" value="Cancel Friend Request">
			</form>
			 <%
			 
		 }
	 }
	 else if(!s.sd.isSent(roll,me) && !s.sd.isPending(roll,me)){
		 
		 %>Trying to send request
			<form action="sendRequest">
			<input type="hidden" value="<%=roll%>" name="who">
				 <input type="hidden" name="soc" value="s">
			<input type="submit" value="Send Friend Request">
			</form>
			<% 
		 
	 }
	 
	 else if(s.sd.isPending(me,roll)){  
		 %>
			<form action="acceptRequest" > <input type="submit" value="Accept Request">
		<input type="hidden" name="who" value="<%=roll%>">
			</form>
	 <% 
	 }
	 
	 
	 
	else{ 
	 %>
	 <form action="sendRequest">
	 <input type="hidden" name="soc" value="c">
	<input type="hidden" value="<%=roll%>" name="who">
	<input type="submit" value="Cancel Friend Request">
	</form>
	 <%
	 }
 }
	 
 %>
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

<br>






<table width ="100%" id="chat">

<th id="chat">
<h2>Basic Info</h2>
</th>

<th id="chat" style="text-align:center" >
<%if(profile){ %>
<h2>Chat Groups</h2>
<%} %>
</th>

<tr id="chat"><td width="25%">
<span id="prop">Name- </span> <span id="val"><%=rs.getString("S_NAME") %></span><br>
<span id="prop">Roll- </span> <span id="val"><%=rs.getString("S_ROLL") %></span><br>
<span id="prop">Branch- </span> <span id="val"><%=rs.getString("S_BRANCH") %></span><br>
<span id="prop">Section- </span> <span id="val"><%=rs.getString("S_SECTION") %></span><br>
<span id="prop">About Me- </span><span id="val"><%=rs.getString("S_ABOUTME") %></span><br>
<span id="prop">Email- </span> <span id="val"><%=rs.getString("S_EMAIL") %></span><br>
<span id="prop">Gender- </span> <span id="val"><%=rs.getString("S_GENDER") %></span><br><br>

</td>
<td width="75%" style="text-align:center">

<%

try{
	if(profile){
%>

<%
String cid[]=chatGroups.split("#"),chatid="CHATID";
for(int i=0;i<cid.length && !chatGroups.equals("");i++){
	rs=s.cd.fetchAll(cid[i]); 
	%>
	<a href="chatScreen.jsp?cid=<%=rs.getString(chatid)%>"><%=rs.getString("NAME") %></a><br>
	<form action="removeChat"> <input type="submit" value="Leave Group"> 
	<input type="hidden" name="cid" value="<%=rs.getString(chatid)%>" > </form><br>
	
	
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
rs=s.sd.fetchAll(roll,branch);


%>
</td>
</tr>
</table>

<h2>Personal Info</h2><br>
<span id="prop">Phone-</span> <span id="val"><%=rs.getString("S_PHONE") %></span><br>
<span id="prop">Hexcode- </span><span id="val"><%=rs.getString("S_HEXCODE") %></span><br>
<span id="prop">Address- </span><span id="val"><%=rs.getString("S_ADDRESS") %></span><br>

<%-- 
<%
if(profile||s.sd.isFriend(s,who)){
	%>
	<span id="prop">Hexcode- </span><span id="val"><%=rs.getString("S_HEXCODE") %></span><br>
	<%	
}

%>
 --%>






<%}catch(Exception e){
	out.println("Sorry error occured in basic info");
	e.printStackTrace();
	}
	
%>
<h2>Student Class FriendList</h2> <br>
<%


try{
	String sr="S_ROLL";
	
	rs=s.sd.fetchClassFriendList(roll, branch, s.s_section);
	String list=rs.getString("S_CLASSFRIENDS");
	for(int i=0;i<list.length();i=i+4){
		rs=s.sd.fetchAll(s.ds.hexToRoll(list.substring(i,i+4)), branch);
		
	%>
	  <a href="studentProfile.jsp?who=<%=rs.getString(sr)%>" ><%=rs.getString("S_NAME") %></a> <br> 
<%
	}
	 
}catch(Exception e){
out.println("Sorry No Student Class Friends");
e.printStackTrace();
}

try{
	

 rs=s.acd.showAchievement(roll);
 %>
 <h2>Achievements</h2><br>

	
	<table border="3">
 <tr><th>Achievement ID</th><th>Achievement</th><th>By Teacher</th><th>Date</th></tr>
 <% 
while(rs.next()){
	%>
 <tr><td><%=rs.getString("ID")%></td> <td><%=rs.getString("CONTENT") %></td>  <td><%=rs.getString("SENDER") %></td>

 <td><%=rs.getString("DATE1") %></td> </tr>
	
<%
}
%></table>
	
	
	
	
	
	
	
	
	
<%

}catch(Exception e){
out.println("Sorry No Achievements");e.printStackTrace();
}

// try{
	
//rs=s.sd.getSociety(roll, branch);
//if(rs.next()){
	%>
	<%-- <br>
<h2>Societies joined</h2><br>
	<% /*
	String ids="ID";
	String id[]=rs.getString("S_SOCIETY").split("#");
	if(!rs.getString("S_SOCIETY").equals("")&&(!rs.getString("S_SOCIETY").equals(" "))){
	for(int i=0;i<id.length;i++){
		rs=sod.fetchAll(id[i]);
	
          */  %> 
            <a href="societyProfile.jsp?id=<%=rs.getString(ids) %>"><%=rs.getString("NAME") %></a><br>

<% /*
}
}
}
}catch(Exception e){
out.println("Sorry No Societies");e.printStackTrace();
}

*/
%><br><br>  --%>
<% 
if(profile){  // To check who is viewing the profile as private content need not be displayed to everyone 
%>
<br><br>
<h2>Other Class FriendList</h2> <br>
<%try{
	String sr="S_ROLL";
	rs=s.sd.fetchOtherFriendList(roll,branch);
	String list=rs.getString("S_OTHERFRIENDLIST");
	String roll1="";
	for(int i=0;i<list.length();i=i+4){
		roll1=s.ds.hexToRoll(list.substring(i,i+4));
		rs=s.sd.fetchAll(roll1, s.ds.branchName(s.ds.findBranchFromRoll(roll1)));
	%>
		<a href="studentProfile.jsp?who=<%=rs.getString(sr)%>" ><%=rs.getString("S_NAME") %></a><%if(profile){ %>
		&nbsp<form action="removeFriend">
		<input type="hidden" name="friend" value="<%=rs.getString(sr)%>">
		<input type="submit" value="Remove Friend"></form>
		<%} %>
		<br>
		
	<%
	}
	 
}catch(Exception e){
out.println("Sorry No Other Class Friends");
e.printStackTrace();
}

%>



<h2> TeacherList</h2><br><%
	try{ 
		String idt="T_ID";
		rs=s.sd.fetchTeacherList(roll, branch);
		String tchr= rs.getString("S_TEACHERLIST");
		for(int i=0;i<tchr.length();i=i+4){
			rs=s.td.fetchAll(tchr.substring(i,i+4));
			%>
		<a href="teacherProfile.jsp?who=<%=rs.getString(idt).substring(1,4)%>" ><%=rs.getString("T_NAME") %></a><%if(profile){ %>
		&nbsp<form action="removeFriend">
		<input type="hidden" name="friend" value="<%=rs.getString(idt)%>">
		<input type="submit" value="Remove Friend"></form>
		<%} %>
		 <br>
		<%}
}
	catch(Exception e){
		out.println("Sorry No Teacher Friends");e.printStackTrace();
		}

%><br><br>

<h2>Notice</h2> 
<% try{
 rs=s.nd.showNoticeStudent(s);
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
 <br>
<h2>Academics</h2><br>
<form action="studentMarks.jsp">
<span id="prop">Select Semester</span><select name="semester">
<option value="1">1 </option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
</select>
<input type="submit">
</form>


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
				rs=s.td.fetchAll(temp);
				%>
				
<a href="teacherProfile.jsp?who=<%=rs.getString(idt).substring(1,4)%>" ><%=rs.getString("T_NAME") %></a>

<form action="acceptRequest" > <input type="submit" value="Accept Request">
<input type="hidden" name="who" value="<%= rs.getString(idt)%>">


</form>	<br>	
		<%
			}else{
				rs=s.sd.fetchAll(s.ds.hexToRoll(temp),s.ds.branchName(s.ds.findBranchFromRoll(s.ds.hexToRoll(temp))));
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
			rs=s.td.fetchAll(temp);
			%>
			
<a href="teacherProfile.jsp?who=<%=rs.getString(idt).substring(1,4)%>" ><%=rs.getString("T_NAME") %></a> <br>			
	<%
		}else{
			rs=s.sd.fetchAll(s.ds.hexToRoll(temp),s.ds.branchName(s.ds.findBranchFromRoll(s.ds.hexToRoll(temp))));
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
%>



<%
}  //FOR PROFILE ENDED

try{
if(s!=null){
s.closeConnection();
}

}catch(Exception e){
	out.println("Sorry error occured");e.printStackTrace();
	}
 


 %><br><br>

    




</body>
</html>