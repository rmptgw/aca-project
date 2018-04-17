<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="com.mis.web.model.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String CONTEXT_PATH = request.getContextPath();
	String contextName = request.getContextPath();
	String jspBasePath = contextName + "/";
	String imgPath = jspBasePath + "images";

	int startPage = request.getAttribute("startPage") == null ? 1 : (Integer) request.getAttribute("startPage");
	int endPage = request.getAttribute("endPage") == null ? 1 : (Integer) request.getAttribute("endPage");
	int pageCnt = request.getAttribute("pageCnt") == null ? 1 : (Integer) request.getAttribute("pageCnt");
	int cpage = request.getAttribute("cpage") == null ? 1 : (Integer) request.getAttribute("cpage");
	int totalArticle = request.getAttribute("totalArticle") == null
			? 1
			: (Integer) request.getAttribute("totalArticle");
	int virtual_no = request.getAttribute("virtual_no") == null
			? 1
			: (Integer) request.getAttribute("virtual_no");

	String srch_type = request.getAttribute("srch_type") == null
			? ""
			: (String) request.getAttribute("srch_type");
	String srch_keyword = request.getParameter("srch_keyword") == null
			? ""
			: (String) request.getAttribute("srch_keyword");

	Provider provInfo = new Provider();
	provInfo = (Provider) request.getAttribute("provInfo");

	String provNo = provInfo.getProvNo() == null ? "" : provInfo.getProvNo();
	String provName = provInfo.getProvName() == null ? "" : provInfo.getProvName();
	String provCEO = provInfo.getProvCEO() == null ? "" : provInfo.getProvCEO();
	String provHP = provInfo.getProvHP() == null ? "" : provInfo.getProvHP();
	String provEmail = provInfo.getProvEmail() == null ? "" : provInfo.getProvEmail();
	String provAdd = provInfo.getProvAdd() == null ? "" : provInfo.getProvAdd();
%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>::: 주문관리 시스템 :::</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="css/bootstrap.css" media="screen">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../bower_components/html5shiv/dist/html5shiv.js"></script>
      <script src="../bower_components/respond/dest/respond.min.js"></script>
    <![endif]-->
<script>
    function f_logout()
    {
    	location.href="<%=contextName%>
	/login.do?act=LOGOUT";
	}
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-23019901-1' ]);
	_gaq.push([ '_setDomainName', "bootswatch.com" ]);
	_gaq.push([ '_setAllowLinker', true ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
<script type="text/javascript">
	function delete_click(url_param) {

		if (confirm('정말로 삭제하시겠습니까?')) {
			location.replace(url_param);
		}

	}

	function update_click(url_param) {

		location.replace(url_param);
	}

	function list_click(url_param) {

		location.replace(url_param);
	}
</script>
</head>
<body>

	<div class="container">
		<div class="bs-docs-section clearfix">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-header"></div>

					<div class="bs-component">
						<nav class="navbar navbar-inverse">
						<div class="container-fluid">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed"
									data-toggle="collapse"
									data-target="#bs-example-navbar-collapse-2">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand"
									href="<%=contextName%>/login.do?act=MAIN">MIS</a>
								<!--이미지  -->
							</div>

							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-2">
								<form class="navbar-form navbar-right" role="search" method=post
									action="<%=contextName%>/login.do?act=LOGOUT">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Search"
											value="<%=request.getSession().getAttribute("userDept").toString()%> :: <%=request.getSession().getAttribute("userName").toString()%>  "
											disabled="disabled">
									</div>
									<button type="submit" class="btn btn-default">로그아웃</button>
								</form>
								<ul class="nav navbar-nav navbar-right">
									<li><a href="<%=contextName%>/emp.do?act=EMPL">사원 관리</a></li>
								</ul>
								

								<ul class="nav navbar-nav navbar-right">
									<li class="active"><a
										href="<%=contextName%>/prov.do?act=provL">납품업체 관리 <span
											class="sr-only">(current)</span></a></li>
								</ul>
								<ul class="nav navbar-nav navbar-right">
									<li><a href="<%=contextName%>/cus.do?act=CUSL">고객관리</a></li>
								</ul>
							</div>
						</div>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="bs-docs-section">

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h3 id="tables">납품업체 상세보기</h3>
					</div>
					<div class="bs-component">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">납품업체번호</h3>
							</div>
							<div class="panel-body">
								<%=provNo%>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">납품업체이름</h3>
							</div>
							<div class="panel-body">
								<%=provName%>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">대표이사</h3>
							</div>
							<div class="panel-body">
								<%=provCEO%>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">연락처</h3>
							</div>
							<div class="panel-body">
								<%=provHP%>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">이메일</h3>
							</div>
							<div class="panel-body">
								<%=provEmail%>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">주소</h3>
							</div>
							<div class="panel-body">
								<%=provAdd%>
							</div>
						</div>
						<div class="col-lg-offset-x">

							<p class="bs-component">
								<a
									href="javascript:delete_click('<%=contextName%>/prov.do?act=provD&NO=<%=provNo%>')"
									class="btn btn-danger">삭제</a> <a
									href="javascript:update_click('<%=contextName%>/prov.do?act=provUR&NO=<%=provNo%>')"
									class="btn btn-primary">수정</a> <a
									href="javascript:list_click('<%=contextName%>/prov.do?act=provL&page=<%=cpage%>&srch_type=<%=srch_type%>&srch_keyword=<%=srch_keyword%>')"
									class="btn btn-default">목록</a>
							</p>
						</div>

					</div>
</body>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>