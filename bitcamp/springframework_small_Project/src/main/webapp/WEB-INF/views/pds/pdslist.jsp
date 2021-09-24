<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="list_table" style="width: 85%">
<colgroup>
	<col width="50"><col width="100"><col width="300"><col width="50">
	<col width="50"><col width="50"><col width="100"><col width="50">
</colgroup>

<thead>
	<tr>
		<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
		<th>조회수</th><th>다운수</th><th>작성일</th><th>삭제</th>
	</tr>
</thead>

<tbody>
	<c:forEach var="pds" items="${pdslist}" varStatus="i">
		<tr>
			<th>${i.count}</th>
			<td>${pds.id}</td>
			<td style="text-align: left;">
				<a href="pdsdetail.do?seq=${pds.seq}">
					${pds.title}
				</a>
			</td>
			<td>
				<input type="button" name="btnDown" value="다운로드" onclick="filedown('${pds.newfilename}','${pds.seq}','${pds.filename}')">
			</td>
			<td>${pds.readcount}</td>
			<td>${pds.downcount}</td>
			<td>
				<font size="1">${pds.regdate}</font>
			</td>
			<td>
				<img alt="" src="./image/del.png" data_file_seq="${pds.seq}" class="btn_fileDelete"><!-- data_file_seq 사용자 지정속성 -->
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>

<!-- 자료추가버튼 -->
<div id="button.wrap">
	<span class="button blue">
		<button type="button" id="_btnAdd">자료추가</button>
	</span>
</div>

<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="seq">
	<input type="hidden" name="filename">
</form>

<script>
$("#_btnAdd").click(function() {
	location.href = "pdswrite.do";
});

$(".btn_fileDelete").click(function() {
	let data_file_seq = $(this).attr("data_file_seq");
	location.href = "deletePds.do?seq=" + data_file_seq;
});

function filedown(newfilename, seq, filename) {
	
	//location.href = "fileDownload.do?newfilename=" + newfilename + "&seq=" + seq + "&filename=" + filename;
	
	let doc = document.file_Down;
	doc.newfilename.value = newfilename;
	doc.seq.value = seq;
	doc.filename.value = filename;
	doc.submit();
	
}
</script>