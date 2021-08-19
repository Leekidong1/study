<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form name="frmForm" id="_frmForm" action="pdsupdateAf.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="seq" value="${pds.seq}">
	<table class="list_table">
		<tr>
			<th>아이디</th>
			<td style="text-align: left;">
				<input type="text" name="id" readonly="readonly" value="${pds.id}" size="50">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left;">
				<input type="text" name="title" size="50" value="${pds.title}">
			</td>
		</tr>
		<tr>
			<th>파일 업로드</th>
			<td style="text-align: left">
				<p style="font-size: 12px">기존 파일명:${pds.filename}</p>
				<input type="file" name="fileload" style="width: 400px">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left;">	<!-- summernote, smart editor - 글자,이미지 같이 올리는 기능 (구글찾아볼것) -->
				<textarea rows="10" cols="50" name="content">${pds.content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="height: 50px; text-align: center;">
				<a href="#none" id="_btnPds" title="수정완료">
					<img alt="" src="image/bupdate.png">
				</a>
			</td>
		</tr>
	</table>
</form>

<script>
$("#_btnPds").click(function() {
	
	//빈칸체크
	
	
	$("#_frmForm").submit();
});

</script>