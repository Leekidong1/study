<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- 글씨체추가 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- css 추가 -->		
<link href="css/styles-sb-admin.css" rel="stylesheet" />

<title>Login</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<style type="text/css">
body{
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body class="bg-primary">
    <div id="layoutAuthentication">
        <div id="layoutAuthentication_content">
            <main>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-3">
                            <div class="card shadow-lg border-0 rounded-lg mt-5">
                                <div class="card-header"><h3 class="text-center font-weight-light my-4"><img src="image/howdy003.png"></h3></div>
                                <div class="card-body">
                                    <form action="member" method="post">	
									<input type="hidden" name="param" value="loginAf">
									<div class="form-group">
										<label>Login</label>
								        <input type="text" id="id" name="id" class="form-control" placeholder="ID" type="text"><br>
								        <input type="password" id="pwd" name="pwd" class="form-control" placeholder="PWD" >
									</div><br>
									<div class="form-group">
										<div class="checkbox">
										 <input type="checkbox" id="chk_save_id" >아이디 저장
										</div>
									</div><br>
									<div class="form-group">
										<input type="submit" class="btn btn-primary btn-block" onclick="account()" value="로그인">		
								    <br><br>
								    </div>
					   				</form>
                                </div>
                                <div class="card-footer text-center py-3">
                                	<div class="small"><a href="member?param=regi">Need an account? Sign up!</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <div id="layoutAuthentication_footer">
            
        </div>
    </div>

<script type="text/javascript"> 

let user_id = $.cookie("user_id");
	if(user_id != null){
		$("#id").val(user_id);
		$("#chk_save_id").prop("checked", true);
	}
	
	$(document).ready(function() {
	$("#chk_save_id").click(function () {
		//	alert('click');
		if( $("#chk_save_id").is(":checked") ){	// check가 되었을 때		
			if( $("#id").val().trim() == "" ){
				alert('id를 입력해 주십시오');
				$("#chk_save_id").prop("checked", false);
			}else{
				// id를 쿠키에 저장
				$.cookie("user_id", $("#id").val().trim(), { expires:7, path:'/' });
			}
		}
		else{
			$.removeCookie("user_id", { path:'/' });
		}	
	});
});
function account() {
   	let id = $("#id").val().trim();
   	let pwd = $("#pwd").val().trim();
   	if(id == null) {
   		alert('아이디를 입력해주세요');
   	}else if(pw == null) {
   		alert('비밀번호를 입력해주세요');
   	}
       location.href = "member?param=loginAf";
}

</script>

</body>
</body>
</html>