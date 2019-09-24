<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>WRITE</title>
</head>
<body>

<form name="writeForm" action="writeComplete" method="post">
	작성자 : <input type="text" name="writer"><br>
	제목 : <input type="text" name="title"><br>
	내용 : <input type="text" name="content"><br>
	<input type="submit" value="작성완료">
	
	<button id="btn" type="button">회원가입</button>
</form>

</body>
</html>