<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@page import="db.DBConnection"%> <!-- 6/28 수정 : 추가 -->
<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String id = "";
int seq = Integer.parseInt(request.getParameter("seq"));
System.out.println(seq);
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>  
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
<%
}else{
	id = mem.getId();
}

String choice = request.getParameter("choice");
if(choice == null){
	choice = "";
}

BbsDao dao = BbsDao.getInstance();

//페이지 번호
String sPageNumber = request.getParameter("pageNumber");
int pageNumber = 0;
if(sPageNumber != null && !sPageNumber.equals("")){
	pageNumber = Integer.parseInt(sPageNumber);
}
System.out.println("choice는 뭐받았어요?"+choice);


List<BbsDto> list = dao.getBbsSearchList(choice, pageNumber);

if(list != null && list.size() != 0){
System.out.println(list.get(0).getId());
}
//글의 총수
int len = dao.getAllBbs();		// **만들자~!~!
System.out.println("총 글의 수:" + len);

//페이지 수
int bbsPage = len / 10;		// 24 / 10 -> 2
if((len % 10) > 0){
	bbsPage = bbsPage + 1;
}
%>

<!-- 댓글 -->
<%!
public String replys(int depth){
	String rs = "<img src='./image/question.png'>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
	String ts = "";
	for(int i=0; i<depth; i++){
		ts = ts + nbsp;
	}
	return depth==0?"":ts + rs;
}
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓴거 보이는곳</title>
<!-- 6/28 수정 : 추가(67줄까지)-->
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// select
	let obj = document.getElementById("choice");
	obj.value = "detail_bbs.jsp?choice=<%=choice %>";
	obj.setAttribute("selected", "selected");
});
</script>
<style type="text/css">
.replyBtns button{
	font-size: 15px;
	margin: 3px;
}
</style>
</head>
<body>
<div class="card text-center shadow-lg mb-3">
<div align="right">
<!-- BoardWrite.jsp -> detail_bbsWrite.jsp 수정 -->
<button type="button" class="btn btn-secondary" onclick="location.href='detail_bbsWrite.jsp?seq=<%=seq%>'">게시판 글쓰기</button>
	<div align="center">
	<form action="Bbs" method="get">
	<input type="hidden" name="param" value="pagging">
	<input type="hidden" name="seq" value="<%=seq%>">
		<!-- 6/28 수정 : 추가(88줄까지)-->
		<select name="choice"> 
			<option value="all" selected="selected">전체보기</option>
			<option value="free">자유글</option>
			<option value="hello">가입인사</option>
			<option value="notice">공지사항</option>
		</select>
		<button type="submit" class="btn btn-secondary" style="font-size: 13px">검색</button>
	</form>
	<br><br>
	</div>
</div>
<div align="center">
<table >
<col width="70"><col width="200"><col width="150">

<%
MemberDao Mdao = MemberDao.getInstance();
if(list == null || list.size() == 0){
	%>
		<tr>
			<td colspan="3">작성된 글이 없습니다</td>
		</tr>
	<%
}else{
	for(int i = 0;i < list.size(); i++){
		BbsDto bbs = list.get(i);
		int group_auth = Mdao.getGroup_Auth(bbs.getId());
		if(bbs.getDepth() == 0){
	%>
		<tr>
			<td colspan="3">
				<div style="display: flex">
					<div align="center" style="margin-right: 30px; border-radius: 5px;">
						<%=replys(bbs.getDepth()) %>
						<%if(bbs.getType().equals("free")) {%>
						<img src = "./img/topicfree.png" width="50px" height="30px">
						<p style="font-size: 12px"><b>자유글</b></p>
						<% }else if(bbs.getType().equals("notice")){ %>
						<img src = "./img/notice.png" width="25px" height="30px">
						<p style="font-size: 12px"><b>공지사항</b></p>
						<% }else{ %>
						<img src = "./img/greeting.png" width="50px" height="30px">
						<p style="font-size: 12px"><b>가입인사</b></p>
						<% } %>
					</div>	
					<div style="display: flex; border-radius: 5px; padding: 10px; background-color: #E6E6E6; box-shadow: 5px 5px 8px #D8D8D8;">
						<div style="width: 400px">
							<span style="font-size: 13px">
								<span><strong>아이디:</strong>&nbsp;<%=bbs.getId() %></span>
								<%if(group_auth==3){  %> <!-- 0630현재로그인한유저의 권한출력 -> 게시글을 작성한 유저의 권한출력 -->
								<span>&nbsp;&nbsp;&nbsp;『모임장』</span>
								<%}else if(group_auth==2){ %>
								<span>&nbsp;&nbsp;&nbsp;『부모임장』</span>
								<%}else{ %>
								<span>&nbsp;&nbsp;&nbsp;『모임원』</span>
								<%} %>
							</span>
							<%
							if(bbs.getDel() == 0){
								%>
								<br><br><pre><%=bbs.getContent() %></pre>
								<%
							}else{
								%>		
								<font color="#ff0000">********* 이 글은 작성자에 의해서 삭제되었습니다</font> 
								<%
							}
							%>
						</div>
						<div align="center" class="replyBtns">
							<div>
								<button style="font-size: 11px;" class="btn btn-warning" onclick="addReply(<%=bbs.getBbs_seq() %>)">댓글작성</button>
							</div>
							<% 
								if(id.equals(bbs.getId())){ 
							%>
							<button style="font-size: 11px;" class="btn btn-warning" onclick="editBbs(<%=bbs.getBbs_seq()%>)">수정</button>&nbsp;
							<button style="font-size: 11px;" class="btn btn-warning" onclick="delBbs(<%=bbs.getBbs_seq()%>)">삭제</button>
							<% 
								} 
							%>
						</div>
					</div>
				</div>	
			</td>
		</tr>
		<tbody id="reply">
			<tbody id="writeReplay"></tbody>
			<tbody id="showlist"></tbody>
		</tbody>
		<tr>
			<td>
				<br>	<!-- 빈공간주기 -->
			</td>
		</tr>
		<%
		}else{	// depth가 1이상
		%>
		<tr>
			<td colspan="3">
				<div style="display: flex">
					<div style="margin-right: 30px;">
						<%=replys(bbs.getDepth()) %>
					</div>	
					<div style="display: flex; border-radius: 5px; padding: 10px; background-color: #E6E6E6; box-shadow: 5px 5px 8px #D8D8D8;">
						<div style="width: 400px">
							<span style="font-size: 13px">
								<span><strong>아이디:</strong>&nbsp;<%=bbs.getId() %></span>
								<%if(group_auth==3){  %> <!-- 0630현재로그인한유저의 권한출력 -> 게시글을 작성한 유저의 권한출력 -->
								<span>&nbsp;&nbsp;&nbsp;『모임장』</span>
								<%}else if(group_auth==2){ %>
								<span>&nbsp;&nbsp;&nbsp;『부모임장』</span>
								<%}else{ %>
								<span>&nbsp;&nbsp;&nbsp;『모임원』</span>
								<%} %>
							</span>
							<%
							if(bbs.getDel() == 0){
								%>
								<p><%=bbs.getContent() %></p>
								<%
							}else{
								%>		
								<font color="#ff0000">********* 이 글은 작성자에 의해서 삭제되었습니다</font> 
								<%
							}
							%>
						</div>
						<div align="center" class="replyBtns">
							<div>
								<button style="font-size: 11px;" class="btn btn-warning" onclick="addReply(<%=bbs.getBbs_seq() %>)">댓글작성</button>
							</div>
							<% 
								if(id.equals(bbs.getId())){ 
							%>
							<button style="font-size: 11px;" class="btn btn-warning" onclick="editBbs(<%=bbs.getBbs_seq()%>)">수정</button>&nbsp;
							<button style="font-size: 11px;" class="btn btn-warning" onclick="delBbs(<%=bbs.getBbs_seq()%>)">삭제</button>
							<% 
								} 
							%>
						</div>
					</div>
				</div>	
			</td>
		</tr>
		<tbody id="reply">
			<tbody id="writeReplay"></tbody>
			<tbody id="showlist"></tbody>
		</tbody>
		<tr>
			<td>
				<br>	<!-- 빈공간주기 -->
			</td>
		</tr>		
		<%
		}
	}
}
%>
</table>
<br><br>
<%
for(int i = 0;i < bbsPage; i++){
	if(pageNumber == i){	// 현재페이지		[1] 2 [3]
		%>
		<span style="font-size: 15pt; color: #0000ff; font-weight: bold;">
			<%=i + 1 %>
		</span>&nbsp;
		<%		
	}else{					// 그밖에 페이지
		%>
		<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
			style="font-size: 15pt;color: #000;font-weight: bold;text-decoration: none;">
			[<%=i + 1 %>]
		</a>&nbsp;
		<%
	}
}
%>

</div> 
</div>
<script type="text/javascript">
/* 
function searchBbs() {

	let choice = document.getElementById("choice").value;
	alert(choice);
	location.href = "detail_bbs.jsp?choice=" + choice + "&pageNumber=" + pageNum;
}
 */
 
 let g_seq = <%=seq%>;
 
function goPage( pageNum ) {
	let choice = document.getElementById('choice').value;
	
	location.href = "Bbs?param=pagging&seq=" + g_seq + "&choice=" + choice + "&pageNumber=" + pageNum ;
}
 
function editBbs(b_seq) {
	location.href = "detail_bbsUpdate.jsp?b_seq="+b_seq+"&g_seq="+g_seq; 
}

function delBbs(b_seq) {
	location.href = "Bbs?param=bbsDeleteAf&b_seq="+b_seq+"&g_seq="+g_seq; 
}
let replycount = 0;
function addReply(bbs_seq) {
	if(replycount == 0){
		let reply =	'<tr>' +
						'<td align="center">'+
							'<p style="font-size: 13px"><b>작성자<br><%=id%></b></p>' +
						'</td>'+
						'<td align="left">' + 
							'<textarea class="form-control" id="replyAnswer" rows="5" cols="30" style="margin-top: 10px"></textarea>' +
						'</td>' + 
						'<td>' +
							'<button class="btn btn-warning" onclick="sendReply(' + bbs_seq + ')">작성완료</button>' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						
					'</tr>';
					
		$('#writeReplay').append(reply); 
		replycount++;
	}else{
		$('#writeReplay').empty();
		replycount--;
	}
	
}

function sendReply(bbs_seq) {
	alert('sendReply success');
	let id = '<%=id %>';
	let answer = document.getElementById('replyAnswer').value;
	alert('sendReply success '+'id:'+id+' answer:'+answer );
	location.href = 'Bbs?param=addReply&id=' + id + '&answer=' + answer +'&bbs_seq=' + bbs_seq +'&group_seq=' + g_seq;
	
}
</script>
</body>
</html>