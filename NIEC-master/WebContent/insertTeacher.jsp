<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body bgcolor="#C5D5E5">
<form action="insertTeacher" method="GET">

<br />
T_Name: <input type="text" name="t_name" /><br />
t_password<input type="text" name="t_password" /><br />
<select name="t_branch">
<option value="CSE">CSE </option>
<option value="EEE"> EEE</option>
<option value="ECE"> ECE</option>
<option value="CIV"> CIVIL</option>
<option value="IT_"> IT</option>
<option value="MEC">MECH</option>
</select>
<br />
t_phone<input type="text" name="t_phone" /><br />
t_classes<input type="text" name="t_classes" /><br />
t_email<input type="text" name="t_email" /><br />
<select name="t_gender">
<option value="male">Male</option>
<option value="female">Female</option>
<option value="Other">Other</option>
</select>
<br />
t_studentList<input type="text" name="t_studentList" /><br />
t_teacherList<input type="text" name="t_teacherList" /><br />
t_pendingList<input type="text" name="t_pendingList" /><br />
t_sentList<input type="text" name="t_sentList" /><br />
t_aboutMe<input type="text" name="t_aboutMe" /><br />
T_id: <input type="text" name="t_id"><br>
Insert<input type="radio" name="choice" value="insert" /><br>
Delete<input type="radio" name="choice" value="delete" /><br>
Update<input type="radio" name="choice" value="update" /><br>

<input type="submit" value="Submit" />
</form>

</body>
</html>