<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.br.board.model.vo.Category" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
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
        height:550px;
        margin:auto;
        margin-top:50px;
    }

    #enroll-form table{border:1px solid white;}
    #enroll-form input, #enroll-form textarea{
        width:100%;
        box-sizing:border-box;
    }
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2>일반게시판 작성하기</h2>
        <br>

        <form id="enroll-form" action="<%= contextPath %>/insert.bo" method="post" enctype="multipart/form-data"> <!-- 파일넘기려면 enctype속성 필요 -->

            <table>
                <tr>
                    <th width="70">카테고리</th>
                    <td width="500">
                        <select name="category">
                            <!-- db로부터 category테이블에 존재하는 값들 조회해와서 -->
                            <% for(Category c : list){ %>
                            	<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" rows="10" style="resize:none" required></textarea></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td><input type="file" name="upfile"></td>
                </tr>
            </table>
            <br>

            <div>
                <button type="submit">작성하기</button>
                <button type="reset">취소하기</button>
            </div>
        </form>
    </div>
	
	
</body>
</html>