<%@page import="bit.com.a.util.BbsArrow"%>
<%@page import="bit.com.a.dto.SearchDto"%>
<%@page import="bit.com.a.dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String msg = (String)request.getAttribute("msg");
if(msg != null){
%>
	<script type="text/javascript">
		alert('<%=msg%>');
	</script>
<%
}
%>


<script type="text/javascript">
$(document).ready(function() {
	// 검색한 선택사항, 검색내용 저장하는 기능
	document.getElementById("_searchWrod").value = '${ search.search }';
	document.getElementById("_choice").value = '${ search.choice }';
	
});
</script>

<jsp:useBean id="uc" class="bit.com.a.util.BbsArrow"/> 
<%
BbsArrow arrow = new BbsArrow();
%>
<!-- 검색 -->
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">
	<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
		<tr>
			<td style="padding-left: 5px">
				<select id="_choice" name="choice">
					<option value="" selected>선택</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option>
				</select>
			</td>
			<td style="padding-left: 5px">
				<input type="text" id="_searchWrod" name="searchWord">
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
	ajax($("#_choice").val(),$("#_searchWrod").val(),0);
});

$("#btnSearch").click(function() {
	ajax($("#_choice").val(),$("#_searchWrod").val(),0);
});

let totalCount = ${ totalCount };	//글의 총수
let pageSize = 10;		//페이지의 크기 1 ~ 10	[1]~[10]
let pageNumber = ${ search.pageNumber } + 1;		//현재페이지

function ajax(Vchoice, Vsearch, VpageNumber) {
	$('#pagingMain').empty();
	$('#_list_table').empty();
	$.ajax({
		url:"bbslistAjax.do",
		data:{ choice:Vchoice, search:Vsearch, pageNumber:VpageNumber },
		success:function(map){
			totalCount = map.totalCount;
			pageNumber = map.search.pageNumber + 1;
			/* 게시판 셋팅 */
			let setting = '<colgroup><col style="width: 70px"><col style="width: auto"><col style="width: 80px"><col style="width: 100px"></colgroup>' +
						  '<tr><th>번호</th><th>제목</th><th>조회수</th><th>작성자</th></tr>';
			
			$('#_list_table').append(setting);	
			
			if(map.bbslist == null && map.bbslist[0].id == ""){
				let add = '<tr><td colspan="3">작성된 글이 없습니다</td></tr>'
				$('#_list_table').append(add);	
			}
			/* 게시판리스트 추가 */
			$.each(map.bbslist, function(index,dto){
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
							'<td style="text-align: left;">' +
									ts + 
								'<a href="bbsdetail.do?seq=' + dto.seq + '">' +
									dto.title +
								'</a>' +
							'</td>' +
							'<td>' + dto.readcount +'</td>' +
							'<td>' + dto.id + '</td>' +
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
					ajax($("#_choice").val(),$("#_searchWrod").val(),(page-1));
				}
			});	
			
		},
		error:function(){
			alert('error');
		}
	});
}
</script>