<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>

    <div class="outer" align="center">
        <br>
        <h2>공지사항 상세보기</h2>
        <br>

        <table id="detail-area" border="1">
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430">${ notice.noticeTitle }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${ notice.noticeWriter }</td>
                <th>작성일</th>
                <td>${ notice.createDate }</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height:150px;">${ notice.noticeContent }</p>
                </td>
            </tr>
        </table>
        <br><br>

        <div>
            <a href="list.no" class="btn btn-secondary btn-sm">목록가기</a>
            
            <c:if test="${ not empty loginUser and loginUser.userId eq notice.noticeWriter }">
            <!-- 현재 로그인한 사용자가 해당 글을 쓴 본인일 경우 -->
            <a href="updateForm.no?no=${ notice.noticeNo }" class="btn btn-warning btn-sm">수정하기</a>
            <a href="delete.no?no=${ notice.noticeNo }" class="btn btn-danger btn-sm">삭제하기</a>
            
            </c:if>
            <!-- 
            	삭제하기 클릭시 바로 삭제요청이 되게끔
            	=> UPDATE NOTICE SET STATUS = 'N' WHERE NOTICE_NO = ?
            	
            	>> 해당 요청시 실행된 Servlet 클래스 만들어서 요청처리하기
            	
            		> 요청 성공시 => 공지사항목록페이지, alert(성공적으로 삭제되었습니다.)
            		> 요청 실패시 => 에러문구 보여지는 에러페이지 (공지사항 삭제 실패)
             -->
        </div>
    </div>
</body>
</html>