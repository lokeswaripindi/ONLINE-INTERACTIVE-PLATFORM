<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#C5D5E5">
<form action="insertAcademics">
<select name="branch">
<option value="CSE">CSE </option>
<option value="EEE"> EEE</option>
<option value="ECE"> ECE</option>
<option value="CIV"> CIVIL</option>
<option value="IT_"> IT</option>
<option value="MEC">MECH</option>
</select>
<br />
Semester<select name="semester">
<option value="1">1 </option>
<option value="2"> 2</option>
<option value="3"> 3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
</select><br>

SUBJECTS<input type="text" name="subjects"/><br>
SUBJECTCODES<input type="text" name="subjectcodes"/><br>
PREVIOUSPPR<input type="text" name="previousppr"/><br>
TEACHERS<input type="text" name="teachers"/><br>
SYLLABUS<input type="text" name="syllabus"/><br>
Insert<input type="radio" name="choice" value="insert" /><br>
Update<input type="radio" name="choice" value="update" /><br>
Delete<input type="radio" name="choice" value="delete" /><br>
<input type="submit" value="Submit" />


</form>
</body>
</html>