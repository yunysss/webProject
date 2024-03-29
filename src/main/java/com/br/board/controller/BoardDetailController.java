package com.br.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.board.model.service.BoardService;
import com.br.board.model.vo.Attachment;
import com.br.board.model.vo.Board;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청시 전달값 뽑기 (no키값으로 조회할 글번호)
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		BoardService bService = new BoardService();
		// 1) 조회수 증가 요청
		int result = bService.increaseCount(boardNo);
		
		if(result > 0) {
			// >> 조회수 증가 성공시
			//		2_1) BOARD테이블로부터 게시글 정보 조회 요청
			Board b = bService.selectBoard(boardNo);
			//		2_2) ATTACHMENT테이블로부터 첨부파일 정보 조회 요청
			//		두 객체 담아서 boardDetailView.jsp로 응답
			Attachment at = bService.selectAttachment(boardNo);
			//		두 객체 담아서 boardDetailView.jsp로 응답
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
		} else {
			// >> 조회수 증가 실패시
			// 		=> 에러페이지
			request.setAttribute("errorMsg", "조회실패");
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
