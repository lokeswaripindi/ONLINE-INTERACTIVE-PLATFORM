<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body bgcolor="#C5D5E5">
<form action="insertNotice" method="GET">

content<input type="text" name="content" /><br />
sender<input type="text" name="sender" /><br />
receiver<input type="text" name="receiver" /><br />
approve
<select name="approve">
<option value="0">0</option>
<option value="1">1</option>
</select><br />
id<input type="text" name="id" /><br />
Insert<input type="radio" name="choice" value="insert" /><br>
Delete<input type="radio" name="choice" value="delete" /><br>
approve<input type="radio" name="choice" value="approve" /><br>

<input type="submit" value="Submit" />
</form>
</body>
</html>