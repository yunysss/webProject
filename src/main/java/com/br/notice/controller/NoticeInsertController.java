package com.br.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.br.member.model.vo.Member;
import com.br.notice.model.service.NoticeService;
import com.br.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		
		// 로그인한 회원 정보를 얻어내는 방법
		// 1. jsp에서 input type="hidden"으로 애초에 요청시 숨겨서 전달하기
		// 2. session에 담겨있는 회원객체로부터 뽑기
		HttpSession session = request.getSession();
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setNoticeWriter(String.valueOf(userNo)); // "1"
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {
			// 성공 => 공지사항목록페이지(noticeListView.jsp)
			//        alert로 "성공적으로 공지사항 등록되었습니다."
			session.setAttribute("alertMsg", "성공적으로 공지사항 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.no");
		} else {
			// 실패 => 에러페이지(errorPage.jsp)
			//        에러문구 "공지사항 등록 실패"
			request.setAttribute("errorMsg", "공지사항 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
