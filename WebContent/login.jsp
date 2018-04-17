x<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String CONTEXT_PATH = request.getContextPath();
	String contextName = request.getContextPath();
	String jspBasePath = contextName + "/";
	String imgPath = jspBasePath + "images";
%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>LOGIN</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="css/bootstrap.css" media="screen">
<script>

     var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-23019901-1']);
      _gaq.push(['_setDomainName', "bootswatch.com"]);
        _gaq.push(['_setAllowLinker', true]);
      _gaq.push(['_trackPageview']);

     (function() {
       var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
       ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
     })();

    </script>

<script language="javascript">

function f_submit() {
	
	
	var form = document.fm;
	
	if (form.user.value == ""){
		alert("사원번호를 입력하세요");
		form.user.focus();
		return;
	}
	if (form.pass.value == ""){
		alert("비밀번호를 입력하세요");
		form.pass.focus();
		return;
	}
	
	form.action = "<%=contextName%>/login.do?act=LOGIN";
		form.submit();

	}
</script>
</head>
<body>
	<div class="container">
		<div class="bs-docs-section">

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="containers">주문관리 관리 시스템</h1>
					</div>
					<div class="bs-component">
						<div class="jumbotron">
							<h1>LOGIN</h1>
							<p>발급 받은 사원번호와 비빌번호를 입력하여 로그인 해주세요</p>
							<form class="form-horizontal" name="fm" method=post
								action="<%=contextName%>/login.do?act=LOGIN">
								<div class="form-group">
									<label for="inputEmail" class="col-lg-1 control-label">사원번호</label>
									<div class="col-lg-4">
										<input type="text" class="form-control" id="inputId"
											name="user" placeholder="사원번호">
									</div>
									<label for="inputPassword" class="col-lg-1 control-label">비밀번호</label>
									<div class="col-lg-4">
										<input type="password" class="form-control" id="inputPw"
											name="pass" placeholder="비밀번호">
									</div>
									<div class="col-lg-1">
										<p>
											<a class="btn btn-primary btn-lg"
												href="javascript:f_submit()">로그인</a>
										</p>
										<!--자바스크립트 걸기..  -->
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>