<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.br.notice.model.vo.Notice" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background:black;
        color:white;
        width:1000px;
        height:500px;
        margin:auto;
        margin-top:50px;
    }
    .list-area{
        border:1px solid white;
        text-align:center;
    }
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
    <div class="outer">
        <br>
        <h2 align="center">공지사항</h2>
        <br>
        
        <% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
        <!-- && 이전의 결과가 false일 경우 뒤쪽 조건 검사는 시행되지 않음 => NullPointerException 발생 안함 -->
        <!-- 현재 로그인한 사용자가 관리자일 경우 보여질 div -->
        <div align="right" style="width:850px">
        	<!-- <button onclick="location.href = '요청할url'">글작성</button> -->
        	<a href="<%= contextPath %>/enrollForm.no" class="btn btn-secondary btn-sm">글작성</a>
        	<br><br>
        </div>
		<% } %>
		
        <table class="list-area" align="center">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th width="400">글제목</th>
                    <th width="100">작성자</th>
                    <th>조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
			<% if(list.isEmpty()){ %>
                <!-- case1. 공지글이 없을 경우 -->
                <tr>
                    <td colspan="5">존재하는 공지사항이 없습니다.</td>
                </tr>
			<% }else{ %>
                <!-- case2. 공지글이 있을 경우 -->
                <% for(Notice n : list){ %>
                <tr>
                    <td><%= n.getNoticeNo() %></td>
                    <td><%= n.getNoticeTitle() %></td>
                    <td><%= n.getNoticeWriter() %></td>
                    <td><%= n.getCount() %></td>
                    <td><%= n.getCreateDate() %></td>
                </tr>
                <% } %>
            <% } %>
            </tbody>
        </table>
        <script>
        	$(function(){
        		$(".list-area>tbody>tr").click(function(){
        			
        			const num = $(this).children().eq(0).text(); // 클릭했을때의 글 번호
        			
        			// 요청할url?키=밸류&키=밸류...
        			// 요청시 전달값(키=밸류) => 쿼리스트링
        			
        			location.href = "<%= contextPath %>/detail.no?no=" + num;
        		})
        	})
        </script>

        <br><br>
    </div>
	
</body>
</html>