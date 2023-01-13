package com.br.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.br.board.model.service.BoardService;
import com.br.board.model.vo.Attachment;
import com.br.board.model.vo.Board;
import com.br.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1_1) 파일의 용량 제한
			int maxSize = 10*1024*1024;
			// 1_2) 파일을 저장시킬 물리적인 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/"); // 맨앞의 '/'는 webapp폴더 의미
			
			// 2. 전달된 파일 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 3. DB에 기록
			
			// 공통적으로 수행 : UPDATE BOARD
			int boardNo = Integer.parseInt(multiRequest.getParameter("no"));
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			Attachment at = null; // 처음에는 null, 새로운 첨부파일이 넘어왔을 경우 그때 생성
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// 새로 넘어온 첨부파일이 있을 경우 => Attachment 객체 생성
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles/");
				
				if(multiRequest.getParameter("originFileNo") != null) {
					// 기존의 첨부파일이 있었을 경우 => UPDATE ATTACHMENT (기존파일번호)
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				} else {
					// 기존의 첨부파일이 없었을 경우 => INSERT ATTACHMENT (현재게시글번호)
					at.setRefBoardNo(boardNo);
				}
				
			}
			
			int result = new BoardService().updateBoard(b, at);
			// 새로운 첨부파일 X							=> b, null	=> BOARD UPDATE
			// 새로운 첨부파일 O, 기존의 첨부파일 O			=> b, fileNo이 담긴 at => BOARD UPDATE, ATTACHMENT UPDATE
			// 새로운 첨부파일 O, 기존의 첨부파일 X			=> b, refBoardNo이 담긴 at => BOARD UPDATE, ATTACHMENT INSERT
			
			if(result > 0) {
				// 성공 => views/board/boardDetailView.jsp
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath() + "/detail.bo?no=" + boardNo);
				
			}else {
				// 실패 => 에러페이지
				request.setAttribute("errorMsg", "수정실패");
				request.getRequestDispatcher("views/common/errorPage.jsp");
			}
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
