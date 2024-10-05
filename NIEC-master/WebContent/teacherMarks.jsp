<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.database.*" import="java.util.*" import="java.sql.*" import="com.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body bgcolor="#C5D5E5">
<a href="teacherProfile.jsp">My Profile</a><br>
<span id="prop">Enter Student Roll</span><form action="teacherMarks"><input type="text" name="roll"><br>
<input type="hidden" name="formType" value="insertForm">
<span id="prop">Branch</span><br>
<select name="branch">
<option value="CSE">CSE </option>
<option value="EEE"> EEE</option>
<option value="ECE"> ECE</option>
<option value="CIV"> CIVIL</option>
<option value="IT_"> IT</option>
<option value="MEC">MECH</option>
</select><br>
<span id="prop">Semester</span><br><select name="semester">
<option value="1">1 </option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
</select><br>
<input type="submit">
 <%try{
if(!(request.getAttribute("roll")==null)){
String roll=(String)request.getAttribute("roll");
String branch=(String)request.getAttribute("branch");
String sem=(String)request.getAttribute("semester");
student s=new student(roll);
decodingStudent ds=new decodingStudent();
String b=ds.branchName(ds.findBranchFromRoll(roll));
HashMap<String,String[]> hm=s.showMarks(s,sem);
if(hm!=null && b.equals(branch)){
String subjects[]=hm.get("subject");
String codes[]=hm.get("codes");
String marksCodes[]=hm.get("marks");
String len=marksCodes.length+"";

%>


</form><br><br>
<h2>MARKS</h2>
<table border="5">

<tr><th>Subject</th><th>Code</th><th>Insert/Update</th></tr>


<form action="teacherMarks">
<input type="hidden" name="len" value="<%=len %>">
<input type="hidden" name="formType" value="updateForm">
<% 
for(int i=0;i<marksCodes.length&&(subjects.length==marksCodes.length);i++){   %>
     <tr>
	<td><br><%=subjects[i] %></td><td><%=codes[i]%>  
	<td><input type="text" name="marks<%=i %>" value=<%= marksCodes[i]%> >  </td></tr>
	<%
	} %>

</table>
<span id="prop">Update</span><input type="radio" name="choice" value="update" /><br><br>

<input type="submit"></form> 
<% s.closeConnection();
}
else{
	out.println("Invalid Roll or Branch");
	
}


 }}catch(Exception e){
	 out.println("Some problem occured in getting data");
	 e.printStackTrace();
	 }


%> 














</body>
</html>