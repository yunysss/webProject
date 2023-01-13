<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.br.board.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
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
        margin:auto;
        margin-top:50px;
    }

    
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2>일반게시판 상세보기</h2>
        <br>

        <table border="1" id="detail-area">
            <tr>
                <th width="70">카테고리</th>
                <td width="70"><%= b.getCategory() %></td>
                <th width="70">제목</th>
                <td width="350"><%= b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%= b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3" height="200">
                    <%= b.getBoardContent() %>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
					<% if(at == null){ %>
	                    <!-- case1. 첨부파일 없을 경우 -->
	                    첨부파일이 없습니다.
					<% } else {%>
	                    <!-- case2. 첨부파일 있을 경우 : 클릭시 다운로드 -->
	                    <a download="<%= at.getOriginName() %>" href="<%= contextPath%>/<%=at.getFilePath() + at.getChangeName()%>"><%= at.getOriginName() %></a> 
	                    <!-- 첨부파일의저장경로,첨부파일의실제저장된파일명(수정명) -->
	                    <!-- localhost:8887/web//resources/board_upfiles/2023011212031599191.jpg -->
	                    <!-- a태그에 download속성 추가해야 다운로드됨 (안붙이면 그냥 브라우저에서 열림)-->
	                    <!-- download만 제시하면 저장된 파일명으로 저장됨 / 속성값 제시하면 그 이름으로 저장됨 -->
	                    <!-- 첨부파일 원본명 -->
                    <% } %>
                </td>
            </tr>
        </table>
        <br>

        <div>
            <a href="<%= contextPath %>/list.bo?cpage=1" class="btn btn-secondary btn-sm">목록가기</a>
            
            <% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
	            <a href="<%=contextPath %>/updateForm.bo?no=<%= b.getBoardNo() %>" class="btn btn-warning btn-sm">수정하기</a>
	            <a href="" class="btn btn-danger btn-sm">삭제하기</a>
			<% } %>
        </div>
        
        <br><br>
    </div>
</body>
</html>