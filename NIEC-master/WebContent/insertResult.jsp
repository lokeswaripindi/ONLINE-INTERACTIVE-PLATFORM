<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body bgcolor="#C5D5E5">
<form action="insertResult">
Semester<select name="semester">
<option value="1">1 </option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
</select><br>
<select name="s_branch">
<option value="CSE">CSE </option>
<option value="EEE"> EEE</option>
<option value="ECE"> ECE</option>
<option value="CIV"> CIVIL</option>
<option value="IT_"> IT</option>
<option value="MEC">MECH</option>
</select><br>
roll<input type="text" name="roll"/><br>
Enter raw marks e.g. ?1?23#24#22#?2?21#45#<input type="text" name="marks"/><br>

Insert<input type="radio" name="choice" value="insert" /><br>
show<input type="radio" name="choice" value="show" /><br>
Update<input type="radio" name="choice" value="update" /><br>
<input type="submit" value="Submit" />


</form>
</body>
</html>
