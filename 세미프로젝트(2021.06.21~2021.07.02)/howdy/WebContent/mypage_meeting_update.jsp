<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.MeetingDto"%>
<%@page import="dao.MeetingDao"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<%
int seq = Integer.parseInt(request.getParameter("seq"));
//String year = request.getParameter("year");
//String month = request.getParameter("month");
//String day = request.getParameter("day");
MeetingDao dao = MeetingDao.getInstance();
MeetingDto dto = dao.getMeeting(seq);
%>

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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>


<%
Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);

//cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
%>
<div align="center">
<h2>일정 수정</h2>
	<form action="meet" method="post">
	<input type="hidden" name="param" value="updateMeetingAf">
	<input type="hidden" name="meeting_seq" value="<%=seq %>">
	<input type="hidden" name="latitude" id="latitude" value=""><!-- latitude(위도) -->
	<input type="hidden" name="longitude" id="longitude" value=""><!-- longitude(경도) -->
		<table class="table table-bordered" style="width: 65%">
			<col width="200"><col width="500">
			<tr>
				<th>일정종류</th>
				<td>
					<select name="category" id="category">
			            <option value="shortTerm">단기일정</option>
			            <option value="longTerm">장기일정</option>
			            <option value="weekend">주말일정</option>
			            <option value="everySat">매주 토요일</option>
			            <option value="everySun">매주 일요일</option>
			         </select>
				</td>
			</tr>
			<tr>
				<th>지역</th>
				<td>
					<div id="pre_address"></div>
					<input type="text" name="address" id="address" placeholder="주소">
					<input type="button" onclick="Postcode()" value="주소찾기"><br>
					<input type="text" name="detailAddress" id="detailAddress" onchange="checkMap()" placeholder="상세주소"><br>
					<input type="text" id="extraAddress" placeholder="참고항목"><br>
					<div id="map"></div>
				</td>
			</tr>
			<tr>
				<th>정원</th>
				<td>
					<input type="text" size="5" name="max_mem" value="<%=dto.getMax_mem()%>">명
				</td>
			</tr>
			<tr>
				<th>시작일</th>
				<td>
					<div id="pre_date">기존입력일정: <%=toDates(dto.getSdate()) %> ~ <%=toDates(dto.getEdate()) %></div>
					<select name="syear">
					<%
						for(int i = tyear - 5;i <= tyear + 5; i++){
							%>	
							<option <%=(tyear+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>
							
							<%
						}		
					%>		
					</select>년	
					
					<select name="smonth">
					<%
						for(int i = 1;i <= 12; i++){
							%>	
							<option <%=(tmonth+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>
							
							<%
						}		
					%>		
					</select>월
					
					<select name="sday">
					<%
						for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
							%>	
							<option <%=(tday+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>일
					
					<select name="shour">
					<%
						for(int i = 1;i < 24; i++){
							%>	
							<option <%=(thour + "").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>시
					
					<select name="smin">
					<%
						for(int i = 0;i < 60; i++){
							%>	
							<option <%=(tmin + "").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>분	
				</td>
			</tr>
			<tr>
				<th>종료일</th>
				<td>
					<select name="eyear">
					<%
						for(int i = tyear - 5;i <= tyear + 5; i++){
							%>	
							<option <%=(tyear+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>
							
							<%
						}		
					%>		
					</select>년	
					
					<select name="emonth">
					<%
						for(int i = 1;i <= 12; i++){
							%>	
							<option <%=(tmonth+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>
							
							<%
						}		
					%>		
					</select>월
					
					<select name="eday">
					<%
						for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
							%>	
							<option <%=(tday+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>일
					
					<select name="ehour">
					<%
						for(int i = 1;i < 24; i++){
							%>	
							<option <%=(thour + "").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>시
					
					<select name="emin">
					<%
						for(int i = 0;i < 60; i++){
							%>	
							<option <%=(tmin + "").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
								<%=i %>
							</option>				
							<%
						}		
					%>		
					</select>분	
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" size="30" name="title" value="<%=dto.getTitle()%>">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="20" cols="60" name="content"><%=dto.getContent()%></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="일정수정">
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$("select[name='day']").val("<%=(tday+"") %>");

$(document).ready(function() {
	
	$("select[name='month']").change( setMonth );	
});

function setMonth() {
//	alert('setDay()');
	let year = $("select[name='year']").val();
	let month = $("select[name='month']").val();

	let lastday = (new Date(year, month, 0)).getDate();
//	alert(lastday);

	// 날짜 적용
	let str = '';
	for(i = 1;i <= lastday; i++){
		str += "<option value='" + i + "'>" + i + "</option>";
	}
	
	$("select[name='day']").html(str);
}
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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=397303b6b1a3824cfbfe07f9d7527206&libraries=services"></script> <!-- 카카오지도 -->
<script>
////////////////////////////지도 JS영역////////////////////////////////
function checkMap() {
	let Myaddress = document.getElementById('address').value;//  주소를 가져온다. by이기동**
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	    mapOption = {
	        center: new kakao.maps.LatLng(37.5556326132925, 126.992217614712), // 지도의 중심좌표
	        level: 5 // 지도의 확대 레벨
	    };  
	
	mapContainer.style.width = "400px";
	mapContainer.style.height = "400px";
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(Myaddress, function(result, status) {	// 주소를 입력한다. by이기동**
		
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
	    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
	    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
	    
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);	// *********검색한 주소의 좌표가져온다
			//alert(result[0].y+" "+result[0].x);
	        document.getElementById("latitude").value = result[0].y;
	        document.getElementById("longitude").value = result[0].x;
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
        	});

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+ Myaddress +'</div>'
	        });
	        
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});    
}
$(document).ready(function() {
	$('#category option[value="<%=dto.getCategory()%>"]').prop('selected', true);
	$('#pre_address').html('기존입력주소:<%=dto.getLocation() %>');
});
</script>

</body>
</html>





