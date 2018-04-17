<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.mis.web.model.*" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String CONTEXT_PATH = request.getContextPath();
String contextName = request.getContextPath();
String jspBasePath = contextName + "/";
String imgPath = jspBasePath + "images";


ArrayList<Employee> empList = new ArrayList<Employee>();
empList = (ArrayList<Employee>)request.getAttribute("empList");


int startPage= request.getAttribute("startPage") == null ? 1 : (Integer)request.getAttribute("startPage");
int endPage= request.getAttribute("endPage") == null ? 1 : (Integer)request.getAttribute("endPage");
int pageCnt= request.getAttribute("pageCnt") == null ? 1 : (Integer)request.getAttribute("pageCnt");
int cpage = request.getAttribute("cpage") == null ? 1 : (Integer)request.getAttribute("cpage");
int totalArticle = request.getAttribute("totalArticle") == null ? 1 : (Integer)request.getAttribute("totalArticle");
int virtual_no = request.getAttribute("virtual_no") == null ? 1 : (Integer)request.getAttribute("virtual_no");

String srch_type = request.getAttribute("srch_type") == null ? "" : (String)request.getAttribute("srch_type");
String srch_keyword = request.getAttribute("srch_keyword")== null ? "" :  (String)request.getAttribute("srch_keyword");    


String empNo;
String empName;
String empPosi;
String empDept;
String empAuth;

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
    	location.href="<%=contextName%>/login.do?act=LOGOUT";
    }
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
     
     
     
     function insert_click(url_param){
    	    
    	    location.replace(url_param);
    	}
    </script>

</head>
<body>

    <div class="container">
      <div class="bs-docs-section clearfix">
        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
            </div>

<div class="bs-component">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%=contextName%>/login.do?act=MAIN">MIS</a><!--이미지  -->
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <form class="navbar-form navbar-right" role="search" method=post action="<%=contextName%>/login.do?act=LOGOUT">
        <div class="form-group">
        
                   <input type="text" class="form-control" placeholder="Search" value="<%=request.getSession().getAttribute("userDept").toString() %> :: <%=request.getSession().getAttribute("userName").toString() %>  " disabled="disabled">
        </div>
        <button type="submit" class="btn btn-default">로그아웃</button>
      </form>
      
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="<%=contextName%>/emp.do?act=EMPL">사원 관리 <span class="sr-only">(current)</span></a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">    
        <li><a href="<%=contextName%>/prov.do?act=PROVL">납품업체 관리</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">    
        <li><a href="<%=contextName%>/cus.do?act=CUSL">고객 관리</a></li>
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
              <h3 id="tables">사원 목록</h3>
            </div>
            <div class="bs-component">
              <table class="table table-striped table-hover ">
                <thead>
                  <tr>
                    <th>No</th>
               		<th>사원번호</th>
                	<th>이름</th>
                	<th>부서</th>
                	<th>직급</th>
                	<th>권한</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if (empList.size() > 0) {
							                    		
					for (int i=0; i <empList.size(); i++) {		
						
						Employee emp =  empList.get(i);
						
						empNo		= emp.getEmpNo();
						empName		= emp.getEmpName();
						empPosi		= emp.getEmpPosi();
						empDept		= emp.getEmpDept();
						empAuth		= emp.getEmpAuth();
						
				%>
					 <tr>
		                <td><%=virtual_no--%></td>
		                <td><%=empNo %></td>
		                <td><b><a href="<%=contextName%>/emp.do?act=EMPR&NO=<%=empNo %>&page=<%=cpage%>&srch_type=<%=srch_type%>&srch_keyword=<%=srch_keyword%>"> <%=empName%></a></b></td>
		                <td><%=empDept%></td>
		                <td><%=empPosi%></td>
		                <td><%=empAuth%></td>
		              </tr>
                  <%}
					
                }else{ %>
                  <tr>
                    <td colspan="6" align="center">자료가 존재하지 않습니다.</td>
                  </tr>
                  <%} %>
                </tbody>
              </table> 

            </div><!-- /example -->
          </div>
        </div>
      </div>
    <div class="col-lg-offset-x">  
    <ul class="pagination">
    <%if(cpage == 1){ %>
      <li class="disabled"><a href="<%=contextName%>/emp.do?act=EMPL&page=1">&laquo;</a></li>
    <%}else{ %>
      <li class="active"><a href="<%=contextName%>/emp.do?act=EMPL&page=1">&laquo;</a></li>
    <%} %>
    
      
                <%for(int i=startPage; i<=endPage; i++){ %>
                    <%if(cpage == i){ %>
                         <li class="active"><a href="<%=contextName%>/emp.do?act=EMPL&page=<%=i%>"><%=i %></a></li>
                    <%}else{ %>
                        <li><a href="<%=contextName%>/emp.do?act=EMPL&page=<%=i%>"><%=i %></a></li>
                    <%} %>
                <%} %>


    <%if(endPage < 11){ %>
       <li class="disabled"><a href="#">&raquo;</a></li>
    <%}else{ %>
      <li><a href="<%=contextName%>/emp.do?act=EMPL&page=<%=startPage-1 %>">&raquo;</a></li>
    <%} %>
    
    </ul>
    
    
    </div>
    
<div class="col-md-offset-11">    
     <p class="bs-component">
     	<a href="javascript:insert_click('<%=contextName%>/emp.do?act=EMPCR')" class="btn btn-primary">사원 등록</a>
     </p>
</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>