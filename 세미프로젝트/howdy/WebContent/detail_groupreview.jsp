<%@page import="dto.MemberDto"%>
<%@page import="dao.ReviewDao"%>
<%@page import="dto.ReviewDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
public String star(int score){
	String result = "";
	String star = "<img src='lib/images/star-on.png'>";
	for(int i=0; i<score; i++){
		result = result + star;
	}
	return result;
}

%>
    
<%
String id = "";
int group_auth = 0;
int auth = 0;
MemberDto dto = (MemberDto)session.getAttribute("login");
if(dto != null){
	id = dto.getId();
	group_auth = dto.getGroup_auth();
	auth = dto.getAuth();
}


int seq = Integer.parseInt(request.getParameter("seq")); //+++++ 겟파라미터 seq 추가
ReviewDao dao = ReviewDao.getInstance();
List<ReviewDto> list = dao.getReviewBbsList(seq);
%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<title>Insert title here</title>
<style type="text/css"> /* 0702추가 35~61행 */

.authorcell{
	padding-left: 10px;
	font-family: "Times New Roman";
}

.starcell{
	padding-right: 160px;	
}

.titlecell{
	text-align: right;
	height: 60px;
	font-style: italic;
	font-size: 22px;
	font-weight: bold;
	padding-right: 30px;
	padding-top: 10px;
}

.contentcell{
	font-family: "Noto Sans KR";
	padding-bottom: 25px;
	min-height: 80px;
}

</style>
</head>
<body>

<p align="right"><button class="btn btn-primary" onclick="reviewWriteBtn(<%=seq%>)">후기 작성</button></p>
<div class="card text-center shadow-lg mb-3">
<table>
<col width="100"><col width="100"><col width="600">
	<% 
	if(list == null || list.size() == 0) {
		%>
		<tr>
			<td colspan="3"> 작성된 리뷰가 없어요.
		</tr>
	<%
	}else{
		for(int i = 0; i < list.size(); i++){
			ReviewDto rev = list.get(i);
				if(rev.getDel()==1){
					%>
					<tr>
						<td colspan="3" bgcolor="pink"> <font color="#ff0000">삭제된 리뷰입니다.</font>
					</tr>
					<%
				}else{
					%>
					<tr class="bg-light">
						<td colspan="2"><div class="authorcell">작성자 : <%=rev.getId()%></div></td>
						<td><div class="starcell"><%=star(rev.getScore()) %></div></td> <!--+++++기존 평점위치  -->
					</tr>
					<tr class="card-title">
						<td colspan="3"><div class="titlecell">"<%=rev.getTitle() %>"</div></td> <!--+++++ 변경된평점위치 -->
					</tr>
					<tr class="card-body">
						<td colspan="3"><div class="contentcell"><%=rev.getContent() %></div></td>
					</tr>
					<%
				}
				if(rev.getId().equals(id) || auth == 1 || group_auth == 3){
					%>
					<tr class="bg-light">
						<td colspan="3">
							<div align="right">
								<input type="button" class="btn btn-primary" value="수정" onclick="update(<%=rev.getReview_seq()%>)">
								<input type="button" class="btn btn-primary" value="삭제" onclick="del(<%=rev.getReview_seq()%>)">
							</div>
						</td>
					</tr>
					<%
				} 
		}
	}
	%>
</table>
</div>
</body>

<script type="text/javascript">
function reviewWriteBtn(seq) {
	location.href = "ReviewController?param=reviewWriteBtn&seq="+seq; //+++++ seq값전달표현식 추가 
}
function del(r_seq) {
	let group_seq = <%=seq%>;
	location.href = "ReviewController?param=deleteReview&seq="+r_seq+"&group_seq=" + group_seq; 
}
function update(r_seq) {
	let group_seq = <%=seq%>;
	location.href = "detail_reivewupdate.jsp?review_seq="+r_seq+"&group_seq=" + group_seq; 
}
</script>
</html>