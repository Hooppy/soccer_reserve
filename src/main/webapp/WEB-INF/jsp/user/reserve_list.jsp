<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	$(document).ready(function() {
		$("input:radio[name=codes]").click(function(){
			var radio = $('input[name="codes"]:checked').val();
			var data = radio.split(",");
			
			var idx = data[0];
			var code = data[1];
			var reg_dt = data[2];
			var time = data[3];
			
			$('#idx').val(idx);
			$('#code').val(code);
			$('#reg_date').val(reg_dt);
			$('#time').val(time);
		});
		
		$("input:button[name=reserve_drop]").click(function(){
			if($('#idx').val() == ''){
				alert('예약내역을 선택하세요.');
				return false;
			}
			
			$('#reserve_delete').submit();
		});
	});
</script>

<head>
<meta charset="EUC-KR">
<title>RESERVE LIST</title>
</head>
<body>
<form action="delete" id="reserve_delete" method="post">
	<c:choose>
		<c:when test="${not empty list and fn:length(list) > 0}">
			<table>
				<tr>
					<th></th>
					<th>구장</th>
					<th>날짜</th>
					<th>시간</th>
				</tr>
				<c:forEach items="${list}" var="list">
					<tr>
						<td><input type="radio" name="codes" value="${list.rsvr_idx},${list.rsvr_class},${list.reg_date},${list.rsvr_date}"></td>
						<td>${list.rsvr_class}</td>
						<td>${list.rsvr_date}</td>
						<td>${list.time}</td>
					</tr>
				</c:forEach>
			</table>
			<input type="button" name="reserve_drop" value="예약취소">
		</c:when>
		<c:otherwise>
			예약이 없습니다. <br/>
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="idx" name="idx">
	<input type="hidden" id="code" name="code">
	<input type="hidden" id="reg_date" name="reg_date">
	<input type="hidden" id="time" name="time">
</form>
</body>
</html>