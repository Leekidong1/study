<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- utf-8설정 -->
<fmt:requestEncoding value="utf-8" /> <!-- 시간형, encoding format 설정할 때 많이 쓰임 -->

<jsp:useBean id="nows" class="java.util.Date" />
<!-- useBean : Object생성할 때 쓰임. -->
<%--
 위코드 해석:	Date nows = new Date();
--%>

<div style="width: 100%; height: 53px; border-bottom: 1px solid #5e5e5e">
	<div style="width: 100%; height: 100%; clear: both; display: inline-block;">
		<div id="_title_image" style="width: 30%; float: left; display: inline;">
			<img alt="" src="./image/bbslogo1.jpg" style="height: 53px">
		</div>
		<div id="_title_today" style="width: 70%; float: left; text-align: right;">
			<div style="position: relative; top: 27px;">
				<c:if test="${login.id != '' }">
					<a href="logout.do" title="로그아웃">[로그아웃]</a>&nbsp;&nbsp;&nbsp;
				</c:if>
				
				<c:if test="${login.name != '' }">
					[${login.name }]님 환영합니다
				</c:if>
				<fmt:formatDate value="${ nows }" var="now" pattern="yyyy/MM/dd"/>
				${ now }
			</div>
		
		</div>
	</div>
</div>