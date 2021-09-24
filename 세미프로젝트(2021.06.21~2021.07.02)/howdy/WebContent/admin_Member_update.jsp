<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%!
// Date => String로 바꿔주는 함수 : 202106211611 -> 2021년 6월 21일 16시 11분
public String toDates(String mdate){

	//202106211611 => 2021-06-21 16:11
	String s = mdate.substring(0, 4) + "년" // yyyy
			 + mdate.substring(4, 6) + "월" // mm
			 + mdate.substring(6, 8) + "일" // dd
			 + mdate.substring(8, 10) + "시" // hh
			 + mdate.substring(10, 12) + "분"; // mm:ss
	return s;
}
%>

<%
String id = request.getParameter("id");
MemberDao dao = MemberDao.getInstance();
MemberDto dto = dao.getMember(id);


int date = Integer.parseInt(dto.getBirth());
Calendar cal = Calendar.getInstance();
cal.set(date/10000, date/100%10-1, date%100);
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;
int tday = cal.get(Calendar.DATE);
%> 

<!DOCTYPE html>
<html>
<title>my page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
#table{
	margin-top: 100px;
} 
</style>
<body>

<div align="center">
	<form action="member" method="post">
	<input type="hidden" name="param" value="updateMember">
		<table id="table">
			<col width="100px"><col width="100px">
			<tr>
				<th>ID</th>
				<td>
					<input type="text" id="id" name="id" class="form-control input-lg" value="<%=dto.getId() %>" size="40" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th rowspan="2">Password</th>
				<td>
					<input type="password" id="pwd" name="pwd" class="form-control input-lg" placeholder="비밀번호 입력" size="40">
				</td>
			</tr>
			<tr>
				<td>
					<input type="password" id="pwdcheck" name="pwdcheck" class="form-control input-lg" placeholder="비밀번호를 다시 입력해주세요." size="40">
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text"  class="form-control input-lg" id="name" value="<%=dto.getName() %>" name="name" size="10">&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" id="gender" name="gender" value="male">&nbsp;남 
					<input type="radio" id="gender" name="gender" value="female" checked="checked">&nbsp;여
				</td>
			</tr>
		 	<tr>
		 		<th>생년월일</th>
				<td>
					<select class="form-control" name="year">
					<%
						for(int i = tyear - 70;i <= tyear; i++){
							%>	
							<option <%=(tyear+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>
							
							<%
						}		
					%>		
					</select>	
					
					<select class="form-control" name="month">
					<%
						for(int i = 1;i <= 12; i++){
							%>	
							<option <%=(tmonth+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>
							
							<%
						}		
					%>		
					</select>
					
					<select class="form-control"  name="day">
					<%
						for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
							%>	
							<option <%=(tday+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>
				</td>
			</tr> 
			<tr>
				<th>지역</th>
				<td>
					<div>등록된 주소 : <%=dto.getLocation() %></div>
					<input type="text" class="form-control input-lg" name="address" id="address" placeholder="주소">
					<input type="text" class="form-control input-lg" name="detailAddress" id="detailAddress" onchange="checkMap()" placeholder="상세주소">
					<input type="text" class="form-control input-lg" id="extraAddress" placeholder="참고항목">
					<div class="msg"></div>	
				</td>
				<td>
					<input type="button" class="btn btn-warning" onclick="Postcode()" value="주소찾기">
				</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td>
					<input type="text" id="phone" name="phone" class="form-control input-lg" value="<%=dto.getPhone() %>" size="40" placeholder="하이픈(-)제외">
				</td>
			</tr>
			<tr>
				<th>이메일주소</th>
				<td>
					<input type="text" id="email" name="email" class="form-control input-lg" value="<%=dto.getEmail() %>" size="30" placeholder="이메일주소를 입력하세요">
				</td>
			</tr> 
			<tr>
				<th>카테고리</th>
				<td>
					<select name="category" class="form-control" style="width:330px">
			            <option value="sports">운동/스포츠</option>
			            <option value="entertaiment">문화/공연/축제</option>
			            <option value="readBook">책/글/인문학</option>
			            <option value="language">외국/언어</option>
			            <option value="vehicle">차/오토바이</option>
			            <option value="game">게임</option>
			            <option value="cook">요리</option>
			            <option value="pets">반려동물</option>
		         	</select>
				</td>
			</tr>	
			<tr>
				<td style="text-align: center;" colspan="2">
					<input type="submit" class="form-control input-lg" onclick="send()" value="수정하기" style="padding: 20px;">
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$("select[name='day']").val("<%=(tday+"") %>");
$(document).ready(function() {
	
	$("select[name='month']").change( setMonth );
	
	function setMonth() {
//		alert('setDay()');
		let year = $("select[name='year']").val();
		let month = $("select[name='month']").val();

		let lastday = (new Date(year, month, 0)).getDate();
//		alert(lastday);

		// 날짜 적용
		let str = '';
		for(i = 1;i <= lastday; i++){
			str += "<option value='" + i + "'>" + i + "</option>";
		}
		
		$("select[name='day']").html(str);
	}

	$('#category option[value="<%=dto.getCategory()%>"]').prop('selected', true);
});
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!-- 우편변호 -->
<script>
////////////////////////////우편번호 JS영역////////////////////////////////
function Postcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
</script>

</body>
</html>