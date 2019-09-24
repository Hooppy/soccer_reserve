<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>

	$(function() {
	    $("#time").datepicker({
	    	dateFormat: "yy-mm-dd",
	    	minDate: 0,
			onSelect: function(dateText) {
				$('input[name="code"]').removeAttr('checked');
				$('div').empty();
	    	}
	    }).val()
	});
	
	$(document).ready(function() {
		var d = new Date();
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var day = d.getDate();
		
		if(month < 10) month = "0" + month;
		if(day < 10) day = "0" + day;
		
		var hour = d.getHours() + 1;
		var min = d.getMinutes();
		
		var date = String(year) + '-' + String(month) + '-' + String(day);
		var hm = String(hour) + String(min);
		
		$("input:radio[name=code]").click(function(){
			var code = $('input[name="code"]:checked').val();
			var time = $("#time").val();
			
			$.ajax({
				url: 'reserve/search',
				method: 'GET',
				dataType: 'json',
				data: {'code': code, 'time': time},
				success: function(data){
					$('div').empty();
					if(data.length > 0){
						$('#r2').show();
						$.each(data, function(key, value){
							var a1 = value.time;
							var a2 = a1.substring(0,2);
							var a3 = a1.substring(3,5);
							var a4 = a2 + a3;
							
							if(date == time){
								if(parseInt(hm) < parseInt(a4)){
									$('#r4').append('<input type="radio" name="idx" value="' + value.idx + '"/>' + value.time + '<br/>');
								}
							}else{
								$('#r4').append('<input type="radio" name="idx" value="' + value.idx + '"/>' + value.time + '<br/>');
							}
							
						});
					}else{
						$('div').append('NOT DATA');
					}
					
					$("input:radio[name=idx]").click(function(){
						var idx = $('input[name="idx"]:checked').val();
						$("#idx").val(idx);
					});
				}, beforeSend: function(data){
					$('#loading').show();
				}, complete: function(data){
					$('#loading').hide();
				}
					
			});
		});
	});
</script>

<head>
<meta charset="EUC-KR">
<title>INDEX</title>
</head>
<body>

${msg} <br/>
<a href="board/list">게시판</a> <br/>

<form action="reserve" method="post">

	경기일 : <input id="time" name="time"> <br/>
	경기구장 : <input type="radio" name="code" value="A" />A구장
	        <input type="radio" name="code" value="B" />B구장
	        <input type="radio" name="code" value="C" />C구장 <br/>
	경기시간 : <br/>
<img id="loading" src="resources/images/loading.gif" style="display:none">
<div id="r4"></div> <br/>
<input type="hidden" id="idx" name="idx">

<input type="submit" value="예약하기">



</form>
</body>
</html>