<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body bgcolor="#C5D5E5">

<a href="teacherProfile.jsp">My Profile</a><br>

<form action="insertNotice" method="GET">

<center><h1>Student Notices</h1></center>
<span id="prop">Enter Notice</span><input type="text" name="content" /><br />
<span id="prop">Enter Roll Number OR Branch OR SECTION OR "all" to send it to everyone</span><input type="text" name="receiver" /><br />
<!-- <span id="prop">Insert</span><input type="radio" name="choice" value="insert" /><br> -->

<input type="hidden" name="approve" value="0"/>

<input type="submit" value="Submit" />
</form>

</body>
</html>