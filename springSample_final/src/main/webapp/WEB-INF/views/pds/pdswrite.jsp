<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form name="frmForm" id="_frmForm" action="pdsupload.do" method="post" enctype="multipart/form-data">
	<table class="list_table">
		<tr>
			<th>아이디</th>
			<td style="text-align: left;">
				<input type="text" name="id" readonly="readonly" value="${login.id}" size="50">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left;">
				<input type="text" name="title" size="50" id="title">
			</td>
		</tr>
		<tr>
			<th>파일 업로드</th>
			<td style="text-align: left">
				<input type="file" name="fileload" multiple="multiple" style="width: 400px" id="file">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left;">	<!-- summernote, smart editor - 글자,이미지 같이 올리는 기능 (구글찾아볼것) -->
				<textarea rows="10" cols="50" name="content" id="content"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="height: 50px; text-align: center;">
				<a href="#none" id="_btnPds" title="자료올리기">
					<img alt="" src="image/bwrite.png">
				</a>
			</td>
		</tr>
	</table>
</form>

<script>
$("#_btnPds").click(function() {

	//빈칸체크
	if($("#file").val() == '' || $("#title").val() == '' || $("#content").val() == ''){
		if($("#title").val() == ''){
			alert('제목 작성해주세요');	
		}
		if($("#file").val() == ''){
			alert('파일 선택해주세요');
		}
		if($("#content").val() == ''){
			alert('내용 작성해주세요');
		}
		return
	}
	
	$("#_frmForm").submit();
});

</script>