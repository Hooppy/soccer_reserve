<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>

<script>
	$(document).ready(function() {
		$("#btn").click(function() {
			location.href = "new";
		});
	});
</script>

<head>

<meta charset="EUC-KR">
<title>INDEX</title>
</head>
<body>
${failureCode}
<form name="loginForm" action="login" method="post">
	<input type="text" name="username"><br>
	<input type="text" name="password"><br>
	<input type="submit" value="로그인">
	<button id="btn" type="button">회원가입</button>
	
<img src="../resources/images/1.png">

</form>
</body>
</html>