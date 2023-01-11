package com.br.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.notice.model.service.NoticeService;
import com.br.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 상세 요청
		
		int noticeNo = Integer.parseInt(request.getParameter("no")); // 조회하고자했던 공지사항번호
		
		// 1) 조회수 증가 (update)
		int result = new NoticeService().increaseCount(noticeNo);
		
		if(result > 0) { // 성공 == 조회가능한 공지사항 맞다 
						 //     => 응답페이지(views/notice/noticeDetailView.jsp)
			
			// 2) 공지사항 상세조회 (select)
			Notice n = new NoticeService().selectNotice(noticeNo);
			
			request.setAttribute("notice", n);
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		} else { // 실패 == 조회불가능한 공지사항 => 에러페이지
			
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
