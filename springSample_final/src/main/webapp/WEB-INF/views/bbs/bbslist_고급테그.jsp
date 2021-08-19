<%@page import="bit.com.a.util.BbsArrow"%>
<%@page import="bit.com.a.dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
List<BbsDto> list = (List<BbsDto>)request.getAttribute("bbslist");
for(BbsDto bbs : list){
	System.out.println(bbs.toString());
}
--%>

<!-- UtilClass 객체생성  -->
<jsp:useBean id="uc" class="bit.com.a.util.BbsArrow"/> 
<%-- UtilClass uc = new UtilClas(); --%>

<!-- 검색 -->
<div class="box_border" style="margin-top: 5px;margin-bottom: 10px">

<table style="margin-left: auto;margin-right: auto; margin-top: 3px; margin-bottom: 3px">
<tr>
	<td>검색</td>
	<td style="padding-left: 5px">
		<select id="_choice" name="choice">
			<option value="" selected="selected">선택</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="writer">작성자</option>
		</select>	
	</td>
	<td style="padding-left: 5px">
		<input type="text" id="_search" name="search">
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
<colgroup>
	<col style="width: 70px">
	<col style="width: auto;">
	<col style="width: 80px">
	<col style="width: 100px">
</colgroup>

<tr>
	<th>번호</th><th>제목</th><th>조회수</th><th>작성자</th>
</tr>

<c:if test="${empty bbslist}">
<tr>
	<td colspan="3">작성된 글이 없습니다</td>
</tr>
</c:if>

<%--
for(int i = 0;i < bbslist.size(); i++){
	BbsDto bbs = bbslist.get(i);
}
--%>

<c:forEach items="${bbslist}" var="bbs" varStatus="i">
<!-- UtilClass 안에 있는 depth 변수 셋팅  -->
<jsp:setProperty property="depth" name="uc" value="${bbs.depth}"/>

<tr>
	<td>${i.count}</td>
	<td style="text-align: left;">		
		<jsp:getProperty property="arrow" name="uc"/>	<!--UtilClass 안에 있는 depth 변수 return => arrow이미지 -->		
		<a href="bbsdetail.do?seq=${bbs.seq}">
			${bbs.title}
		</a>
	</td>
	<td>${bbs.readcount}</td>
	<td>${bbs.id}</td>
</tr>
</c:forEach>


</table>

<script>
$("#btnSearch").click(function () {
	location.href = "bbslist.do?search=" + $("#_search").val() + "&choice=" + $("#_choice").val();	
});
</script>






