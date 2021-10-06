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
					<span style="font-weight: bold; margin-right: 3px">작성일검색:</span>
					<input type="text" id="datepicker1"> ~ <input type="text" id="datepicker2">
			</td>
		</tr>
		<tr>
			<td>
				<select id="_choice" name="choice">
					<option value="" selected>선택</option>
					<option value="writer">작성자</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="filename">파일명</option>
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

<!-- 자료추가버튼 -->
<div id="button.wrap" align="left">
	<span class="button blue">
		<button type="button" id="_btnAdd">자료추가</button>
	</span>
</div>
<!-- 파일다운로드 -->
<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="seq">
</form>

<br><!-- 페이징 -->
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
		url:"pdslist_ajax.do",
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
							'<col width="50">' + 
							'<col width="70">' +
							'<col width="300">' +
							'<col width="50">' +
							'<col width="50">' +
							'<col width="50">' +
							'<col width="100">' +
							'<col width="50">' +
						  '</colgroup>' +
						  '<tr>'+
						  	'<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>'+
						  	'<th>조회수</th><th>다운수</th><th>작성일</th><th>삭제</th>'+
						  '</tr>';
			
			$('#_list_table').append(setting);	
			if(map.pdslist == ''){
				let add = '<tr><td colspan="8">작성된 자료글이 없습니다</td></tr>'
				$('#_list_table').append(add);	
			}
			/* 게시판리스트 추가 */
			$.each(map.pdslist, function(index,dto){
				let rs = "<img src='./image/arrow.png' width='20px' height='20px'/>";
				let nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
				
				let ts = "";

				for(i = 0; i < dto.depth; i++){
					ts += nbsp;
				}
				if(ts != ""){
					ts += rs;
				}
								
				
				let add = '<tr>' +
							'<td>' + (index+1) + '</td>' +
							'<td>' + dto.id + '</td>' +
							'<td style="text-align: left;">' +
									ts +
								'<a href="pdsdetail.do?seq=' + dto.seq + '">' +
									dto.title +
								'</a>' +
							'</td>' +
							'<td>';
				if(dto.filename != "no"){
					add += '<input type="button" name="btnDown" value="다운로드" onclick="filedown(' + dto.seq +')">';
				}
					add +=  '</td>' +
							'<td>' + dto.readcount + '</td>' +
							'<td>' + dto.downcount + '</td>' +
							'<td>' +
								'<font size="1">' + dto.regdate + '</font>' +
							'</td>' +
							'<td>' +
								'<a href="deletePds.do?seq=' + dto.seq + '">' +
									'<img  src="./image/del.png">' +
								'</a>' +
							'</td>' +
						  '</tr';
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

$("#_btnAdd").click(function() {	//자료추가
	location.href = "pdswrite.do";
});

function filedown(seq) {	//파일다운로드
	//location.href = "fileDownload.do?newfilename=" + newfilename + "&seq=" + seq + "&filename=" + filename;
	let doc = document.file_Down;
	doc.seq.value = seq;
	doc.submit();
	
}
</script>
</body>
</html>