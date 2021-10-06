<%@page import="java.util.Calendar"%>
<%@page import="bit.com.a.dto.CalendarPlugDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link href="<%=request.getContextPath() %>/fcalLib/main.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/fcalLib/main.js"></script>


<style type="text/css">
.body {
  margin: 40px 10px;
  padding: 0;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 12px;
}
#calendar{
	max-width: 900px;
	margin: 0 auto;
}
select{
	width: 60px; 
	height: 30px;
	margin-right: 3px;
}
</style>

<%
/* 
List<CalendarPlugDto> list = (List<CalendarPlugDto>)request.getAttribute("callist");
String events = "[";
for(CalendarPlugDto c : list){
	events += "{ id:" + c.getSeq() + ", title:'" + c.getTitle() 
				+ "', start:'" + c.getStartdate() + "', end:'" + c.getEnddate() 
				+ "', constraint:'" + c.getContent() + "' },";
}
events = events.substring(0, events.lastIndexOf(","));
events += "]";
System.out.println(events);
request.setAttribute("events", events); 
*/
%>

<%
Calendar cal = Calendar.getInstance();
int hour = cal.get(Calendar.HOUR);
int min = cal.get(Calendar.MINUTE);
%>


<script type="text/javascript">
let add ="";
document.addEventListener("DOMContentLoaded", function () {
	
	let calendarEl = document.getElementById("calendar");	
	let calendar = new FullCalendar.Calendar(calendarEl, {		
		headerToolbar: {
			left: "prev next today",
			center: "title",
			right: "dayGridMonth,timeGridWeek,timeGridDay,listMonth"
		},
		initialDate: new Date(),	// 처음 실행시 설정 날짜 오늘인 경우 '2021-08-21'
		locale: 'ko',				// 한글설정
		navLinks: true,
		businessHours: true,

	});
	
	// 날짜클릭
	calendar.on("dateClick", function (info) {
	//	alert('dateClick');
		$("#exampleModal").modal('show');
		$("#date").val(info.dateStr);
	});
	
	// 일정클릭
	calendar.on("eventClick", function (info) {
		$("#startdate").val("");
		$("#enddate").val("");
	//	alert("eventClick");
		$("#exampleModal1").modal('show');
	//	alert(info.event.title);
		$("#titledetail").val(info.event.title);
		$("#startdate").val(dateFormat(info.event.start));
		$("#enddate").val(dateFormat(info.event.end));
		$("#contentdetail").val(info.event.constraint);
	});
	
	$.ajax({
		url:"calendarpluglistAjax.do",
		success:function(list){
			$.each(list, function(index,dto){
				calendar.addEvent({
					id:dto.seq,
					title:dto.title,
					start:dto.startdate,
					end:dto.enddate,
					constraint:dto.content
				})
			});

		},
		error:function(){
			alert('error');
		}
	});
	
	calendar.render();	
	
});

function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}

</script>
<div class="body">
	<div id="calendar"></div>
</div>



<!-- 모달 창 테두리 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <!-- 모달 대화창 본체-->
  <div class="modal-dialog" role="document">
    <!-- 모달 콘텐츠 부분 -->
    <div class="modal-content">
      <!-- 모달 헤더ー -->
      <div class="modal-header">
        <!-- 모달 제목 -->
        <h5 class="modal-title" id="exampleModalLabel">일정 추가</h5>
        <!-- 닫기 아이콘 -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
         <span aria-hidden="true">&times;</span>
        </button>
      </div>
    <form method="post"  name="frmForm" id="_frmForm">
      <!-- 모달 본문 -->
      <div class="modal-body">
	      <div class="form-group row">
	      	<label for="name" class="col-md-3 col-form-label">날짜</label>
	      	<div class="col-md-9">
	         	<input type="text" class="form-control" name="date" id="date" required readonly="readonly">
	       	</div>
	     </div>
	     <div class="form-group row">
	      	<label for="name" class="col-md-3 col-form-label">일정제목</label>
	      	<div class="col-md-9">
	         	<input type="text" class="form-control" name="title" id="title" required>
	       	</div>
	     </div>
	     <div class="form-group row">
	      	<label for="name" class="col-md-3 col-form-label">내용</label>
	      	<div class="col-md-9">
	         	<textarea rows="10" cols="10" class="form-control" name="content" id="content" required></textarea>
	       	</div>
	     </div>
	     <div class="form-group row">
	      	<label for="name" class="col-md-3 col-form-label">일정시간</label>
	      	<div class="col-md-9">
	         	<select name="shour">
					<%
						for(int i = 1; i < 24; i++){
					%>
						<option <%=(hour+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
				</select>시
					
				<select name="smin">
					<%
						for(int i = 0; i < 60; i++){
					%>
						<option <%=(min+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
				</select>분 ~
				<select name="ehour">
					<%
						for(int i = 1; i < 24; i++){
					%>
						<option <%=(hour+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
				</select>시
					
				<select name="emin">
					<%
						for(int i = 0; i < 60; i++){
					%>
						<option <%=(min+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
				</select>분
	       	</div>
	     </div>
      </div>
     </form>
      
      <!-- 모달 꼬리말 -->
      <div class="modal-footer">
      	<button type="button" onclick="submit()" class="btn btn-secondary">일정추가</button>
        <!-- 닫기 버튼 -->
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- 켈린더 딕테일 -->
<!-- 모달 창 테두리 -->
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <!-- 모달 대화창 본체-->
  <div class="modal-dialog" role="document">
    <!-- 모달 콘텐츠 부분 -->
    <div class="modal-content">
      <!-- 모달 헤더ー -->
      <div class="modal-header">
        <!-- 모달 제목 -->
        <h5 class="modal-title" id="exampleModalLabel">일정 상세</h5>
        <!-- 닫기 아이콘 -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
         <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <!-- 모달 본문 -->
      <div class="modal-body">
       	<div class="form-group row">
	         <label for="name" class="col-md-3 col-form-label">제목</label>
	         <div class="col-md-9">
	           <input type="text" class="form-control" id="titledetail" readonly required>
	         </div>
        </div>
        <div class="form-group row">
	         <label for="name" class="col-md-3 col-form-label">시작일정</label>
	         <div class="col-md-9">
	           <input type="text" class="form-control" id="startdate" readonly required>
	         </div>
        </div>
        <div class="form-group row">
	         <label for="name" class="col-md-3 col-form-label">종료일정</label>
	         <div class="col-md-9">
	           <input type="text" class="form-control" id="enddate" readonly required>
	         </div>
        </div>
        <div class="form-group row">
	         <label for="name" class="col-md-3 col-form-label">내용</label>
	         <div class="col-md-9">
	           <input type="text" class="form-control" id="contentdetail" readonly required>
	         </div>
        </div>
      </div>
      
      
      <!-- 모달 꼬리말 -->
      <div class="modal-footer">
         <button type="button" class="btn btn-secondary">수정</button>
         <button type="button" class="btn btn-secondary">삭제</button>
        <!-- 닫기 버튼 -->
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
function submit() {
	$("#_frmForm").attr({ "target":"_self", "action":"calplugwriteAf.do" }).submit();
}
</script>