<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body bgcolor="#C5D5E5">
<form action="insertSociety">
<br>
name<input type="text" name="name" /><br>
password<input type="text" name="password" /><br>
memberList<input type="text" name="memberlist" /><br>
about<input type="text" name="about" /><br>
contact<input type="text" name="contact" /><br>
type<input type="text" name="type" /><br>
branchList<input type="text" name="branchList" /><br> 
id<input type="text" name="id" /><br>
Insert<input type="radio" name="choice" value="insert" /><br>
Delete<input type="radio" name="choice" value="delete" /><br>
Update<input type="radio" name="choice" value="update" /><br>
<input type="submit" value="Submit" />



</form>


</body>
</html>