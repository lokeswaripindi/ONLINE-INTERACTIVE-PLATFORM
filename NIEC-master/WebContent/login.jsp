<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body background="loginPic.jpg">
<h1>Login</h1><br>
<form align="center" action="login">
<select name="whos">
<option value="Student">Student</option>
<option value="Teacher">Teacher</option>
<option value="Admin">Admin</option>
</select><br>

<div id="prop">Enter ID</div><input style="background-color: #0C0E15; color: #FFFFFF" type="text" name="id"><br>
<div id="prop">Enter Password</div><input style="background-color: #0C0E15; color: #FFFFFF" type="password" name="pass"><br><br>
<input type="submit">
</form>


</body>
</html>