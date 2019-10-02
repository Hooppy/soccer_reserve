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
				$('#r4').empty();
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
		
		if(hour < 10) hour = "0" + hour;
		if(min < 10) min = "0" + min;
		
		var date = String(year) + '-' + String(month) + '-' + String(day);
		var hm = String(hour) + String(min);
		
		$("input:radio[name=code]").click(function(){
			var code = $('input[name="code"]:checked').val();
			var time = $("#time").val();
			$("input[type=hidden]").val('');
			
			$.ajax({
				url: 'reserve/search',
				method: 'GET',
				dataType: 'json',
				data: {'code': code, 'time': time},
				success: function(data){
					$('#r4').empty();
					var cnt = 0;
					
					if(parseInt(hm) > 2100 || parseInt(data.length) < 1){
						$('#r4').append('���� ������ �ð��� �����ϴ�.');
					}else{
						$.each(data, function(key, value){
							var a1 = value.time;
							var a2 = a1.substring(0,2);
							var a3 = a1.substring(3,5);
							var a4 = a2 + a3;
							
							if(date == time && data.length > 0){
								if(parseInt(hm) < parseInt(a4)){
									cnt = cnt + 1;
									$('#r4').append('<input type="radio" name="idx" value="' + value.idx + '"/>' + value.time + '<br/>');
								}
							}else{
								$('#r4').append('<input type="radio" name="idx" value="' + value.idx + '"/>' + value.time + '<br/>');
							}
							
						});
					}
					
					if(date == time && cnt == 0){
						$('#r4').append('���� ������ �ð��� �����ϴ�.');
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
		$("input:button[name=reserve_btn]").click(function(){
			if($('#time').val() == ''){
				alert('��¥�� �����ϼ���.');
				return false;
			}else if($('#time').val() < date){
				alert('���� ��¥�� ������ �Ұ����մϴ�.');
				return false;
			}else if($('#idx').val() == ''){
				alert('�ð��� �����ϼ���.');
				return false;
			}
			
			$('#reserve_form').submit();
		});
	});
</script>

<head>
<meta charset="EUC-KR">
<title>INDEX</title>
</head>
<body>
${msg} <br/>
<a href="board/list">�Խ���</a> <br/>
<a href="reserve_list">������ȸ</a>

<form action="reserve" id="reserve_form" method="post">
	����� : <input id="time" name="time"> <br/>
	��ⱸ�� : <input type="radio" name="code" value="A" />A����
	        <input type="radio" name="code" value="B" />B����
	        <input type="radio" name="code" value="C" />C���� <br/>
	���ð� : <br/>
<img id="loading" src="../resources/images/loading.gif" style="display:none">
<div id="r4"></div> <br/>
<input type="hidden" id="idx" name="idx">
<input type="hidden" name="reg_id" value="${username}">
<input type="button" name="reserve_btn" value="�����ϱ�">

</form>
</body>
</html>