<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<a href="teacherProfile.jsp">My Profile</a><br>
<body bgcolor="#C5D5E5">
<form action="insertAchievement" method="GET">
<center><h1>Student Achievement</h1></center>
<span id="prop">Enter Achievement</span><input type="text" name="content" /><br />
<span id="prop">Student Roll Number</span><input type="text" name="student" /><br />
<!-- <span id="prop">Insert</span><input type="radio" name="choice" value="insert" /><br> -->
<input type="submit" value="Submit" />
</form>

</body>
</html>