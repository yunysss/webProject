<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .login-area>*{float:right;}
    .login-area a{
        text-decoration:none;
        color:black;
        font-size:12px;
    }

    .nav-area{background:black;}
    .menu{
        display:table-cell;
        width:150px;
        height:50px;
    }
    .menu a{
        text-decoration:none;
        color:white;
        font-size:20px;
        font-weight:bold;
        display:block;
        width:100%;
        height:100%;
        line-height:50px;
    }
    .menu a:hover{background:darkgray;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- 매페이지마다 include되기 때문에 menubar.jsp에 연결 -->

</head>
<body>
	<c:if test="${ not empty alertMsg }">
		<script>
			alert("${ alertMsg }");
		</script>
		<c:remove var="alertMsg" scope="session"/>
	</c:if>

	<h1 align="center">Welcome Boram World</h1>

    <div class="login-area">
    
    	<c:choose>
    		<c:when test="${ empty sessionScope.loginUser }">
		        <!-- case1. 로그인 전 -->
		        <form action="login.me" method="post">
		            <table>
		                <tr>
		                    <th>아이디</th>
		                    <td><input type="text" name="userId" required></td>
		                </tr>
		                <tr>
		                    <th>비밀번호</th>
		                    <td><input type="password" name="userPwd" required></td>
		                </tr>
		                <tr>
		                    <th colspan="2">
		                        <button type="submit">로그인</button>
		                        <button type="button" onclick="enrollPage();">회원가입</button>
		                    </th>
		                </tr>
		            </table>
		            <script>
		            	function enrollPage(){
		            		// location.href = "views/member/memberEnrollForm.jsp";
		            		// url에 웹 애플리케이션의 디렉토리 구조가 노추로디면 보안에 취약
		            		
		            		location.href = "enrollForm.me";
		            		// 단순한 페이지 요청에 있어서도 Servlet을 만들어서 처리하자
		            		// 포워딩방식을 통해서 해당 페이지가 보여지게끔 (이때 url에는 서블릿 매핑값만 남아있음)
		            	}
		            </script>
		        </form>
        	</c:when>
        	<c:otherwise>
		        <!-- case2. 로그인 후 -->
		        <div>
		            <b>${ loginUser.userName }님</b>의 방문을 환영합니다 <br><br>
		            <div align="center">
		                <a href="myPage.me">마이페이지</a>
		                <a href="logout.me">로그아웃</a>
		            </div>
		        </div>
        	</c:otherwise>
        </c:choose>
    </div>

    <br clear="both">
    <br>

    <div class="nav-area" align="center">
        <div class="menu"><a href="${ pageContext.servletContext.contextPath }">HOME</a></div>
        <div class="menu"><a href="list.no">공지사항</a></div>
        <div class="menu"><a href="list.bo?cpage=1">일반게시판</a></div> <!-- cpage : currentpage(클릭한페이지) -->
        <div class="menu"><a href="list.th">사진게시판</a></div>
    </div>
</body>
</html>