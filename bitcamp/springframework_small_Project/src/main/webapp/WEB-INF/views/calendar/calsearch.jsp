<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
  $( function() {
    $( "#datepicker1" ).datepicker({
    	dateFormat: "yy-mm-dd"
    });
    $( "#datepicker2" ).datepicker({
    	dateFormat: "yy-mm-dd"
      });
  } );
</script>
</head>
<body>
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">
	<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
		<tr>
			<td colspan="3" style="padding: 5px">
					<span style="font-weight: bold; margin-right: 3px">일정검색:</span>
					<input type="text" id="datepicker1"> ~ <input type="text" id="datepicker2">
			</td>
		</tr>
		<tr>
			<td>
				<select id="_choice" name="choice">
					<option value="" selected>선택</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
			</td>
			<td>
				<input type="text" id="_search" name="search" size="30">
			</td>
			<td style="padding-left: 5px">
				<span class="button blue">
					<button type="button" id="btnSearch">검색</button>
				</span>
			</td>
		</tr>	
	</table>
</div>

<table class="list_table" style="width: 85%" id="_list_table">
</table>

<br>
<div id="pagingMain" class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"  style="justify-content:center;"></ul>
    </nav>
</div>
<br>

<script type="text/javascript">
$(document).ready(function() {
	ajax($("#_choice").val(),$("#_search").val(),0,$("#datepicker1").val(),$("#datepicker2").val());
});

$("#btnSearch").click(function() {
	ajax($("#_choice").val(),$("#_search").val(),0,$("#datepicker1").val(),$("#datepicker2").val());
});

let totalCount = 0;	//글의 총수
let pageSize = 10;		//페이지의 크기 1 ~ 10	[1]~[10]
let pageNumber = 1;		//현재페이지

function ajax(Vchoice, Vsearch, VpageNumber, VstartDate, VendDate) {
	$('#pagingMain').empty();
	$('#_list_table').empty();
	$.ajax({
		url:"calsearchAjax.do",
		data:{ choice:Vchoice, search:Vsearch, pageNumber:VpageNumber, startDate:VstartDate, endDate:VendDate },
		success:function(map){
			/* 검색고정 */
			$("#_choice").val(map.search.choice);
			$("#_search").val(map.search.search);
			$("#datepicker1").val(map.search.startDate);
			$("#datepicker2").val(map.search.endDate);
			/* 페이징 값변경 */
			totalCount = map.totalCount;
			pageNumber = map.search.pageNumber + 1;
			/* 게시판 셋팅 */
			let setting = '<colgroup>' +
							'<col style="width: 70px">' + 
							'<col style="width: auto">' +
							'<col style="width: 150px">' +
							'<col style="width: 80px">' +
						  '</colgroup>' +
						  '<tr>'+
						  	'<th>번호</th><th>제목</th><th>일정</th><th>중요도</th>'+
						  '</tr>';
			
			$('#_list_table').append(setting);	
			
			if(map.callist == null && map.callist[0].id == ""){
				let add = '<tr><td colspan="4">작성된 일정이 없습니다</td></tr>'
				$('#_list_table').append(add);	
			}
			/* 게시판리스트 추가 */
			$.each(map.callist, function(index,dto){
					
				let add = '<tr>' +
							'<td>' + (index+1) + '</td>' +
							'<td style="text-align: left;">' +
								'<a href="caldetail.do?seq=' + dto.seq + '">' +
									dto.title +
								'</a>' +
							'</td>' +
							'<td>' + dto.rdate.substring(0, 4) + '년 ' + dto.rdate.substring(5, 6) + '월 ' + dto.rdate.substring(6, 8) + '일' + '</td>';
							if(dto.important == 'verymuch'){
								add += '<td>가장중요</td>';
							}
							if(dto.important == 'very'){
								add += '<td>중요</td>';
							}
							if(dto.important == 'normal'){
								add += '<td>보통</td>';
							}
							add += '</tr';
				$('#_list_table').append(add);		
			});
			/* 페이징 추가 */
			let paging = '<nav aria-label="Page navigation">' +
	        				'<ul class="pagination" id="pagination"  style="justify-content:center;"></ul>' +
	        			 '</nav>'
			$('#pagingMain').append(paging);
			
			let _totalPages = Math.floor(totalCount / 10);
			if(totalCount % 10 > 0){
				_totalPages++;
			}
			$("#pagination").twbsPagination({
				startPage: pageNumber,
				totalPages: _totalPages,
				visiblePages: 10,
				initiateStartPageClick:false,
				first:'<span sris-hidden="true">«</span>',
				prev:"이전",
				next:"다음",
				last:'<span sris-hidden="true">»</span>',
				onPageClick:function(event, page){
					ajax($("#_choice").val(),$("#_search").val(),(page-1),$("#datepicker1").val(),$("#datepicker2").val());
				}
			});	
			
		},
		error:function(){
			alert('error');
		}
	});
}
</script>
</body>
</html>