<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>

<script>
	$(document).ready(function(){
		
		$("#listDiv a").click(function(e){
			$("#pageNum").val($(this).attr("href"));
			listForm.submit();
		});
		
		
		
		$("#write").click(function() {
			location.href = "write";
		});
	});
</script>
<head>
<meta charset="EUC-KR">
<title>WRITE</title>
</head>
<body>
	<table border="1" >
		<tr>
			<th bgcolor="" width="50">NO</th>
			<th bgcolor="" width="200">제목</th>
			<th bgcolor="" width="150">작성자</th>
			<th bgcolor="" width="150">작성일</th>
			<th bgcolor="" width="100">조회수</th>
		</tr>
		<c:forEach items="${boardList}" var="list">
			<tr>
				<td bgcolor="" width="50">${list.idx}</td>
				<td bgcolor="" width="200">${list.title}</td>
				<td bgcolor="" width="150">${list.writer}</td>
				<td bgcolor="" width="150">${list.reg_date}</td>
				<td bgcolor="" width="100">${list.cnt}</td>		
			</tr>	
		</c:forEach>
	</table>
	<c:forEach var="num" begin="${page.pageNum}" end="${page.amount}">
		<a href="${num}">${num}</a>&nbsp;&nbsp;
	</c:forEach>
	
	<div id="listDiv">
		<form name="listForm" action="board/board_list" id="list"> 
			<input type="hidden" id="pageNum" value="${page.pageNum}">
			<input type="hidden" id="amount"  value="${page.pageNum}">
		</form>
	</div>
	
	<button id="write" type="button">글쓰기</button>

</body>
</html>