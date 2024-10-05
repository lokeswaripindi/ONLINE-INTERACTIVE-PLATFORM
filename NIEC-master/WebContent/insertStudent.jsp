<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body bgcolor="#C5D5E5">
Student
<form action="insertStudent" method="GET">
s_roll <input type="text" name="s_roll"><br />
s_hexcode<input type="text" name="s_hexcode" /><br />

s_name <input type="text" name="s_name" /><br />
s_password<input type="text" name="s_password" /><br />
<select name="s_branch">
<option value="CSE">CSE </option>
<option value="EEE"> EEE</option>
<option value="ECE"> ECE</option>
<option value="CIV"> CIVIL</option>
<option value="IT_"> IT</option>
<option value="MEC">MECH</option>
</select>
<br />
Section<select name="section">
<option value="A1">A1 </option>
<option value="A2">A2</option>
<option value="S1">S1</option>
<option value="S2">S2</option>
<option value="T1">T1</option>
<option value="T2">T2</option>
<option value="F1">F1</option>
<option value="F2">F2</option>
</select><br>
<br />
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
s_address<input type="text" name="s_address" /><br />
s_phone<input type="text" name="s_phone" /><br />
s_email<input type="text" name="s_email" /><br />
<select name="s_gender">
<option value="male">Male</option>
<option value="female">Female</option>
<option value="Other">Other</option>
</select>
<br />
s_otherFriendList<input type="text" name="s_otherFriendList" /><br />
s_teacherList<input type="text" name="s_teacherList" /><br />
s_pendingList<input type="text" name="s_pendingList" /><br />
s_sentList<input type="text" name="s_sentList" /><br />
s_aboutMe<input type="text" name="s_aboutMe" /><br />

s_society<input type="text" name="s_society" /><br />
chat id<input type="text" name="chatid" /><br />
Insert<input type="radio" name="choice" value="insert" /><br>
Delete<input type="radio" name="choice" value="delete" /><br>
Update<input type="radio" name="choice" value="update" /><br>
<input type="submit" value="Submit" />
</form>
</body>
</html>

